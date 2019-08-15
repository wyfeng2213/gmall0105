package com.atguigu.gmall.shoppingcart.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.atguigu.gmall.bean.OmsCartItem;
import com.atguigu.gmall.service.CartService;
import com.atguigu.gmall.shoppingcart.mapper.OmsCartItemMapper;
import com.atguigu.gmall.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author : 熊亚东
 * @description :
 * @date : 2019/7/17 | 13:17
 **/
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    OmsCartItemMapper omsCartItemMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public OmsCartItem ifCartsExistByUser(String memberId, String skuId) {
        OmsCartItem omsCartItem = new OmsCartItem();
        omsCartItem.setMemberId(memberId);
        omsCartItem.setProductSkuId(skuId);
        OmsCartItem omsCartItem1 = omsCartItemMapper.selectOne(omsCartItem);
        return omsCartItem1;
    }

    @Override
    public void addCart(OmsCartItem omsCartItem) {
        if (StringUtils.isNotBlank(omsCartItem.getMemberId())) {
            omsCartItemMapper.insert(omsCartItem);
        }
    }

    @Override
    public void updataCart(OmsCartItem omsCartItemFromDb) {
        Example e = new Example(OmsCartItem.class);
        e.createCriteria().andEqualTo("id", omsCartItemFromDb.getId());
        omsCartItemMapper.updateByExampleSelective(omsCartItemFromDb, e);
    }

    // 刷新缓存 , 从数据库中重新查询数据 加载到缓存中
    @Override
    public void flushCartCatch(String memberId) {
        OmsCartItem omsCartItem = new OmsCartItem();
        omsCartItem.setMemberId(memberId);
        List<OmsCartItem> omsCartItems = omsCartItemMapper.select(omsCartItem);
        /*同步到redis缓存中*/
        Jedis jedis = redisUtil.getJedis();
        /*结构
             K                V
        * memberId  map<skuId , cartItem->JSON>
        * V 用Hashmap<>集合表示,方便修改，更新，查询数据
        * */
        HashMap<String, String> map = new HashMap<>();
        for (OmsCartItem cartItem : omsCartItems) {
            //刷新缓存的时候计算总价格
            map.put(cartItem.getProductSkuId(), JSON.toJSONString(cartItem));
        }
        jedis.hmset("user:" + memberId + ":cart", map);
        jedis.close();
    }

    @Override
    public List<OmsCartItem> cartList(String memberId) {
        Jedis jedis = null;
        List<OmsCartItem> omsCartItems = new ArrayList<>();
        try {
            jedis = redisUtil.getJedis();/*Hash()查询*/
            List<String> hvals = jedis.hvals("user:" + memberId + ":cart");
            for (String hval : hvals) {
                OmsCartItem omsCartItem = JSON.parseObject(hval, OmsCartItem.class);
                omsCartItems.add(omsCartItem);
            }
        } catch (Exception e) {
            // 真正的异常需要放到数据库中去 e.getMessage();
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
        return omsCartItems;
    }

    @Override
    public void deletByOrderItemProductSkuId(String productSkuId) {
        OmsCartItem omsCartItem = new OmsCartItem();
        omsCartItem.setProductSkuId(productSkuId);
        omsCartItemMapper.deleteByPrimaryKey(omsCartItem);
    }

    // 每次查询获取购物车列表或者添加购物车的时候 都会重新加载缓存, 不需要担心删除的时候数据库数据 jedis数据没有删除成功的问题
    //只需要再次进入购物车列表就可以重新加载缓存
    @Override
    public void delCartByMemberid(String memberId) {
        OmsCartItem omsCartItem = new OmsCartItem();
        omsCartItem.setMemberId(memberId);
        omsCartItemMapper.delete(omsCartItem);

        Jedis jedis = null;
        try {
            jedis = redisUtil.getJedis();
            jedis.del("user:" + memberId + ":cart");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    @Override
    public void checkCart(String skuId, String isChecked, String userId) {
        // 1. 更新到数据库 , 可以一段时间之后 或者深夜同步到数据中去
        // 2.更新缓存  flushCartCatch(userId)

        //更新购物车中的isChecked标志  redis
        Jedis jedis = redisUtil.getJedis();
        String userCartKey = "user:" + userId + ":cart";
        String cartJson = jedis.hget(userCartKey, skuId);

        OmsCartItem cartInfo = JSON.parseObject(cartJson, OmsCartItem.class);
        cartInfo.setIsChecked(isChecked);
        String cartCheckedJson = JSON.toJSONString(cartInfo);
        jedis.hset(userCartKey, userId, cartCheckedJson);

        //2.新增到已选中购物车
        String userCheckedKey = "user:" + userId + ":checked";
        if (isChecked.equals("1")) {
            jedis.hset(userCheckedKey, skuId, cartCheckedJson);
        } else {
            jedis.hdel(userCheckedKey, skuId);
        }
        jedis.close();

    }

}

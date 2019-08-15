package com.atguigu.gmall.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.atguigu.gmall.annotations.LoginRequired;
import com.atguigu.gmall.bean.OmsCartItem;
import com.atguigu.gmall.bean.OmsOrder;
import com.atguigu.gmall.bean.OmsOrderItem;
import com.atguigu.gmall.bean.UmsMemberReceiveAddress;
import com.atguigu.gmall.service.CartService;
import com.atguigu.gmall.service.OrderService;
import com.atguigu.gmall.service.SkuService;
import com.atguigu.gmall.service.UserService;
import com.atguigu.gmall.util.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author : 熊亚东
 * @description :
 * @date : 2019/7/20 | 13:29
 **/
@Controller
@CrossOrigin
public class OrderController {

    @Reference
    CartService cartService;

    @Reference
    UserService userService;

    @Reference
    OrderService orderService;

    @Reference
    SkuService skuService;

    // 生成订单请求
    @RequestMapping("submitOrder")/*提交订单*/
    @LoginRequired(loginSuccess = true)
    public ModelAndView submitOrder(String receiveAddressId, BigDecimal totalAmount, String tradeCode, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        //总金额 可以不用提交 , 用户可以修改的, 总金额从购物车里面计算的
        String memberId = (String) request.getAttribute("memberId");
        String nickname = (String) request.getAttribute("nickname");
        /*1.检查交易编码*/
        String success = orderService.checkTradeCode(memberId, tradeCode);
        if (success != null && success.equals("success")) {
            List<OmsOrderItem> omsOrderItems = new ArrayList<>();
            OmsOrder omsOrder = new OmsOrder();
            omsOrder.setAutoConfigDay("7");/*7天后自动收货*/
            omsOrder.setCreateTime(new Date());
            omsOrder.setMemberId(memberId);
            omsOrder.setMemberUsername(nickname);
            omsOrder.setNote("能快点儿送达吗？我急用！！！");
            String outTradeNumber = "gmall";
            outTradeNumber = outTradeNumber + System.currentTimeMillis();/*将毫秒时间戳拼接到外部订单号*/
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMDDHHmmss");
            outTradeNumber = outTradeNumber + simpleDateFormat.format(new Date());/*将时间字符串拼接到外部订单号*/
            omsOrder.setOrderSn(outTradeNumber);
            // 订单总金额
            omsOrder.setPayAmount(totalAmount.toString());
            omsOrder.setOrderType("1");
            UmsMemberReceiveAddress umsMemberReceiveAddress = userService.getReceiveAddressByReceiveAddressId(receiveAddressId);
            omsOrder.setReceiverCity(umsMemberReceiveAddress.getCity());
            omsOrder.setReceiverDetailAddress(umsMemberReceiveAddress.getDetailAddress());
            omsOrder.setReceiverName(umsMemberReceiveAddress.getName());
            omsOrder.setReceiverPhone(umsMemberReceiveAddress.getPhoneNumber());
            omsOrder.setReceiverPostCode(umsMemberReceiveAddress.getPostCode());
            omsOrder.setReceiverProvince(umsMemberReceiveAddress.getProvince());
            omsOrder.setReceiverRegion(umsMemberReceiveAddress.getRegion());
            /*当前日期加一天 ，一天后配送*/
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, 1);
            Date time = calendar.getTime();
            omsOrder.setReceiveTime(time);
            omsOrder.setSourceType("0");/*0:PC 1:APP*/
            omsOrder.setStatus("0");
            // 需要调用购物车的服务 获取总金额
            omsOrder.setTotalAmount(totalAmount.toString());
            /*2.根据用户id获得要购买的商品列表（从购物车中取出商品列表）和总价格*/
            List<OmsCartItem> omsCartItems = cartService.cartList(memberId);
            /*这里可以检验是否勾选，，，这里就不操作了*/
            for (OmsCartItem omsCartItem : omsCartItems) {
                OmsOrderItem omsOrderItem = new OmsOrderItem();
                // 价格
                omsOrderItem.setProductPrice(omsCartItem.getPrice());
//                omsOrderItem.setRealAmount(omsCartItem.getTotalPrice()); // 设置实际价格
                omsOrderItem.setProductName(omsCartItem.getProductName());
                omsOrderItem.setProductCategoryId(omsCartItem.getProductCategoryId());
                omsOrderItem.setProductQuantity(omsCartItem.getQuantity().toString());
                omsOrderItem.setOrderSn(outTradeNumber);/*外部订单号，用来和其他系统进行交互，比如连接Alipay（支付宝）时使用*/
                omsOrderItem.setProductSkuCode("66666666666666");
                omsOrderItem.setProductId(omsCartItem.getProductId());
                omsOrderItem.setProductSkuId(omsCartItem.getProductSkuId());
                omsOrderItem.setProductSn(omsCartItem.getProductSn());
                /*3.检验价格*/
                // 不替用户做决定 , 一定要回滚 , 比如买一台组装电脑 ,结果没有显示器 给提交了订单 , 那组装电脑其余部件买了有用 ?
                boolean b = skuService.checkPrice(omsCartItem.getProductSkuId(), omsCartItem.getPrice());
                if (b == false) {
                    //request.setAttribute("errMsg","您选择的商品可能存在价格变动，请重新下单");
                    ModelAndView mv = new ModelAndView("tradeFail");
                    return mv;
                } else {
                    omsOrderItems.add(omsOrderItem);
                }
                /*4.检验库存 , 远程调用库存系统 仓库系统 如果库存不足,应该返回失败, 提示库存不足*/
            }
            // === 需要设置购物车计算的 总金额   omsOrder.setPayAmount(totalAmount.toString());
            omsOrder.setOmsOrderItems(omsOrderItems);
            /*5.将订单和订单详情写入数据库  ==== 删除购物车对应的被勾选的商品*/
            orderService.SaveOrderAndDeletCartItem(omsOrder);
            /*6.订单成功之后需要删除购物车数据，redis mysql*/
            cartService.delCartByMemberid(memberId);
            /*7.重定向到支付系统*/
            ModelAndView modelAndView = new ModelAndView("redirect:http://localhost:8087/index");
            // 真实项目中通过用户id能够查询到 , 通过参数传递不安全
            modelAndView.addObject("outTradeNumber", outTradeNumber);
           modelAndView.addObject("totalAmount", totalAmount);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("fail");
            return modelAndView;
        }
    }

    //需要5个服务 , 不登录searchWeb可以不用 , 是购物车列表过来的需要服务
    // 1.购物车服务 , 2.passport服务 3.订单服务 4.用户服务
    // 进入结算界面 , 直接从缓存或者数据库中查询用户所要购买的商品，转化成订单 , 确认信息
    @RequestMapping("toTrade")/*去结算页面*/
    @LoginRequired(loginSuccess = true)
    public String toTrade(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String memberId = (String) request.getAttribute("memberId");
        String nickname = (String) request.getAttribute("nickname");
        String sourceType = userService.getSourceType(memberId);
        modelMap.put("memberId", memberId);
        modelMap.put("nickname", nickname);
        List<OmsCartItem> omsCartItems = new ArrayList<>();
        /*收货人地址集合，一个人可能存在多个收货地址*/
        List<UmsMemberReceiveAddress> receiveAddressByMemberId = userService.getReceiveAddressByMemberId(memberId);
        /*将购物车集合转换成清单结算集合*/
        omsCartItems = cartService.cartList(memberId);
        List<OmsOrderItem> omsOrderItems = new ArrayList<>();
        int ALL_PRICE = 0;/*总价*/
        for (OmsCartItem omsCartItem : omsCartItems) {
            /*每循环一个已经被勾选的购物车对象，就封装一个商品详情到omsOrderItems*/
//            if (omsCartItem.getIsChecked() != null){/*选中*/
//                if (omsCartItem.getIsChecked().equals("1")){
//                    OmsOrderItem omsOrderItem = new OmsOrderItem();
//                    omsOrderItem.setProductPrice(omsCartItem.getPrice());
//                    omsOrderItem.setProductName(omsCartItem.getProductName());/*还可以继续封装其他参数。。。。*/
//                    omsOrderItems.add(omsOrderItem);
//                }
//            }else {/*用户没有选择购物车中的商品*/
//                omsOrderItems.add(null);
//            }/*这里由于没有写isChecked()方法就不判断了*/
            OmsOrderItem omsOrderItem = new OmsOrderItem();
            omsOrderItem.setProductPrice(omsCartItem.getPrice());
            omsOrderItem.setOrderSn(omsCartItem.getProductSn());
            omsOrderItem.setProductQuantity(omsCartItem.getQuantity().toString());
            //计算价格
            ALL_PRICE = ALL_PRICE + (Integer.parseInt(omsCartItem.getPrice()) * Integer.parseInt(omsCartItem.getQuantity().toString()));
            omsOrderItem.setProductName(omsCartItem.getProductName());/*还可以继续封装其他参数。。。。*/
            omsOrderItems.add(omsOrderItem);

        }
        modelMap.put("totalAmount", ALL_PRICE);
        modelMap.put("sourceType", sourceType);
        modelMap.put("omsOrderItems", omsOrderItems);
        modelMap.put("receiveAddress", receiveAddressByMemberId);

        /*============================重要：生成交易编码，防止用户回退重复提交订单 ，在提交订单前做交易编码校验=================================*/
        String tradeCode = orderService.genTradeCode(memberId);
        modelMap.put("tradeCode", tradeCode);

        return "toTrade";
    }

}

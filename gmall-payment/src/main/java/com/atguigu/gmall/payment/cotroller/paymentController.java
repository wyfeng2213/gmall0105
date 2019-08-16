package com.atguigu.gmall.payment.cotroller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.atguigu.gmall.annotations.LoginRequired;
import com.atguigu.gmall.bean.OmsOrder;
import com.atguigu.gmall.bean.PaymentInfo;
import com.atguigu.gmall.payment.config.AlipayConfig;
import com.atguigu.gmall.service.OrderService;
import com.atguigu.gmall.service.PaymentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : 熊亚东
 * @description :
 * @date : 2019/7/22 | 8:54
 **/
@Controller
@CrossOrigin
public class paymentController {

    @Autowired
    AlipayClient alipayClient;

    @Autowired
    PaymentService paymentService;

    @Reference
    OrderService orderService;

    //支付平台回调请求 , 更新支付状态
    @RequestMapping("alipay/callback/return")
    @LoginRequired(loginSuccess = true)/*一下两个参数也可以通过此注释从请求域中获得获得，参考orderController*/
    public String alipayCallBackReturn(HttpServletRequest request, ModelMap modelMap) {
        /*回调请求中获取支付宝参数 ， 详情请参考蚂蚁金服的开发者文档alipay*/
        String sign = request.getParameter("sign");/*支付宝签名*/
        String tradeNo = request.getParameter("trade_no");/*支付宝交易凭证号*/
        String outTradeNo = request.getParameter("out_trade_no");
        String tradeStatus = request.getParameter("trade_status");/*订单状态*/
        String totalAmount = request.getParameter("total_amount");
        String subject = request.getParameter("subject");
        //callback_content 回调的信息内容 比如 :charset=utf-8&out_trade_no=atguigu201903201551591553068319124&method=alipay.trade.page.pay.return&total_amount=0.01&sign=JHMpfB9wv%2FOaNV9Cpjp7%2B6uY83ScfJ4YIG6dsDtrJlbbRJj6Z7%2FMlT3EazeB487wlKGPFim9L2xzIl8MwBpCwOc2qI95pCDwDrXgaO%2F2yA%2FlDp6bDkcRx84Lkm%2F2MNwZ%2FyFSW%2FyyDxWGEI3izHYMm1rf8T6nNDKvfuKTrKiKiIAGSv%2FJX1z7InGW%2BgeWtLlYWdV9fS1aKDEUZwGJaKwQeGf0c2YpZ2u%2FPoBuT32IQTbACx60SO4Jdz4y%2BVjwF5UmvLZD6HP7n5hvcQE833r9FOCU3rOskdAWNWt4wEvaJ%2FAuq%2BFAg6xHTDk1E2iDwkLVjumnYM%2FUcpw6G6Yu60nVtQ%3D%3D&trade_no=2019032022001409701031928056&auth_app_id=2018020102122556&version=1.0&app_id=2018020102122556&sign_type=RSA2&seller_id=2088921750292524&timestamp=2019-03-20+15%3A52%3A40
        String callback_content = request.getQueryString();
        /*通过支付宝的paramMap进行签名验证 ，2.0版本的接口将paramMap参数去掉了，所以我们这里进行一次假验证*/
        // 不在returnurl里面验签了 , 只在notifyurl验签
        //**获取paramsMap
//           1>Map<String, String> paramsMap = ... //将异步通知中收到的所有参数都存放到map中
//           2>可以放在方法的参数中获取  @RequestParam Map<String,String> paramMap
//        boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE) //调用SDK验证签名
//        if(signVerfied){
//            //  验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，
//            校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
//        }else{
//            // 验签失败则记录异常日志，并在response中返回failure.
//        }
        if (StringUtils.isNotBlank(sign)) {
            /*验证成功  更新信息到后台*/
            PaymentInfo paymentInfo = new PaymentInfo();
            paymentInfo.setOrderSn(outTradeNo);
            paymentInfo.setPaymentStatus("已付款");
            // 需要加判断 , tradeNo必须要有才修改信息
            paymentInfo.setAlipayTradeNo(tradeNo);
            // 回调的内容
            paymentInfo.setCallbackContent(callback_content);
            paymentInfo.setCallbackTime(new Date());
            paymentInfo.setConfirmTime(new Date());

            // 以上操作支付成功  更新支付信息和订单信息,库存信息 应该是同步执行的
            /*=====================================================================================================================================*/
            /*更新用户的支付状态*/
            /*支付成功后 引起的系统服务 ，——> 订单服务更新 ，——> 库存服务 ，——> 物流服务*/
            /*这里我们将引入分布式事务消息中间件ActiveMQ*/
            /*调用ActiveMQ发送支付成功的消息*/
            /*进行支付更新的幂等性检查操作在updatePayment方法里面，防止与PaymentServiceMqListener一起重复更新*/
            /*=====================================================================================================================================*/
            //更新操作有发送给订单消息的服务
            paymentService.updatePayment(paymentInfo);
        }
        return "finish";
    }

    /*未使用微信支付业务*/
    @RequestMapping("weixin/submit")
    @LoginRequired(loginSuccess = true)/*一下两个参数也可以通过此注释从请求域中获得获得，参考orderController*/
    public String weixin(String outTradeNumber, BigDecimal totalAmount, HttpServletRequest request, ModelMap modelMap) {
        return null;
    }

    // 提交支付请求 , 调用支付平台接口
    /*以下下两个参数也可以通过此注释从请求域中获得获得，参考orderController*/
    //outTradeNumber , totalAmount 这两个参数可以不用传递 , 通过用户id查询
    // 需要启动服务 1.购物车服务 , 2.passport服务 3.订单服务 4.用户服务 5.orderService 6,skuService
    @RequestMapping("alipay/submit")
    @LoginRequired(loginSuccess = true)
    @ResponseBody
    public String alipay(String outTradeNumber, BigDecimal totalAmount, HttpServletRequest request, ModelMap modelMap) {
        /*获得一个支付宝的请求客户端（是一个封装好的Http的表单请求）*/
        String form = null;
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();/*创建API对应的request*/

        /*请求设置 回调函数地址*/
        alipayRequest.setReturnUrl(AlipayConfig.return_payment_url); // 同步回调地址
        alipayRequest.setNotifyUrl(AlipayConfig.notify_payment_url); // 异步回调地址 (公网)
        // 产品的信息
        Map<String, Object> map = new HashMap<>();
        map.put("out_trade_no", outTradeNumber);
        map.put("product_code", "FAST_INSTANT_TRADE_PAY"); //固定值
        // 商品金额
        map.put("total_amount", 100);
        // 需要查一下商品标题 ,如果一件显示名称 , 如果是多件显示 合并 | 4笔订单
        map.put("subject", "东方商城华为手机");
        String param = JSON.toJSONString(map);
        //请求设置产品信息
        alipayRequest.setBizContent(param);
        try {
            //调用支付接口   获取表单信息返回给前端 , 其实就是支付宝自己的一个的支付页面
            form = alipayClient.pageExecute(alipayRequest).getBody();
            System.out.println(form);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        /*生成并且保存用户的支付信息 未付款的信息*/
        OmsOrder omsOrder = orderService.getOrderByOutTradeNumber(outTradeNumber);
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setCreateTime(new Date());
        paymentInfo.setPaymentStatus("未付款");
        paymentInfo.setOrderId(omsOrder.getId());
        paymentInfo.setOrderSn(omsOrder.getOrderSn());
        // 商品标题 可以从页面传递过来
        paymentInfo.setSubject("东方商城商品");
        paymentInfo.setTotalAmount(omsOrder.getTotalAmount());
        paymentService.savePaymentInfo(paymentInfo);
        /*=================================================================================================================*/
        /*向消息中间件发送一个检查支付状态的延迟消息队列 ，在activeMQ的config文件<broker>标签配置schedulerSupport="true"*/
        /*=================================================================================================================*/
        /*定义监听循环次数6次   向消息中间件发送一个检查支付状态(支付服务消费)的延迟消息队列*/
        paymentService.sendDelayPaymentResultCheckQueue(outTradeNumber, 6);
        /*提交请求到支付宝*/
        return form;
    }

    //跳转到自己写的一个支付页面
    /*以下下两个参数也可以通过此注释从请求域中获得获得，参考orderController*/
    //outTradeNumber , totalAmount 这两个参数可以不用传递 , 通过用户id查询
    @RequestMapping("index")
    @LoginRequired(loginSuccess = true)/*一下两个参数也可以通过此注释从请求域中获得获得，参考orderController*/
    public String index(String outTradeNumber, BigDecimal totalAmount, HttpServletRequest request, ModelMap modelMap) {
        String memberId = (String) request.getAttribute("memberId");
        String nickname = (String) request.getAttribute("nickname");
        modelMap.put("outTradeNumber", outTradeNumber);
        modelMap.put("totalAmount", totalAmount);
        modelMap.put("nickname", nickname);
        return "index";
    }

}

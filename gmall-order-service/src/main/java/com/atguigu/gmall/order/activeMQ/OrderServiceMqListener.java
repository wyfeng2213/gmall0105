package com.atguigu.gmall.order.activeMQ;

import com.atguigu.gmall.bean.OmsOrder;
import com.atguigu.gmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;

/**
 * @author : 熊亚东
 * @description :
 * @date : 2019/7/23 | 15:34
 **/
@Component
public class OrderServiceMqListener {

    @Autowired
    OrderService orderService;

    @JmsListener(destination = "PAYMENT_SUCCESS_QUEUE" , containerFactory = "jmsQueueListener")
    public void consumPaymentResult(MapMessage mapMessage) throws JMSException {
        //====接受到支付成功,更新状态之后的通知 进行订单服务的更新 , 更新外部订单号

        String out_trade_no = mapMessage.getString("out_trade_no");
        /*在PaymentServiceImpl中支付成功后成功更新了paymentInfo的支付状态，发送消息给订单，让其更新支付状态（status）*/
        /*更新订单状态业务*/
        System.out.println(out_trade_no);
        OmsOrder omsOrder = new OmsOrder();
        omsOrder.setOrderSn(out_trade_no);
        //  更新操作有发送给库存消息的服务 , 更新操作有设置订单状态为成功
        // 调用库存系统 发送消息
        orderService.updataOrder(omsOrder);/*调用更新业务*/
        System.out.println("ok");
    }
}

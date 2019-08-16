package com.atguigu.gmall.payment;

import com.atguigu.gmall.mq.ActiveMQUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallPaymentApplicationTests {

    @Autowired
    ActiveMQUtil activeMQUtil;
    @Autowired
    JmsTemplate jmsTemplate;

    @Test
    public void contextLoads() throws JMSException {
        jmsTemplate.convertAndSend("");
        ConnectionFactory connectionFactory = activeMQUtil.getConnectionFactory();
        Connection connection = connectionFactory.createConnection();
        System.err.println(connection);
    }


}

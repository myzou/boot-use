package com.example.boot.rabbitmq;

import com.example.boot.rabbitmq.entity.Order;
import com.example.boot.rabbitmq.producer.RabbitSender;
import com.example.boot.rabbitmq.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqProducerApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    public void testSend() throws Exception {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setName("测试订单1");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
        orderService.createOrder(order);
    }

    @Autowired
    private RabbitSender rabbitSender;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Test
    public void testSender1() throws Exception {
        Map<String, Object> properties = new HashMap<>();
        properties.put("number", "12345");
        properties.put("send_time", simpleDateFormat.format(new Date()));
        rabbitSender.send("Hello RabbitMQ For Spring Boot!", properties);
    }

    @Test
    public void contextLoads() {
    }

}

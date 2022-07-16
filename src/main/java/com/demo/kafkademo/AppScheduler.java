package com.demo.kafkademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.UUID;

@Configuration
public class AppScheduler {

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    @Scheduled(fixedDelay = 2000)
    public void produce(){
        Order order = new Order();
        String randomId = UUID.randomUUID().toString();
        order.setOrderId(randomId);
        order.setOrderDetails("product details "+randomId);
        System.out.println("Producing records");
        this.kafkaTemplate.send("kafka-demo", randomId, order);
    }
}
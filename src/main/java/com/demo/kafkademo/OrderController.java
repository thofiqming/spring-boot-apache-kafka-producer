package com.demo.kafkademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    @PostMapping("/order/v1")
    public Order createOrder(@RequestBody Order order) {
        this.kafkaTemplate.send("kafka-demo", order);
        return order;
    }
}

package com.dropp.order.controller;

import com.dropp.order.dto.OrderDto;
import com.dropp.order.service.OrderService;
import com.dropp.order.dto.OrderFrontDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/saveOrder")
    public ResponseEntity<OrderDto> saveOrder(@RequestBody OrderFrontDto orderDto){
        OrderDto order = orderService.saveOrder(orderDto);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

}

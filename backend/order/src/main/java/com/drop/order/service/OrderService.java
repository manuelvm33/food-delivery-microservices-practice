package com.drop.order.service;

import com.drop.order.dto.OrderDto;
import com.drop.order.dto.OrderFrontDto;
import com.drop.order.dto.UserDto;
import com.drop.order.entity.Order;
import com.drop.order.mapper.OrderMapper;
import com.drop.order.repo.OrderRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    private static final String USER_SERVICE_URL = "http://USER-SERVICE/user/";
    private OrderRepo orderRepo;

    private SequenceGenerator sequenceGenerator;

    private RestTemplate restTemplate;

    public OrderService(OrderRepo orderRepo, SequenceGenerator sequenceGenerator, RestTemplate restTemplate){
        this.orderRepo = orderRepo;
        this.sequenceGenerator = sequenceGenerator;
        this.restTemplate = restTemplate;
    }
    public OrderDto saveOrder(OrderFrontDto orderFrontDto) {
        Long orderId = sequenceGenerator.generateNextOrderId();
        UserDto userDto = fetchDetailsFromUserId(orderFrontDto.getUserId());
        Order  order = new Order(orderId, orderFrontDto.getFoodItemsList(), orderFrontDto.getRestaurant(), userDto);
        orderRepo.save(order);
        return OrderMapper.INSTANCE.mapOrderToOrderDto(order);
    }

    private UserDto fetchDetailsFromUserId(Integer userId) {
        return restTemplate.getForObject( USER_SERVICE_URL + userId,UserDto.class);
    }
}

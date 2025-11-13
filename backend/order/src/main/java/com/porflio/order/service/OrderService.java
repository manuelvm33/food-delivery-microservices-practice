package com.porflio.order.service;

import com.porflio.order.dto.OrderDto;
import com.porflio.order.dto.OrderFrontDto;
import com.porflio.order.dto.UserDto;
import com.porflio.order.entity.Order;
import com.porflio.order.mapper.OrderMapper;
import com.porflio.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private SequenceGenerator sequenceGenerator;
    @Autowired
    private RestTemplate restTemplate;
    public OrderDto saveOrder(OrderFrontDto orderFrontDto) {
        Long orderId = sequenceGenerator.generateNextOrderId();
        UserDto userDto = fetchDetailsFromUserId(orderFrontDto.getUserId());
        Order  order = new Order(orderId, orderFrontDto.getFoodItemsList(), orderFrontDto.getRestaurant(), userDto);
        orderRepo.save(order);
        return OrderMapper.INSTANCE.mapOrderToOrderDto(order);
    }

    private UserDto fetchDetailsFromUserId(Integer userId) {
        return restTemplate.getForObject("http://USER-SERVICE/user/" + userId,UserDto.class);
    }
}

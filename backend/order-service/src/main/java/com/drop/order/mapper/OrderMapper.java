package com.drop.order.mapper;

import com.drop.order.dto.OrderDto;
import com.drop.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    Order mapOrderDtoToOrder(OrderDto orderDto);
    OrderDto mapOrderToOrderDto(Order order);
}
    
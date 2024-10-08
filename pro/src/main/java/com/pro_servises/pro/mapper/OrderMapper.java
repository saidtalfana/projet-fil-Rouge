package com.pro_servises.pro.mapper;

import com.pro_servises.pro.dto.OrderDto;
import com.pro_servises.pro.model.Order;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto mapToOrderDto(Order order);

    Order mapToOrder(OrderDto orderDto);



}

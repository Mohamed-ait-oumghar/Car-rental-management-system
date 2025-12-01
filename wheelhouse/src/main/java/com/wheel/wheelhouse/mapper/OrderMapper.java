package com.wheel.wheelhouse.mapper;
import com.wheel.wheelhouse.dto.OrderDto;
import com.wheel.wheelhouse.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    //Entity --> Dto
    public static OrderDto toDto(Order order) {
        if (order == null) return null;

        OrderDto orderDto = new OrderDto();
        orderDto.setDebutDate(order.getDebutDate());
        orderDto.setEndDate(order.getEndDate());
        orderDto.setDebutLocation(order.getDebutLocation());
        orderDto.setFinLocation(order.getFinLocation());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setCreationDate(order.getCreationDate());
        if (order.getClient() != null) {
            orderDto.setClientId(order.getClient().getClientId());
        }
        if (order.getCar() != null) {
            orderDto.setCarId(order.getCar().getCarId());
        }
        if (order.getUser() != null) {
            orderDto.setUserId(order.getUser().getUserId());
        }

        return orderDto;
    }

    //Dto -> Entity
    public static Order toEntity(OrderDto orderDto) {
        if(orderDto == null) return null;

        Order order = new Order();
        order.setDebutDate(orderDto.getDebutDate());
        order.setEndDate(orderDto.getEndDate());
        order.setDebutLocation(orderDto.getDebutLocation());
        order.setFinLocation(orderDto.getFinLocation());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setCreationDate(orderDto.getCreationDate());

        return order;
    }


}

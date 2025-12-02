package com.wheel.wheelhouse.service;

import com.wheel.wheelhouse.dto.OrderDto;
import com.wheel.wheelhouse.entity.Car;
import com.wheel.wheelhouse.entity.Client;
import com.wheel.wheelhouse.entity.Order;
import com.wheel.wheelhouse.entity.User;
import com.wheel.wheelhouse.mapper.OrderMapper;
import com.wheel.wheelhouse.repository.CarRepository;
import com.wheel.wheelhouse.repository.ClientRepository;
import com.wheel.wheelhouse.repository.OrderRepository;
import com.wheel.wheelhouse.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

    @Service
    public class OrderService {

        OrderRepository orderRepository;
        ClientRepository clientRepository;
        CarRepository carRepository;
        UserRepository userRepository;

        public OrderService(OrderRepository orderRepository, ClientRepository clientRepository, CarRepository carRepository, UserRepository userRepository) {
            this.orderRepository = orderRepository;
            this.clientRepository = clientRepository;
            this.carRepository = carRepository;
            this.userRepository = userRepository;
        }

        public OrderDto createOrder(OrderDto orderDto) {
            //Create order
            Order order = new Order();

            // Set relations
            Client client = clientRepository.findById(orderDto.getClientId())
                    .orElseThrow(() -> new RuntimeException("Client not found"));
            order.setClient(client);

            User user = userRepository.findById(orderDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            order.setUser(user);

            Car car = carRepository.findById(orderDto.getCarId())
                    .orElseThrow(() -> new RuntimeException("Car not found"));

            //
            List<Order> orderList = orderRepository.findByCar_CarId(car.getCarId());

            for (Order s : orderList) {
                if (orderDto.getEndDate().isBefore(s.getDebutDate()) ||
                        orderDto.getDebutDate().isAfter(s.getEndDate())) {
                }
                throw new RuntimeException("Car already rented during these dates.");
            }

            order.setDebutDate(orderDto.getDebutDate());
            order.setEndDate(orderDto.getEndDate());
            order.setDebutLocation(orderDto.getDebutLocation());
            order.setFinLocation(orderDto.getFinLocation());
            order.setTotalPrice(orderDto.getTotalPrice());
            order.setCreationDate(orderDto.getCreationDate());
            order.setCar(car);

            Order saved = orderRepository.save(order);

            return OrderMapper.toDto(saved);
        }


        //get All orders
        public List<Order> getAllOrders() {
            return orderRepository.findAll();
        }
        //get CreationDate
        public List<Order> getByCreationDate(LocalDate creationDate) {
            return orderRepository.findByCreationDate(creationDate);
        }
        //Pagination
        public Page<OrderDto> getAllOrders(Pageable pageable) {

            Page<Order> ordersPage = orderRepository.findAll(pageable);

            return ordersPage.map(OrderMapper::toDto);
        }
        //Update all attribute except clientId, Creation date
        public OrderDto updateOrder(Long orderId, OrderDto updateOrderDto) {

            Order existingOrder = orderRepository.findById(orderId)
                    .orElseThrow(() -> new RuntimeException("Order not found"));

            if (updateOrderDto.getDebutDate() != null) {
                existingOrder.setDebutDate(updateOrderDto.getDebutDate());
            }
            if (updateOrderDto.getEndDate() != null) {
                existingOrder.setEndDate(updateOrderDto.getEndDate());
            }
            if (updateOrderDto.getDebutLocation() != null) {
                existingOrder.setDebutLocation(updateOrderDto.getDebutLocation());
            }
            if (updateOrderDto.getFinLocation() != null) {
                existingOrder.setFinLocation(updateOrderDto.getFinLocation());
            }
            if (updateOrderDto.getTotalPrice() != null) {
                existingOrder.setTotalPrice(updateOrderDto.getTotalPrice());
            }

            if (updateOrderDto.getCarId() != null) {
                Car car = carRepository.findById(updateOrderDto.getCarId())
                        .orElseThrow(() -> new RuntimeException("Car not found with id: " + updateOrderDto.getCarId()));
                existingOrder.setCar(car);
            }

            if (updateOrderDto.getUserId() != null) {
                User user = userRepository.findById(updateOrderDto.getUserId())
                        .orElseThrow(() -> new RuntimeException("User not found with id: " + updateOrderDto.getUserId()));
                existingOrder.setUser(user);
            }

            Order saved = orderRepository.save(existingOrder);
            return OrderMapper.toDto(saved);
        }
        //delete order
        public void deleteOrder(Long orderId) {
            if (!orderRepository.existsById(orderId)) {
                throw new RuntimeException("Order not found with id: " + orderId);
            }
            orderRepository.deleteById(orderId);
        }


}

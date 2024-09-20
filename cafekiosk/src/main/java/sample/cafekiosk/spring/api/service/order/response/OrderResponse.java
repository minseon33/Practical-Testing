package sample.cafekiosk.spring.api.service.order.response;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import sample.cafekiosk.spring.api.service.product.ProductResponce;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderStatus;
import sample.cafekiosk.spring.domain.orderproduct.OrderProduct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderResponse {
    private Long id;
    private OrderStatus orderStatus;
    private int totalPrice;
    private LocalDateTime registeredDateTime;
    private List<ProductResponce> products;

    @Builder
    private OrderResponse(Long id, OrderStatus orderStatus, int totalPrice, LocalDateTime registeredDateTime, List<ProductResponce> products) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.registeredDateTime = registeredDateTime;
        this.products = products;
    }

    public static OrderResponse of(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .orderStatus(order.getOrderStatus())
                .totalPrice(order.getTotalPrice())
                .registeredDateTime(order.getRegisteredDateTime())
                .products(order.getOrderProducts().stream()
                        .map(orderProduct -> ProductResponce.of(orderProduct.getProduct()))
                        .collect(Collectors.toList()))
                .build();
    }

}

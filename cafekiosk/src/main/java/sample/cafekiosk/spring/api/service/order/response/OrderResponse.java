package sample.cafekiosk.spring.api.service.order.response;

import jakarta.persistence.*;
import lombok.Getter;
import sample.cafekiosk.spring.api.service.product.ProductResponce;
import sample.cafekiosk.spring.domain.order.OrderStatus;
import sample.cafekiosk.spring.domain.orderproduct.OrderProduct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderResponse {
    private Long id;
    private OrderStatus orderStatus;
    private int totalPrice;
    private LocalDateTime registeredDateTime;
    private List<ProductResponce> products;


}

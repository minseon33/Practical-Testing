package sample.cafekiosk.spring.domain.order;

import org.aspectj.weaver.ast.Or;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @DisplayName("주문 생성 시 상품 리스트에서 주문의 총 금액을 계산한다.")
    @Test
    void calculateTotalPrice() {
        //given
        List<Product> product = List.of(
                createProduct("001", 1000),
                createProduct("002", 2000)
        );
        //when
        Order order = Order.create(product,LocalDateTime.now());

        //then
        Assertions.assertThat(order.getTotalPrice()).isEqualTo(3000);
    }
    @DisplayName("주문 생성 시 주문 상태는 INIT이다.")
    @Test
    void init() {
        //given
        List<Product> product = List.of(
                createProduct("001", 1000),
                createProduct("002", 2000)
        );
        //when
        Order order = Order.create(product, LocalDateTime.now());

        //then
        Assertions.assertThat(order.getOrderStatus()).isEqualByComparingTo(OrderStatus.INIT);
    }

    @DisplayName("주문 생성 시 주문 등록 시간을 기록한다.")
    @Test
    void registeredDateTime() {
        //given
        List<Product> product = List.of(
                createProduct("001", 1000),
                createProduct("002", 2000)
        );

        LocalDateTime registeredDateTime = LocalDateTime.now();
        //when
        Order order = Order.create(product, registeredDateTime);

        //then
        Assertions.assertThat(order.getRegisteredDateTime()).isEqualTo(registeredDateTime);
    }

    private Product createProduct(String productNumber, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(ProductType.HANDMADE)
                .sellingStatus(ProductSellingStatus.SELLING)
                .name("메뉴 이름")
                .price(price)
                .build();
    }

}
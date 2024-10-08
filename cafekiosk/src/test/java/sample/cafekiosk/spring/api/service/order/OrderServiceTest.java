package sample.cafekiosk.spring.api.service.order;

import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.spring.api.controller.order.request.OrderCreateRequest;
import sample.cafekiosk.spring.api.service.order.response.OrderResponse;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@ActiveProfiles("test")
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("주문번호 리스트를 받아 주문을 생성한다.")
    @Test
    void createOrder(){
    //given
        LocalDateTime registeredDateTime = LocalDateTime.now();

        Product producr1 = createProduct(ProductType.HANDMADE,"001",1000);
        Product producr2 = createProduct(ProductType.HANDMADE,"002",3000);
        Product producr3 = createProduct(ProductType.HANDMADE,"003",5000);
        productRepository.saveAll(List.of(producr1, producr2, producr3));
        OrderCreateRequest request = OrderCreateRequest.builder()
                .productNumbers(List.of("001", "002", "003"))
                .build();
        orderService.createOrder(request, registeredDateTime);

    //when
        OrderResponse orderResponse = orderService.createOrder(request, registeredDateTime);

    //then
        Assertions.assertThat(orderResponse.getId()).isNotNull();
        Assertions.assertThat(orderResponse)
                .extracting("registeredDateTime","totalPrice")
                .contains(registeredDateTime,4000);
        Assertions.assertThat(orderResponse.getProducts()).hasSize(2)
                .extracting("productNumber","price")
                .containsExactlyInAnyOrder(
                        Tuple.tuple("001",1000),
                        Tuple.tuple("002",3000)
                ); ;

    }


    private Product createProduct(ProductType type, String productNumber, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .sellingStatus(ProductSellingStatus.SELLING)
                .name("메뉴 이름")
                .price(price)
                .build();
    }
}
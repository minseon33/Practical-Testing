package sample.cafekiosk.spring.api.service.product;

import lombok.Builder;
import lombok.Getter;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

@Getter
public class ProductResponce {
    private Long id;
    private String productNumber;
    private ProductType type;
    private ProductSellingStatus productSellingStatus;
    private String name;
    private int price;

    @Builder
    private ProductResponce(Long id, String productNumber, ProductType type, ProductSellingStatus productSellingStatus, String name, int price) {
        this.id = id;
        this.productNumber = productNumber;
        this.type = type;
        this.productSellingStatus = productSellingStatus;
        this.name = name;
        this.price = price;
    }

    public static ProductResponce of(Product product) {
        return ProductResponce.builder()
                .id(product.getId())
                .productNumber(product.getProductNumber())
                .type(product.getType())
                .productSellingStatus(product.getSellingStatus())
                .name(product.getName())
                .price(product.getPrice())
                .build();
     }

}

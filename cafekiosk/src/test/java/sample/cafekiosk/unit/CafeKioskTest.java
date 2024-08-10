package sample.cafekiosk.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;
import sample.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class CafeKioskTest {

    @DisplayName("음료추가 테스트")
    @Test
    void add_manual_test(){
    //given
    CafeKiosk cafeKiosk = new CafeKiosk();
    cafeKiosk.add(new Americano());

    //when

    //then
        System.out.println(">>> 담김 음료 수 : " + cafeKiosk.getBeverages().size());
        System.out.println(">>> 담김 음료 : " + cafeKiosk.getBeverages().get(0).getname());

    }

    @DisplayName("음료 1개를 추가하면 주문 목록에 담긴다.")
    @Test
    void add(){
    //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
    //when

    //then
        assertThat(cafeKiosk.getBeverages().size()).isEqualTo(1);
        assertThat(cafeKiosk.getBeverages().get(0).getPrice()).isEqualTo(4000);
        assertThat(cafeKiosk.getBeverages().get(0).getname()).isEqualTo("아메리카노");
    }

    @DisplayName("음료 여러잔 추가하면 주문 목록에 담긴다.")
    @Test
    void addSeveralBeverages(){
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        //when
        cafeKiosk.add(americano,2);
        //then
        assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);
    }

    @DisplayName("음료가 0잔일때 에러를 던져준다.")
    @Test
    void addZeroBeverages(){
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        //when
        //then
        assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음료는 1잔 이상 주문하실 수 있습니다.");
    }

    @DisplayName("음료삭제 테스트")
    @Test
    void remove(){
    //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);

    //when
        assertThat(cafeKiosk.getBeverages()).hasSize(1);

        cafeKiosk.getBeverages().remove(americano);
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    //then

    }



    @DisplayName("음료 전체삭제 테스트")
    @Test
    void clear(){
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
        cafeKiosk.add(new Latte());

        //when
        assertThat(cafeKiosk.getBeverages()).hasSize(2);

        cafeKiosk.clear();
        assertThat(cafeKiosk.getBeverages()).isEmpty();
        //then

    }

    @DisplayName("주문 목록에 담긴 상품들의 총 금액을 계산할 수 있다.")
    @Test
    void calulateTotalPrice(){
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();

        cafeKiosk.add(americano);
        cafeKiosk.add(latte);
        //when
        int totalPrice = cafeKiosk.calculateTotalPrice();

        //then
        assertThat(totalPrice).isEqualTo(8500);

    }


    @DisplayName("주문생성")
    @Test
    void createOrder(){
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        //when
        cafeKiosk.add(americano);

        Order order = cafeKiosk.createOrder();
        //then
        assertThat(order.getBeverageList()).hasSize(1);
        assertThat(order.getBeverageList().get(0).getname()).isEqualTo("아메리카노");
    }

    @DisplayName("영업시간 내 음료주문하기")
    @Test
    void createOrderWithCurrentTime(){
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        //when
        cafeKiosk.add(americano);

        Order order = cafeKiosk.createOrder(LocalDateTime.of(2024, 8, 7, 10, 0));
        //then
        assertThat(order.getBeverageList()).hasSize(1);
        assertThat(order.getBeverageList().get(0).getname()).isEqualTo("아메리카노");
    }

    @DisplayName("영업시간 외 음료주문하기")
    @Test
    void createOrderOutsideOpenTime(){
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        //when
        cafeKiosk.add(americano);

        //then
        assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(2024, 8, 7, 23, 0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문 시간이 아닙니다. 관리자에게 문의하세요");
    }



}
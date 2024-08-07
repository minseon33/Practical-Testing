package sample.cafekiosk.unit.beverage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class AmericanoTest {

    @DisplayName("아메리카노 이름이 잘 나오는가")
    @Test
    void getName(){
    //given
        Americano americano = new Americano();
    //when

    //then
        assertEquals(americano.getname(), "아메리카노");
        assertThat(americano.getname()).isEqualTo("아메리카노");
    }

    @DisplayName("가격정보를 바르게 가져오는가")
    @Test
    void getPrice(){
    //given
        Americano americano = new Americano();
    //when

    //then
        assertThat(americano.getPrice()).isEqualTo(4000);

    }

}
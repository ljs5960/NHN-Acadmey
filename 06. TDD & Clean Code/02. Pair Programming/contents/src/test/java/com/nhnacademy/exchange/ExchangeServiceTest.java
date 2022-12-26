package com.nhnacademy.exchange;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ExchangeServiceTest{
    ExchangeService exchangeService;

    @BeforeEach
    void setUp(){
        exchangeService = new ExchangeService();
    }

    /*
    환욜이 1달러당 1000원 확인
     */
    @Test
    void check_ExchangeRate_validate(){
        Money money1 = new Money(1000.0, "won");

        exchangeService.exchange(money1);
        assertThat(money1.getAmount()).isEqualTo(1);
        assertThat(money1.getCurrency()).isEqualTo("dollar");

        exchangeService.exchange(money1);
        assertThat(money1.getAmount()).isEqualTo(1000);
        assertThat(money1.getCurrency()).isEqualTo("won");
    }

    /*
    달러 -> 원화 환전 후 1의자리 반올림
     */
    @Test
    void check_dollarToWonExchange_round(){
        Money money = new Money(0.006, "dollar");

        exchangeService.exchange(money);
        assertThat(money.getAmount()).isEqualTo(10);
        assertThat(money.getCurrency()).isEqualTo("won");

        money = new Money(0.001, "dollar");

        exchangeService.exchange(money);
        assertThat(money.getAmount()).isEqualTo(0);
        assertThat(money.getCurrency()).isEqualTo("won");
    }

    /*
    원화 -> 달러 환전 후 소숫점 둘째자리 반올림
     */
    @Test
    void check_wonToDollarExchange_round(){
        Money money = new Money(6, "won");

        exchangeService.exchange(money);
        assertThat(money.getAmount()).isEqualTo(0.01);
        assertThat(money.getCurrency()).isEqualTo("dollar");

        money = new Money(4, "won");
        exchangeService.exchange(money);
        assertThat(money.getAmount()).isEqualTo(0.00);
        assertThat(money.getCurrency()).isEqualTo("dollar");
    }
}
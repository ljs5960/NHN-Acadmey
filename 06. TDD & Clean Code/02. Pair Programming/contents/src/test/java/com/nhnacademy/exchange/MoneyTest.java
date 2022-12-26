package com.nhnacademy.exchange;

import com.nhnacademy.exchange.exception.IncorrectCurrencyException;
import com.nhnacademy.exchange.exception.NegativeAmountException;
import com.nhnacademy.exchange.exception.NoMatchCurrencyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.*;

class MoneyTest{
    // SUT
    ExchangeService service;

    @BeforeEach
    void setUp(){
        service = new ExchangeService();
        Money money = mock(Money.class);
    }

    /*
    원화끼리 더했을 때 값이 일치하는 경우
     */
    @Test
    void check_addAmount_equal_won(){
        Money money1 = new Money(1000.0, "won");
        Money money2 = new Money(1000.0, "won");

        money1.addAmount(money2);
        assertThat(money1.getAmount()).isEqualTo(2000.0);
    }

    /*
    원화끼리 뺐을 때 값이 일치하는 경우
     */
    @Test
    void check_subAmount_equal_won(){
        Money money1 = new Money(1000.0, "won");
        Money money2 = new Money(500.0, "won");

        money1.subAmount(money2);
        assertThat(money1.getAmount()).isEqualTo(500);
    }

    /*
    달러끼리 더했을 때 값이 일치하는 경우
     */
    @Test
    void check_addAmount_equal_dollar(){
        Money money1 = new Money(1000.0, "dollar");
        Money money2 = new Money(1000.0, "dollar");

        money1.addAmount(money2);
        assertThat(money1.getAmount()).isEqualTo(2000.0);
    }

    /*
    달러끼리 뺐을 때 값이 일치하는 경우
   */
    @Test
    void check_subAmount_equal_dollar(){
        Money money1 = new Money(1000.0, "dollar");
        Money money2 = new Money(500.0, "dollar");

        money1.subAmount(money2);
        assertThat(money1.getAmount()).isEqualTo(500);
    }

    /*
    다른 통화의 돈을 더했을 때 NoMatchCurrencyException 발생
     */
    @Test
    void check_addAmount_when_NotEqualCurrency_then_throwNoMatchCurrencyException(){
        Money money1 = new Money(1000.0, "won");
        Money money2 = new Money(1000.0, "dollar");

        assertThatThrownBy(() -> money1.addAmount(money2))
                .isInstanceOf(NoMatchCurrencyException.class)
                .hasMessageContainingAll("No Match Currency:", money1.getCurrency(), money2.getCurrency());
    }

    /*
    다른 통화의 돈을 뺏을 때 NoMatchCurrencyException 발생
    */
    @Test
    void check_subAmount_when_NotEqualCurrency_then_throwNoMatchCurrencyException(){
        Money money1 = new Money(1000.0, "won");
        Money money2 = new Money(1000.0, "dollar");

        assertThatThrownBy(() -> money1.subAmount(money2))
                .isInstanceOf(NoMatchCurrencyException.class)
                .hasMessageContainingAll("No Match Currency:", money1.getCurrency(), money2.getCurrency());
    }

    /*
    돈을 뺐을 때 음수가 발생하는 경우 예외 발생
     */
    @Test
    void check_subAmount_when_Negative_then_throwNegativeAmountException(){
        Money money1 = new Money(1000.0, "won");
        Money money2 = new Money(1100.0, "won");

        assertThatThrownBy(() -> money1.subAmount(money2))
                .isInstanceOf(NegativeAmountException.class)
                .hasMessageContainingAll("Amount is Negative Number: ", Double.toString(money1.getAmount()-money2.getAmount()));
    }

    /*
    두 돈의 amount 비교 동등성 검사
    */
    @Test
    void check_Equivalence_OtherMoneyAmount(){
        Money money1 = new Money(1000.0, "won");
        Money money2 = new Money(1000.0, "won");

        assertThat(money1.getAmount()).isEqualTo(money2.getAmount());
        assertThat(money1.getCurrency()).isEqualTo(money2.getCurrency());
    }

    /*
    잘못된 통화를 입력했을 경우
     */
    @Test
    void check_CurrencyName_when_IsNotValueOf_then_throwIncorrectCurrencyException(){
        assertThatThrownBy(() -> new Money(1000.0, "wonn"))
                .isInstanceOf(IncorrectCurrencyException.class)
                .hasMessageContainingAll("Incorrect Currency: ", "wonn");
    }

    /*
    달러의 경우 소숫점 둘째자리까지 표현
     */
    @Test
    void check_roundAmount(){
        Money money1 = new Money(10.00, "dollar");
        Money money2 = new Money(9.001, "dollar");

        money1.subAmount(money2);
        assertThat(money1.getAmount()).isEqualTo(1);
    }
}
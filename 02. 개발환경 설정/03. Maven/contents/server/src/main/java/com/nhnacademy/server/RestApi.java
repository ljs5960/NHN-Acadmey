package com.nhnacademy.server;

import com.nhnacademy.domain.Account;
import com.nhnacademy.domain.Money;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

public class RestApi {
    public boolean postAmount(Action action, Long accountNumber, BigDecimal amount) {
        System.out.println(
            "[RestAPI] post amount: " + action + ", " + accountNumber + ", " + amount);

        return new Account().deposit(new Money(amount, Currency.getInstance(Locale.US)));
    }
}
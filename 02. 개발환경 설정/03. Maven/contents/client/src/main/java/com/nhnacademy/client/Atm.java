package com.nhnacademy.client;

import static com.nhnacademy.server.Action.DEPOSIT;
import static com.nhnacademy.server.Action.WITHDRAW;

import com.nhnacademy.server.RestApi;
import java.math.BigDecimal;

public class Atm {
    public boolean deposit(BigDecimal amount, Long accountNumber) {
        System.out.println("[ATM] Amount: " + amount + ", AccountNumber: " + accountNumber);

        return new RestApi().postAmount(DEPOSIT, accountNumber, amount);
    }

    public boolean withDraw(BigDecimal amount, Long accountNumber) {
        System.out.println("withDraw: " + accountNumber);

        return new RestApi().postAmount(WITHDRAW, accountNumber, amount);
    }
}

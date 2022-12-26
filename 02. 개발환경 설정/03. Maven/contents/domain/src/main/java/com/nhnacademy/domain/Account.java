package com.nhnacademy.domain;

public class Account {
    public boolean deposit(Money money) {
        System.out.println("[Account] Account Deposit: " + money.toString());
        return true;
    }

    public boolean withdraw(Money money) {
        System.out.println("Withdraw: " + money.toString());
        return true;
    }
}
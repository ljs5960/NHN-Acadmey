package com.nhnacademy.exchange;

import com.nhnacademy.exchange.exception.IncorrectCurrencyException;
import com.nhnacademy.exchange.exception.NegativeAmountException;
import com.nhnacademy.exchange.exception.NoMatchCurrencyException;
import java.util.Objects;

public class Money{
    private double amount;
    private String currency;

    public Money(double amount, String currency){
        try{
            Currency.valueOf(currency);
        }catch(IllegalArgumentException e){
            throw new IncorrectCurrencyException(currency);
        }
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount(){
        return this.amount;
    }

    public String getCurrency(){
        return currency;
    }

    public void addAmount(Money money){
        currencyCheck(money);
        this.amount += money.getAmount();
        roundAmount();
    }

    public void subAmount(Money money){
        currencyCheck(money);
        if(this.amount - money.getAmount() < 0){
            throw new NegativeAmountException(this.amount-money.getAmount());
        }
        this.amount -= money.getAmount();
        roundAmount();
    }

    private void roundAmount(){
        this.amount = Math.round(this.amount * 100)/100;
    }
    private void currencyCheck(Money money){
        if(!Objects.equals(this.currency, money.getCurrency())){
            throw new NoMatchCurrencyException(this.currency, money.getCurrency());
        }
    }

    public void setCurrency(String currency){
        this.currency = currency;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }
}

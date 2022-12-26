package com.nhnacademy.exchange.exception;

public class IncorrectCurrencyException extends RuntimeException{
    public IncorrectCurrencyException(String currency){
        super("Incorrect Currency: "+ currency);
    }
}

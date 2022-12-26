package com.nhnacademy.exchange.exception;

public class NoMatchCurrencyException extends RuntimeException{
    public NoMatchCurrencyException(String currency1, String currency2){
        super("No Match Currency: " +  currency1 +  ", " +  currency2);
    }
}

package com.nhnacademy.exchange.exception;

public class NegativeAmountException extends RuntimeException{
    public NegativeAmountException(double amount){
        super("Amount is Negative Number: " + amount);
    }
}

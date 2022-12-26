package com.nhnacademy.gw1.parking.exception;

import com.nhnacademy.gw1.parking.car.CarType;

public class HeavyCarParkException extends RuntimeException{
    public HeavyCarParkException(CarType carType) {
        super("HeavyCar can't park : " + carType);
    }
}

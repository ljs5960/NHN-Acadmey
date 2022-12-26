package com.nhnacademy.gw1.parking.car;

public class LightCar extends Car {
    public LightCar(String carNumber, boolean isPaycoUser) {
        super(carNumber, CarType.LIGHT_CAR, isPaycoUser);
    }
}

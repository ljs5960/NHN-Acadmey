package com.nhnacademy.gw1.parking.car;

public class NormalCar extends Car {
    public NormalCar(String carNumber, boolean isPaycoUser) {
        super(carNumber, CarType.NORMAL_CAR, isPaycoUser);
    }
}

package com.nhnacademy.gw1.parking.car;

import com.nhnacademy.gw1.parking.exception.HeavyCarParkException;

public class HeavyCar extends Car {
    public HeavyCar(String carNumber, boolean isPaycoUser) {
        super(carNumber, CarType.HEAVY_CAR, isPaycoUser);
    }

    /*
    대형차 주차 시 HeavyCarParkException 발생
    */
    @Override
    public String park(String parkingSpace) {
        throw new HeavyCarParkException(this.getCarType());
    }
}

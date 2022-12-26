package com.nhnacademy.gw1.parking.parkinglot;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.car.CarType;
import java.math.BigDecimal;
import java.util.Objects;

public class ParkingLotDiscountSystem {
    /*
    경차 주차비 50% 할인
     */
    public static BigDecimal ifSmallCarDiscount50Percent(Car car, BigDecimal parkedFee) {
        double discountPercent = 0.5;

        if (Objects.equals(car.getCarType(), CarType.LIGHT_CAR)) {
            parkedFee = parkedFee.multiply(BigDecimal.valueOf(1 - discountPercent));
        }
        return parkedFee;
    }

    /*
    Payco 회원 10% 할인
     */
    public static BigDecimal ifPaycoUserDiscount10Percent(Car car, BigDecimal parkedFee) {
        double discountPercent = 0.1;

        if (Objects.equals(car.isPacoUser(), true)) {
            parkedFee = parkedFee.multiply(BigDecimal.valueOf(1 - discountPercent));
        }
        return parkedFee;
    }

    /*
    시간 할인권 존재시 해당 시간만큼 차감
     */
    public static int discountTimeByTicketTime(Car car, int parkedTime) {
        int discountTime = car.getParkTicket();

        parkedTime -= discountTime;

        return parkedTime;
    }
}

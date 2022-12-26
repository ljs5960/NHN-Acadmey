package com.nhnacademy.gw1.parking.parkinglot;

import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.car.LightCar;
import com.nhnacademy.gw1.parking.car.NormalCar;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParkingLotDiscountSystemTest {
    private final String CAR_NUMBER = "123가 1234";
    private final int PARKED_TIME_60 = 60;

    Car normalCar = new NormalCar(CAR_NUMBER, false);
    ParkingLot parkingLot = new ParkingLot();

    /*
    경차 주차시 일반 차 대비 주차비 50% 감면
     */
    @DisplayName("경차 요금 감면 테스트")
    @Test
    void check_parkingFeeDiscount_ifLightCar() {
        double discountPercent = 0.5;
        Car lightCar = new LightCar(CAR_NUMBER, false);

        BigDecimal lightCarParkingFee = parkingLot.charge(lightCar, PARKED_TIME_60);
        BigDecimal normalCarParkingFee = parkingLot.charge(normalCar, PARKED_TIME_60);

        assertEquals(normalCarParkingFee.multiply(BigDecimal.valueOf(1 - discountPercent)), lightCarParkingFee);
    }

    /*
    Payco 회원시 일반회원 대비 10프로 할인
     */
    @DisplayName("Payco 회원 10% 할인")
    @Test
    void check_ifPaycoUser_thenDiscount10percent() {
        double discountPercent = 0.1;

        Car paycoCar = new NormalCar(CAR_NUMBER, true);

        BigDecimal paycoCarParkingFee = parkingLot.charge(paycoCar, PARKED_TIME_60);
        BigDecimal notPaycoCarParkingFee = parkingLot.charge(normalCar, PARKED_TIME_60);

        assertEquals(notPaycoCarParkingFee.multiply(BigDecimal.valueOf(1 - discountPercent)), paycoCarParkingFee);
    }

    @DisplayName("시간권 지불 후 금액 테스트")
    @Test
    void check_parkingFee_usingParkTicket() {
        Car car1 = new NormalCar(CAR_NUMBER, false);
        Car car2 = new NormalCar(CAR_NUMBER, false);

        car1.setParkTicket(60);
        car2.setParkTicket(120);

        BigDecimal parkingFeeUsingTicket1 = parkingLot.charge(car1, 59);
        BigDecimal parkingFeeUsingTicket2 = parkingLot.charge(car2, 180);

        assertAll(
            () -> assertEquals(parkingLot.charge(normalCar, 0), parkingFeeUsingTicket1),
            () -> assertEquals(parkingLot.charge(normalCar, 60), parkingFeeUsingTicket2)
        );
    }
}
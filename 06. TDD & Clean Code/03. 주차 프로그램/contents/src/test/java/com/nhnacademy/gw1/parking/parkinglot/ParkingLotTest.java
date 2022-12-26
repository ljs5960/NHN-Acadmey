package com.nhnacademy.gw1.parking.parkinglot;

import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.gw1.parking.car.Car;
import com.nhnacademy.gw1.parking.car.NormalCar;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParkingLotTest {
    private final String CAR_NUMBER = "123가 1234";
    private final String parkingSpaceNumber = "A-1";
    private final int PARKED_TIME_60 = 60;

    Car normalCar = new NormalCar(CAR_NUMBER, false);
    ParkingLot parkingLot = new ParkingLot();

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot();
    }

    @DisplayName("번호판 인식 테스트")
    @Test
    void check_carNumberScanner() {
        String scannedNumber = parkingLot.scan(normalCar);

        assertEquals(CAR_NUMBER, scannedNumber);
    }

    @DisplayName("올바른 주차구역 테스트")
    @Test
    void check_parked_atParkingSpace() {
        String parkedSpaceNumber = normalCar.park(parkingSpaceNumber);
        parkingLot.setParkingSpaceNumber(normalCar, parkedSpaceNumber);

        assertEquals(parkingLot.getParkingSpaceNumber(normalCar), parkedSpaceNumber);
    }

    /*
    최초 30분, 60분 Boundary Test
    일일 최대금액 15,000원 Boundary Test (340분)
     */
    @DisplayName("변경된 요금표에 대한 요금 테스트")
    @Test
    void check_parkingFee_withChangedFee() {
        assertEquals(new BigDecimal(0), parkingLot.charge(normalCar, 30));
        assertEquals(new BigDecimal(1_000), parkingLot.charge(normalCar, 31));
        assertEquals(new BigDecimal(1_000), parkingLot.charge(normalCar, 60));
        assertEquals(new BigDecimal(1_500), parkingLot.charge(normalCar, 61));
        assertEquals(new BigDecimal(15_000), parkingLot.charge(normalCar, 340));
        assertEquals(new BigDecimal(15_000), parkingLot.charge(normalCar, 341));
    }
}
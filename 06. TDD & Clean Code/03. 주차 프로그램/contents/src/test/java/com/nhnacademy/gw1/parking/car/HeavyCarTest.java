package com.nhnacademy.gw1.parking.car;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.nhnacademy.gw1.parking.exception.HeavyCarParkException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HeavyCarTest {
    private final String CAR_NUMBER = "123가 1234";
    private final String parkingSpaceNumber = "A-1";

    @DisplayName("대형차 주차 불가능 테스트")
    @Test
    void check_cantPark_ifHeavyCar() {
        Car heavyCar = new HeavyCar(CAR_NUMBER, false);

        assertThatThrownBy(() -> heavyCar.park(parkingSpaceNumber))
            .isInstanceOf(HeavyCarParkException.class)
            .hasMessageContaining("HeavyCar can't park : ", heavyCar.getCarType());
    }
}
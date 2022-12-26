package com.nhnacademy.gw1.parking.parkinglot;

import static com.nhnacademy.gw1.parking.parkinglot.ParkingLotDiscountSystem.discountTimeByTicketTime;
import static com.nhnacademy.gw1.parking.parkinglot.ParkingLotDiscountSystem.ifPaycoUserDiscount10Percent;
import static com.nhnacademy.gw1.parking.parkinglot.ParkingLotDiscountSystem.ifSmallCarDiscount50Percent;

import com.nhnacademy.gw1.parking.car.Car;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<String, String> parkingSpaces = new HashMap<>();

    /*
    차가 주차된 주차구역 번호 Return
     */
    public String getParkingSpaceNumber(Car car) {
        String carNumber = car.getCarNumber();

        return parkingSpaces.get(carNumber);
    }

    public void setParkingSpaceNumber(Car car, String parkingSpaceNumber) {
        parkingSpaces.put(car.getCarNumber(), parkingSpaceNumber);
    }

    /*
    차 번호 스캔
     */
    public String scan(Car car) {
        return  car.getCarNumber();
    }

    /*
    주차비 청구
     */
    public BigDecimal charge(Car car, int parkedTime) {
        BigDecimal parkedFee;

        parkedTime = discountTimeByTicketTime(car, parkedTime);

        if (parkedTime <= 30) { // 최초 30분 : 무료
            parkedFee = new BigDecimal(0);
        } else if (parkedTime <= 60) {  // 최초 30분초과 ~ 1시간 : 1,000원
            parkedFee = new BigDecimal(1_000);
        } else if (parkedTime <= 340 ) {    // 이후 10분당 추가요금 500원 (올림을 위해  +9)
            parkedFee = new BigDecimal(1_000 + ((parkedTime - 60 + 9) / 10) * 500);
        } else {    // 일일 최대 금액 15,000원 (340분)
            parkedFee = new BigDecimal(15_000);
        }

        parkedFee = ifSmallCarDiscount50Percent(car, parkedFee);
        parkedFee = ifPaycoUserDiscount10Percent(car, parkedFee);

        return parkedFee;
    }
}

package com.nhnacademy.gw1.parking.car;

public abstract class Car {
    private String carNumber;
    private CarType carType;
    private boolean isPacoUser;
    private int parkTicket;

    public Car(String carNumber, CarType carType, boolean isPacoUser) {
        this.carNumber = carNumber;
        this.carType = carType;
        this.isPacoUser = isPacoUser;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public CarType getCarType() {
        return carType;
    }

    public boolean isPacoUser() {
        return isPacoUser;
    }

    public int getParkTicket() {
        return parkTicket;
    }

    public void setParkTicket(int parkTicket) {
        this.parkTicket = parkTicket;
    }

    public String park(String parkingSpace) {
        return parkingSpace;
    }
}

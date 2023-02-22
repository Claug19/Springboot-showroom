package com.showroom.springboot.model;

public class ReservedCar {
    private int clientId;
    private int carId;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    @Override
    public String toString() {
        return "ReservedCar{" +
                "clientId='" + clientId + '\'' +
                ", carId='" + carId + '\'' +
                '}';
    }
}

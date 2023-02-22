package com.showroom.springboot.model;

public class SearchCriteria {

    //@NotBlank(message = "username can't be empty!")
    //users
    String username;

    // cars
    String carBrand;
    String carBrandStartWith;
    String model;
    int year;
    int price;

    String reservedCar;
    String deletedReservedCar;

    // clients

    // discounts


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand){
        this.carBrand = carBrand;
    }

    public String getCarBrandStartWith() {
        return carBrandStartWith;
    }

    public void setCarBrandStartWith(String carBrandStartWith) {
        this.carBrandStartWith = carBrandStartWith;
    }

    public String getModel(){
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public int getYear(){
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public int getPrice(){
        return price;
    }

    public void setModel(int price)
    {
        this.price = price;
    }

    public String getReservedCar() {
        return reservedCar;
    }

    public void setReservedCar(String reservedCar) {
        this.reservedCar = reservedCar;
    }

    public String getDeletedReservedCar() {
        return deletedReservedCar;
    }

    public void setDeletedReservedCar(String setDeletedReservedCar) {
        this.deletedReservedCar = deletedReservedCar;
    }
}

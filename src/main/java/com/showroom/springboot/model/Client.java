package com.showroom.springboot.model;

import java.util.List;

public class Client {
    private int id;
    private int familyId;
    private String gender;
    private String firstName;
    private String lastName;
    private String preferredCar;
    private List<String> filters;

    public int getId() {
        return id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getFamilyId()
    {
        return familyId;
    }

    public void setFamilyId(int i) {
        this.familyId = i;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPreferredCar() {
        return preferredCar;
    }

    public void setPreferredCar(String preferredCar)
    {
        this.preferredCar = preferredCar;
    }

    public List<String> getFilters() {
        return filters;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }

    public void addFilter(String filter) {
        this.filters.add(filter);
    }
}
package com.showroom.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.showroom.springboot.model.*;
import com.showroom.springboot.services.CarService;
import com.showroom.springboot.services.ClientService;
import com.showroom.springboot.services.DiscountService;
import com.showroom.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SearchController {

    CarService carService;
    ClientService clientService;
    DiscountService discountService;
    UserService userService;

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired
    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    // *********** user requests ***********
    @PostMapping("/api/search")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody SearchCriteria search, Errors errors) {

        AjaxResponseBody<User> result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);

        }

        List<User> users = userService.findByUserNameOrEmail(search.getUsername());
        if (users.isEmpty()) {
            result.setMsg("no user found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(users);

        return ResponseEntity.ok(result);

    }


    // *********** car requests ***********
    @PostMapping("/api/search-car")
    public ResponseEntity<?> getSearchResultViaAjax1Response(@Valid @RequestBody SearchCriteria search, Errors errors) {

        AjaxResponseBody<Car> result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);

        }

        List<Car> cars = carService.findCarsByBrand(search.getCarBrand());
        if (cars.isEmpty()) {
            result.setMsg("No cars found!");
        } else {
            result.setMsg("Success");
        }
        result.setResult(cars);

        return ResponseEntity.ok(result);
    }


    @PostMapping("/api/search-car-that-start-with")
    public ResponseEntity<?> getSearchResultViaAjax2Response(@Valid @RequestBody SearchCriteria search, Errors errors) {

        AjaxResponseBody<CarCompact> result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }

        List<CarCompact> cars = carService.findCarsThatStartWith(search.getCarBrandStartWith());
        if (cars.isEmpty()) {
            result.setMsg("No cars found!");
        } else
            result.setMsg("Success");
        result.setResult(cars);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/search-car-model")
    public ResponseEntity<?> getSearchResultViaAjax3Response(@Valid @RequestBody SearchCriteria search, Errors errors) {

        AjaxResponseBody<Car> result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }

        List<Car> cars = carService.findCarsByModel(search.getModel());
        if (cars.isEmpty()) {
            result.setMsg("No cars found!");
        } else
            result.setMsg("Success");
        result.setResult(cars);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/search-car-model-year")
    public ResponseEntity<?> getSearchResultViaAjax4Response(@Valid @RequestBody SearchCriteria search, Errors errors) {

        AjaxResponseBody<CarCompact> result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }

        List<CarCompact> cars = carService.findCarsByModelAndYear(search.getModel(), search.getYear());
        if (cars.isEmpty()) {
            result.setMsg("No cars found!");
        } else
            result.setMsg("Success");
        result.setResult(cars);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/search-car-model-price")
    public ResponseEntity<?> getSearchResultViaAjax5Response(@Valid @RequestBody SearchCriteria search, Errors errors) {

        AjaxResponseBody<CarCompact> result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }

        List<CarCompact> cars = carService.findCarsByModelAndPrice(search.getModel(), search.getPrice());
        if (cars.isEmpty()) {
            result.setMsg("No cars found!");
        } else
            result.setMsg("Success");
        result.setResult(cars);

        return ResponseEntity.ok(result);
    }

    //e.g {"clientId": "1","carId": "2"}
    // *********** reserved car requests ***********
    @PostMapping("/api/add-reserved-car")
    public ResponseEntity<?> getSearchResultViaAjax6Response(@Valid @RequestBody SearchCriteria search, Errors errors)  {

        AjaxResponseBody<ReservedCar> result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ReservedCar reservedCar = null;
        try {
            reservedCar = objectMapper.readValue(search.getReservedCar(), ReservedCar.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        carService.addReservedCar(reservedCar);

        List<ReservedCar> reservedCars = new ArrayList<>();
        reservedCars.add(reservedCar);

        if (reservedCars.isEmpty()) {
            result.setMsg("No reserved cars found!");
        } else {
            result.setMsg("Success");
        }
        result.setResult(reservedCars.stream().collect(Collectors.toList()));

        return ResponseEntity.ok(result);

    }

    @PostMapping("/api/delete-reserved-car")
    public ResponseEntity<?> getSearchResultViaAjax7Response(@Valid @RequestBody SearchCriteria search, Errors errors)  {

        AjaxResponseBody<ReservedCar> result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ReservedCar reservedCar = null;
        try {
            reservedCar = objectMapper.readValue(search.getDeletedReservedCar(), ReservedCar.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        carService.deleteReservedCar(reservedCar);

        List<ReservedCar> reservedCars = new ArrayList<>();

        if (reservedCars.isEmpty()) {
            result.setMsg("Success!");
        } else {
            result.setMsg("The reserved car was not deleted");
        }

        reservedCars.remove(reservedCars);


        result.setResult(reservedCars.stream().collect(Collectors.toList()));

        return ResponseEntity.ok(result);

    }

    // *********** client requests ***********
    @PostMapping("/api/search-client")
    public ResponseEntity<?> getSearchResultViaAjax8Response(@Valid @RequestBody SearchCriteria search, Errors errors) {

        AjaxResponseBody<Client> result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);

        }

        List<Client> clients = clientService.getClientList();
        if (clients.isEmpty()) {
            result.setMsg("No clients found!");
        } else {
            result.setMsg("Success");
        }
        result.setResult(clients);

        return ResponseEntity.ok(result);
    }

    // *********** discount requests ***********
    @PostMapping("/api/search-discount")
    public ResponseEntity<?> getSearchResultViaAjax9Response(@Valid @RequestBody SearchCriteria search, Errors errors) {

        AjaxResponseBody<Discount> result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);

        }

        List<Discount> discounts = discountService.getAllDiscounts();
        if (discounts.isEmpty()) {
            result.setMsg("No discounts found!");
        } else {
            result.setMsg("Success");
        }
        result.setResult(discounts);

        return ResponseEntity.ok(result);
    }

}

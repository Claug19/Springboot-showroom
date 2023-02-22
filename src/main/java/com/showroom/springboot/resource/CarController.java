package com.showroom.springboot.resource;

import com.showroom.springboot.model.Car;
import com.showroom.springboot.model.*;
import com.showroom.springboot.services.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/car")
@Api(value = "Car Resource", description = "shows cars info")
public class CarController {

    @Autowired
    CarService carService;

    @ApiOperation(value = "Returns car list")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 100, message = "100 is the message"),
                    @ApiResponse(code = 200, message = "Successful request")
            }
    )
    @GetMapping("/cars")
    public List<Car> getCars() {
        return carService.getCarList();
    }

    @ApiOperation(value = "Returns cars for which car brand starts with specified word")
    @GetMapping("/carsBrandStartWith/{word}")
    public List<CarCompact> findCarsThatStartWith(@PathVariable("word") final String word) {
        return carService.findCarsThatStartWith(word);
    }

    @ApiOperation(value = "Returns cars by given model")
    @GetMapping("/carsByModel/{model}")
    public List<Car> findCarsByModel(@PathVariable("model") final String model) {
        return carService.findCarsByModel(model);
    }

    @ApiOperation(value = "Returns cars by model and year")
    @GetMapping("/carsByModelAndYear/{authorName}/{year}")
    public List<CarCompact> findCarsByModelAndYear(
            @PathVariable("model") final String model,
            @PathVariable("year") final int year) {
        return carService.findCarsByModelAndYear(model, year);
    }

    @ApiOperation(value = "Returns car by model and price")
    @GetMapping("/carsByModelAndPrice/{authorName}/{price}")
    public List<CarCompact> findCarsByModelAndPrice(
            @PathVariable("model") final String model,
            @PathVariable("price") final int price) {
        return carService.findCarsByModelAndPrice(model, price);
    }

    // reserved cars
    @ApiOperation(value = "Returns all reserved cars")
    @GetMapping("/reservedCars")
    public List<ReservedCar> getAllReservedCars() {
        return carService.getAllReservedCars();
    }

    @ApiOperation(value = "Reserve car")
    @PostMapping("/add-reservedCar")
    public ReservedCar addReservedCar(@RequestBody ReservedCar car) {
        carService.addReservedCar(car);
        return car;
    }

    @ApiOperation(value = "Delete reserved car")
    @DeleteMapping("/delete-reservedCar")
    public void deleteReservedCar(@RequestBody ReservedCar car) {
        carService.deleteReservedCar(car);
    }

    @ApiOperation(value = "Delete reserved cards by clientId")
    @DeleteMapping("/delete/{id}")
    public void deleteReservedById(@PathVariable("id") final String id) {
        carService.delete(id);
    }

    // other
    @ApiOperation(value = "Create a new care entry")
    @PostMapping("/addCar")
    public Car create(@RequestBody Car car) {
        carService.addCar(car);
        return car;
    }
}
package com.trimblecars.controller;

import com.trimblecars.model.Car;
import com.trimblecars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/register/{ownerId}")
    public Car register(@RequestBody Car car, @PathVariable Long ownerId)
    {
        return carService.registerCar(car,ownerId);
    }

    @GetMapping
    public List<Car> getAll()
    {
        return carService.getAllCars();
    }

    @GetMapping("/owner/{ownerId}")
    public List<Car> getByOwner(@PathVariable Long ownerId)
    {
        return carService.getCarsByOwner(ownerId);
    }
}

package com.trimblecars.service;

import com.trimblecars.model.Car;
import com.trimblecars.model.CarStatus;
import com.trimblecars.model.User;
import com.trimblecars.repository.CarRepository;
import com.trimblecars.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserRepository userRepository;

    public Car registerCar(Car car, Long ownerId)
    {
        User owner=userRepository.findById(ownerId).orElseThrow();
        car.setOwner(owner);
        car.setStatus(CarStatus.IDLE);
        return carRepository.save(car);

    }

    public List<Car> getCarsByOwner(Long ownerId) {
        return carRepository.findByOwnerId(ownerId);
    }

    public List<Car> getAllCars()
    {
        return carRepository.findAll();
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
    }

    public Car addCar(Car car) {
        // Save to repository (you may have more logic here)
        return carRepository.save(car);
    }


}

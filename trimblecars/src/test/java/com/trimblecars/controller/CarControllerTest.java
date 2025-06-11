package com.trimblecars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trimblecars.model.Car;
import com.trimblecars.model.CarStatus;
import com.trimblecars.repository.CarRepository;
import com.trimblecars.repository.LeaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class CarControllerTest {

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private CarRepository carRepository;

    @BeforeEach
    public void setups() {
        leaseRepository.deleteAll(); // must come before
        carRepository.deleteAll();   // to prevent FK violations
    }


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;



    private Car car;

    @BeforeEach
    void setup() {
        carRepository.deleteAll(); // Clean slate for each test

        car = new Car();
        car.setModel("Swift");
        car.setRegistrationNo("TN01AB1234");
        car.setStatus(CarStatus.IDLE);
        carRepository.save(car);
    }

    @Test
    void testGetAllCars() throws Exception {
        mockMvc.perform(get("/api/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].model", is("Swift")))
                .andExpect(jsonPath("$[0].registrationNo", is("TN01AB1234")));
    }

    @Test
    void testAddCar() throws Exception {
        Car newCar = new Car();
        newCar.setModel("Innova");
        newCar.setRegistrationNo("TN02XY9999");
        newCar.setStatus(CarStatus.IDLE);

        mockMvc.perform(post("/api/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCar)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model", is("Innova")))
                .andExpect(jsonPath("$.registrationNo", is("TN02XY9999")));
    }

    @Test
    void testUpdateCar() throws Exception {
        car.setModel("Updated Swift");
        mockMvc.perform(put("/api/cars/" + car.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model", is("Updated Swift")));
    }
    @Test
    void testDeleteCar() throws Exception {
        mockMvc.perform(delete("/api/cars/" + car.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/cars/" + car.getId()))
                .andExpect(status().isNotFound()); // Optional: If your app returns 404
    }


}

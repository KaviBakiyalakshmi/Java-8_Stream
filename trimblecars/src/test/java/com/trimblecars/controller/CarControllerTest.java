package com.trimblecars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trimblecars.model.Car;
import com.trimblecars.model.CarStatus;
import com.trimblecars.model.User;
import com.trimblecars.repository.CarRepository;
import com.trimblecars.repository.LeaseRepository;
import com.trimblecars.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Car car;
    private User testUser;

    @BeforeEach
    void setup() {
        leaseRepository.deleteAll();
        carRepository.deleteAll();
        userRepository.deleteAll();

        // Create a user
        testUser = new User();
        testUser.setUsername("Test User");
        testUser = userRepository.save(testUser);

        // Register a car for that user
        car = new Car();
        car.setModel("Swift");
        car.setRegistrationNo("TN01AB1234");
        car.setStatus(CarStatus.IDLE);
        car.setOwner(testUser);
        car = carRepository.save(car);
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

        mockMvc.perform(post("/api/cars/register/" + testUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCar)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model", is("Innova")))
                .andExpect(jsonPath("$.registrationNo", is("TN02XY9999")))
                .andExpect(jsonPath("$.status", is("IDLE")));
    }

    @Test
    void testGetCarsByOwner() throws Exception {
        mockMvc.perform(get("/api/cars/owner/" + testUser.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].model", is("Swift")));
    }
}

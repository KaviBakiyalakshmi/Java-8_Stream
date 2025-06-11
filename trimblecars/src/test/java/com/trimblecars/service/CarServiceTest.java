package com.trimblecars.service;

import com.trimblecars.model.Car;
import com.trimblecars.model.CarStatus;
import com.trimblecars.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCarById_Success() {
        // Arrange
        Car car = new Car();
        car.setId(1L);
        car.setModel("Honda City");
        car.setStatus(CarStatus.IDLE);
         // will fail if @NotNull


        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        // Act
        Car result = carService.getCarById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Honda City", result.getModel());
        assertEquals(CarStatus.IDLE, result.getStatus());
        verify(carRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCarById_NotFound() {
        // Arrange
        when(carRepository.findById(2L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            carService.getCarById(2L);
        });

        assertEquals("Car not found with id: 2", exception.getMessage());
        verify(carRepository, times(1)).findById(2L);
    }
}

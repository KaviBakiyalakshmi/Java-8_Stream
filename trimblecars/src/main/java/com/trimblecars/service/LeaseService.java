package com.trimblecars.service;

import com.trimblecars.model.Car;
import com.trimblecars.model.CarStatus;
import com.trimblecars.model.Lease;
import com.trimblecars.model.User;
import com.trimblecars.repository.CarRepository;
import com.trimblecars.repository.LeaseRepository;
import com.trimblecars.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseService {
    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    public Lease startLease(Long carId, Long customerId) {

        if (leaseRepository.countByCustomerIdAndEndDateIsNull(customerId) >= 2) {
            throw new RuntimeException("Maximum 2 active leases allowed");
        }


        Car car = carRepository.findById(carId).orElseThrow();
        if (car.getStatus() != CarStatus.IDLE) {
            throw new RuntimeException("Car not available for lease");
        }

        User customer =userRepository.findById(customerId).orElseThrow();

        Lease lease=new Lease();
        lease.setCar(car);
        lease.setCustomer(customer);
        lease.setStartDate(java.time.LocalDate.now());
        car.setStatus(CarStatus.ON_LEASE);

        carRepository.save(car);
        return leaseRepository.save(lease);
    }

    public Lease endLease(Long leaseId)
    {
        Lease lease=leaseRepository.findById(leaseId).orElseThrow();
        lease.setEndDate(java.time.LocalDate.now());

        Car car=lease.getCar();
        car.setStatus(CarStatus.IDLE);
        carRepository.save(car);

        return leaseRepository.save(lease);
    }

    public List<Lease> getLeasebyCustomer(Long customerId){
        return leaseRepository.findByCustomerId(customerId);
    }

    public List<Lease> getLeaseByOwner(Long ownerId)
    {
        return leaseRepository.findByCarOwnerId(ownerId);
    }
}

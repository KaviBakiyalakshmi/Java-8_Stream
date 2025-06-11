package com.trimblecars.dummydata;

import com.trimblecars.model.*;
import com.trimblecars.repository.CarRepository;
import com.trimblecars.repository.LeaseRepository;
import com.trimblecars.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DummyDataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private LeaseRepository leaseRepository;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            // Create users
            User admin = new User();
            admin.setUsername("admin1");
            admin.setPassword("adminpass");
            admin.setRole(Role.ADMIN);

            User customer = new User();
            customer.setUsername("customer1");
            customer.setPassword("customerpass");
            customer.setRole(Role.CUSTOMER);

            User owner = new User();
            owner.setUsername("owner1");
            owner.setPassword("ownerpass");
            owner.setRole(Role.OWNER);

            userRepository.saveAll(List.of(admin, customer, owner));
            System.out.println("✅ Dummy users inserted successfully");

            // Create car owned by owner1
            Car car = new Car();
            car.setModel("Honda City");
            car.setRegistrationNo("TN01AB1234");
            car.setStatus(CarStatus.IDLE);
            car.setOwner(owner); // Foreign key to User

            carRepository.save(car);
            System.out.println("🚗 Dummy car inserted successfully");

            // Create lease (customer leases car)
            Lease lease = new Lease();
            lease.setCustomer(customer); // FK to user
            lease.setCar(car);           // FK to car
            lease.setStartDate(LocalDate.now());
            lease.setEndDate(LocalDate.now().plusDays(10));

            leaseRepository.save(lease);
            System.out.println("📝 Dummy lease inserted successfully");

        } else {
            System.out.println("ℹ️ Users already exist. Skipping dummy data insert.");
        }
    }
}

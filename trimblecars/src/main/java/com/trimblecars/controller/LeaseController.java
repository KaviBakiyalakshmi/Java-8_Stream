package com.trimblecars.controller;

import com.trimblecars.model.Lease;
import com.trimblecars.repository.LeaseRepository;
import com.trimblecars.service.LeaseService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/api/leases")
public class LeaseController {

    @Autowired
    private LeaseService leaseService;

    @Autowired
    private LeaseRepository leaseRepository;

    @PostMapping("/start")
    public Lease startLease(@RequestParam Long carId, @RequestParam Long customerId)
    {
        return leaseService.startLease(carId, customerId);
    }

    @PostMapping("/end/{leaseId}")
    public Lease endLease(@PathVariable Long leaseId)
    {
        return leaseService.endLease(leaseId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Lease> getCustomerLeases(@PathVariable Long customerId)
    {
        return leaseService.getLeasebyCustomer(customerId);
    }

    @GetMapping("/owner/{ownerId}")
    public List<Lease> getOwnerLease(@PathVariable Long ownerId)
    {
        return leaseService.getLeaseByOwner(ownerId);
    }

    @GetMapping("/export/history")
    public void exportLeaseHistory(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=lease-history.csv");

        List<Lease> leases = leaseRepository.findAll();

        PrintWriter writer = response.getWriter();
        writer.println("Lease ID,Car Model,Customer Name,Start Date,End Date");

        for (Lease lease : leases) {
            writer.printf("%d,%s,%s,%s,%s%n",
                    lease.getId(),
                    lease.getCar().getModel(),
                    lease.getCustomer().getUsername(),
                    lease.getStartDate(),
                    lease.getEndDate() != null ? lease.getEndDate() : "Ongoing"
            );
        }

        writer.flush();
        writer.close();
    }



}

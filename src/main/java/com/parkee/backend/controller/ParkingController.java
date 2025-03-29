package com.parkee.backend.controller;

import com.parkee.backend.model.ParkingTicket;
import com.parkee.backend.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    // Check-In
    @PostMapping("/checkin")
    public Map<String, Object> checkIn(@RequestBody Map<String, String> body) {
        String plateNumber = body.get("plateNumber");
        Map<String, Object> response = new HashMap<>();

        try {
            ParkingTicket ticket = parkingService.checkIn(plateNumber);
            response.put("message", "Check-in successful");
            response.put("ticketId", ticket.getId());
            response.put("checkInTime", ticket.getCheckIn());
        } catch (RuntimeException e) {
            response.put("error", e.getMessage());
        }

        return response;
    }

    // Check-Out
    @PostMapping("/checkout")
    public Map<String, Object> checkOut(@RequestBody Map<String, String> body) {
        String plateNumber = body.get("plateNumber");
        Map<String, Object> response = new HashMap<>();

        try {
            ParkingTicket ticket = parkingService.checkOut(plateNumber);
            response.put("message", "Check-out successful");
            response.put("ticketId", ticket.getId());
            response.put("checkInTime", ticket.getCheckIn());
            response.put("checkOutTime", ticket.getCheckOut());
            response.put("totalPrice", ticket.getTotalPrice());
        } catch (RuntimeException e) {
            response.put("error", e.getMessage());
        }

        return response;
    }
}

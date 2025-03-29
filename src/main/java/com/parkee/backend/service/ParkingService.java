package com.parkee.backend.service;

import com.parkee.backend.model.ParkingTicket;
import com.parkee.backend.repository.ParkingTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class ParkingService {

    @Autowired
    private ParkingTicketRepository repository;

    // RATE: 3000 per jam
    private static final double HOURLY_RATE = 3000.0;

    public ParkingTicket checkIn(String plateNumber) {
        // Cek apakah masih ada tiket ACTIVE
        Optional<ParkingTicket> activeTicket = repository.findByPlateNumberAndStatus(plateNumber, "ACTIVE");
        if (activeTicket.isPresent()) {
            // Kendaraan masih di dalam
            throw new RuntimeException("Kendaraan masih berada di dalam. Tidak bisa check-in lagi.");
        }

        ParkingTicket ticket = new ParkingTicket();
        ticket.setPlateNumber(plateNumber);
        ticket.setCheckIn(LocalDateTime.now());
        ticket.setStatus("ACTIVE");
        ticket.setTotalPrice(0.0);

        return repository.save(ticket);
    }

    public ParkingTicket checkOut(String plateNumber) {
        // Cari tiket ACTIVE
        ParkingTicket ticket = repository.findByPlateNumberAndStatus(plateNumber, "ACTIVE")
                .orElseThrow(() -> new RuntimeException("Tidak ada tiket aktif untuk plat " + plateNumber));

        LocalDateTime now = LocalDateTime.now();
        ticket.setCheckOut(now);

        // Hitung selisih jam
        long hours = ChronoUnit.HOURS.between(ticket.getCheckIn(), now);
        // Jika kurang dari 1 jam, minimal 1 jam (sesuai kebutuhan)
        if (hours == 0) hours = 1;

        double totalPrice = hours * HOURLY_RATE;
        ticket.setTotalPrice(totalPrice);
        ticket.setStatus("DONE");

        return repository.save(ticket);
    }

    public ParkingTicket getActiveTicket(String plateNumber) {
        return repository.findByPlateNumberAndStatus(plateNumber, "ACTIVE").orElse(null);
    }

    public Optional<ParkingTicket> getTicketById(Long id) {
        return repository.findById(id);
    }
}

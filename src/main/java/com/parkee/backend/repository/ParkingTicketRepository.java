package com.parkee.backend.repository;

import com.parkee.backend.model.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {
    Optional<ParkingTicket> findByPlateNumberAndStatus(String plateNumber, String status);
}

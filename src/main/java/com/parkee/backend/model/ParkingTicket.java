package com.parkee.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_ticket")
@Getter
@Setter
public class ParkingTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plateNumber;

    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    private Double totalPrice;
    private String status;
}

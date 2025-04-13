package org.example.Entities;


import jakarta.persistence.*;
import lombok.Data;
import org.example.static_data.Status;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "reservation_date")
    private LocalDateTime reservationDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status ;

}

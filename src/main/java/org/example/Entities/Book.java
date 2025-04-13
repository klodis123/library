package org.example.Entities;


import jakarta.persistence.*;
import lombok.Data;
import org.example.static_data.Genre;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = " books_id")
    private Long bookId;

    @Column (name = "books_title")
    private String bookTitle;

    @ManyToOne
    @JoinColumn (name = "author_id")
    private Author author;

    @Column (name = "published_data")
    private LocalDate publishedData;

    @Column (name = "quantity")
    private BigInteger quantity;

    @Column (name = "genre")
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToMany(mappedBy = "book")
    private List <Reservation> reservations;
}

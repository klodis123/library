package org.example.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "author_id")
    private Long authorId;

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "last_name")
    private String lastName;

    @Column (name = "author_bio")
    private String authorBio;

    @OneToMany(mappedBy = "author")
    private List<Book> books ;


}

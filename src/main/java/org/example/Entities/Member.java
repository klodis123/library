package org.example.Entities;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table (name = "members")
@Data
public class Member {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "member_id")
   private Long memberId;

   @Column(name = "member_first_name")
    private String firstName;

   @Column(name = "member_last_name")
    private String lastName;

   @Column(name = "membership_date")
    private LocalDate membershipDate;

   @Column(name = "contact_info")
    private String contactInfo;

   @OneToMany(mappedBy = "member")
   private List <Reservation> reservations ;

}

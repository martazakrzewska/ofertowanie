package com.mazak.ofertowanie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long nip;
    private String adress;
    private String phoneNumber;

    @OneToMany (mappedBy = "customer",  fetch = FetchType.EAGER)
    private List<Offer> offers;

    public Customer(String name, Long nip, String adress, String phoneNumber) {
        this.name = name;
        this.nip = nip;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
    }
}

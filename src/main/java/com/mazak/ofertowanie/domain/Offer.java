package com.mazak.ofertowanie.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table (name = "offers")
public class Offer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String customer;
    private String name;
    private Long time;

    private List<Material> material;

    private List<Tool> tools;




}

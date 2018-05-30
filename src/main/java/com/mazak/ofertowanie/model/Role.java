package com.mazak.ofertowanie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role {

    @Id
    // sequence - pytamy o id a następnie wstawiamy ( w całej bazie )
    // identity - wstawiamy a potem pytamy
    // table - tak jak sequence ( w całej tabeli )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    public Role(String name) {
        this.name = name;
    }
}

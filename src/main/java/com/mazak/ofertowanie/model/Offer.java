package com.mazak.ofertowanie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table (name = "offer")
public class Offer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToMany
    @JoinTable (name = "customer_offer",
            joinColumns = @JoinColumn(name = "offer_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "customer_id", nullable = false))
    private Customer customer;

    private String name;
    private TimeUnit timeUnit;
    private Duration time;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable (name = "offer_material",
            joinColumns = @JoinColumn(name = "offer_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "material_id", nullable = false))
    private List<Material> materials;

    //TODO: w jakiś sposób wrzucić z marszu  (do tabeli) kilka materiałów które będzie można wybierać
    //TODO: podłączyć tabelę z toolsami -  czy przydzielić je do jakiejś obrabiarki ?????
//
//    private List<Tool> tools;




}

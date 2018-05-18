package com.mazak.ofertowanie.repository;

import com.mazak.ofertowanie.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    boolean existsByName(String customer);

    List<Offer> findByCustomer(String customer);
}

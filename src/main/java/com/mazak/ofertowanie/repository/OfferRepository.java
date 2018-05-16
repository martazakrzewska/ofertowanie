package com.mazak.ofertowanie.repository;

import com.mazak.ofertowanie.domain.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}

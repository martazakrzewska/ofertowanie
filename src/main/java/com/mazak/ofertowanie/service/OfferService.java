package com.mazak.ofertowanie.service;

import com.mazak.ofertowanie.domain.Offer;
import com.mazak.ofertowanie.exception.ValidationException;
import com.mazak.ofertowanie.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    public Offer create(Offer offer) {
        if (null == offer.getCustomer()){
            throw new ValidationException("Customer is required");
        }
        return offerRepository.save(offer);
    }
}

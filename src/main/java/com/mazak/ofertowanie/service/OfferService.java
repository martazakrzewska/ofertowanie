package com.mazak.ofertowanie.service;

import com.mazak.ofertowanie.exception.NotFoundException;
import com.mazak.ofertowanie.model.Offer;
import com.mazak.ofertowanie.exception.ValidationException;
import com.mazak.ofertowanie.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Offer update(Offer offer, Long id) {
        if (null == offer.getCustomer()){
            throw new ValidationException("Customer is required");
        }
        Offer existingOffer = offerRepository.getOne(id);

        return offerRepository.save(offer);
    }

    public List<Offer> search(String customer) {
        return offerRepository.findByCustomer(customer);
    }

    public void remove(Long id) {
        if (!offerRepository.existsById(id)){
            throw new NotFoundException(String.format("Offer with id %s not found", id));
        }
        offerRepository.deleteById(id);
    }
}

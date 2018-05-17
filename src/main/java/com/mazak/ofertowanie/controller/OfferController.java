package com.mazak.ofertowanie.controller;

import com.mazak.ofertowanie.domain.Offer;
import com.mazak.ofertowanie.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class OfferController {

    @Autowired
    private OfferService offerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Offer create(@RequestBody Offer offer){
        return  offerService.create(offer);
    }

}

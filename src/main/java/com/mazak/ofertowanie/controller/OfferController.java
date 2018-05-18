package com.mazak.ofertowanie.controller;

import com.mazak.ofertowanie.model.Offer;
import com.mazak.ofertowanie.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (path = "/offers")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Offer create(@RequestBody Offer offer){
        return  offerService.create(offer);
    }

    @PutMapping
    @ResponseStatus (HttpStatus.OK)
    public Offer update(@RequestBody Offer offer, @PathVariable("id") Long id){
        return offerService.update(offer, id);
    }

    @GetMapping
    @ResponseStatus (HttpStatus.OK)
    public List<Offer> search (
            @RequestParam (value = "customer", required = false, defaultValue = "") String customer){
        return offerService.search(customer);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String remove (@PathVariable("id") Long id){
        offerService.remove(id);
        return String.format("Offer with id %s was remove", id);
    }

}

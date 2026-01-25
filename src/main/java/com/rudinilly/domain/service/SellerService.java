package com.rudinilly.domain.service;

import com.rudinilly.api.command.seller.SellerCommand;
import com.rudinilly.domain.model.entity.Seller;
import com.rudinilly.domain.repository.SellerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

import java.util.UUID;

@ApplicationScoped
public class SellerService {
    private final SellerRepository repository;

    public SellerService(SellerRepository repository){
        this.repository = repository;
    }

    public Seller create(SellerCommand command) {
        Seller seller = new Seller(command.name());

        repository.save(seller);
        return seller;
    }

    public Seller findById(UUID id) {
        Seller seller = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Seller not found")
        );

        return seller;
    }

}

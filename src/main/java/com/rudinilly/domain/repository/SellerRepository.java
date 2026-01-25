package com.rudinilly.domain.repository;

import com.rudinilly.domain.model.entity.Seller;

import java.util.Optional;
import java.util.UUID;

public interface SellerRepository {
    Seller save(Seller seller);
    Optional<Seller> findById(UUID id);
}

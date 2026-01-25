package com.rudinilly.domain.service;

import com.rudinilly.api.command.seller.SellerCommand;
import com.rudinilly.domain.model.entity.Seller;
import com.rudinilly.domain.repository.SellerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SellerServiceTest {
    @Mock
    SellerRepository repository;

    @InjectMocks
    SellerService service;

    @Test
    void shouldCreateSellerAndSaveInRepository() {
        SellerCommand command = new SellerCommand("João Vendedor");

        service.create(command);

        ArgumentCaptor<Seller> captor = ArgumentCaptor.forClass(Seller.class);

        Mockito.verify(repository).save(captor.capture());

        Seller savedSeller = captor.getValue();

        assertEquals("João Vendedor", savedSeller.getName());
    }
}
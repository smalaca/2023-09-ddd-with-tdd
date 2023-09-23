package com.smalaca.productmanagement.infrastructure.repository.inmemory.assortment;

import com.smalaca.productmanagement.domain.assortment.Assortment;
import com.smalaca.productmanagement.domain.assortment.AssortmentRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public class SpringJpaAssortmentRepository implements AssortmentRepository {
    private final CrudRepository<Assortment, UUID> repository;

    public SpringJpaAssortmentRepository(CrudRepository<Assortment, UUID> repository) {
        this.repository = repository;
    }

    @Override
    public Assortment find(UUID sellerId) {
        return null;
    }

    @Override
    public void save(Assortment assortment) {

    }
}

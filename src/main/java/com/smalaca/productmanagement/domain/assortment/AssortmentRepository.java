package com.smalaca.productmanagement.domain.assortment;

import java.util.UUID;

public interface AssortmentRepository {
    Assortment find(UUID sellerId);

    void save(Assortment assortment);
}

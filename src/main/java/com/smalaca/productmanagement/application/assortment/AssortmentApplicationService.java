package com.smalaca.productmanagement.application.assortment;

import com.smalaca.productmanagement.domain.assortment.Assortment;
import com.smalaca.productmanagement.domain.assortment.AssortmentRepository;

class AssortmentApplicationService {
    private final AssortmentRepository assortmentRepository;

    AssortmentApplicationService(AssortmentRepository assortmentRepository) {
        this.assortmentRepository = assortmentRepository;
    }

    void addProduct(AddProductCommand command) {
        // tłumaczenie na język biznesowy -> 0...*
        // 1. zamiana typów prostych na Value Object
        // 2. zamiana identyfikatorów na Aggregate
        Assortment assortment = assortmentRepository.find(command.sellerId());

        // wywołanie metody na domenie - 1
        assortment.addProduct(command.productCode(), command.productName());

        // zapis zmian -> 1 ... *
        assortmentRepository.save(assortment);
    }
}

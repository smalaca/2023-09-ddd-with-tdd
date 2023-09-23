package com.smalaca.productmanagement.application.assortment;

import com.smalaca.productmanagement.domain.assortment.Assortment;
import com.smalaca.productmanagement.domain.assortment.AssortmentRepository;
import com.smalaca.productmanagement.domain.assortment.ProductValidationService;

class AssortmentApplicationService {
    private final AssortmentRepository assortmentRepository;
    private final ProductValidationService productValidationService;

    AssortmentApplicationService(AssortmentRepository assortmentRepository, ProductValidationService productValidationService) {
        this.assortmentRepository = assortmentRepository;
        this.productValidationService = productValidationService;
    }

    void addProduct(AddProductCommand command) {
        // tłumaczenie na język biznesowy -> 0...*
        // 1. zamiana typów prostych na Value Object
        // 2. zamiana identyfikatorów na Aggregate
        Assortment assortment = assortmentRepository.find(command.sellerId());

        // wywołanie metody na domenie - 1
        assortment.addProduct(command.asDto());

        // zapis zmian -> 1 ... *
        assortmentRepository.save(assortment);
    }
}

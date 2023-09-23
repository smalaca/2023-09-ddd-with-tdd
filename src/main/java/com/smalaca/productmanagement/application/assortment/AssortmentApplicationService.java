package com.smalaca.productmanagement.application.assortment;

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

        // wywołanie metody na domenie - 1

        // zapis zmian -> 1 ... *
    }
}

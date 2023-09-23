package com.smalaca.productmanagement.infrastructure.productvalidationservice;

import com.smalaca.productmanagement.domain.assortment.ProductValidationService;

public class RestProductValidationService implements ProductValidationService {
    @Override
    public boolean isValid(String productCode) {
        return false;
    }
}

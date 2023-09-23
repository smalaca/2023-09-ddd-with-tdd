package com.smalaca.pruchase.domain.order;

class AmountException extends RuntimeException {
    AmountException() {
        super("Amount must be greater than zero.");
    }
}

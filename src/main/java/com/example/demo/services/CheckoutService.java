package com.example.demo.services;

import jakarta.transaction.Transactional;

public interface CheckoutService {
    PurchaseResponse placeOrder(PurchaseData purchase);

    @Transactional
    PurchaseResponse checkout(PurchaseData purchase);
}

package com.example.test;

// Interface to define a contract for anything that can be paid
public interface Payable {
    void processPayment(PaymentMethod method);
    double getTotalAmount();
}


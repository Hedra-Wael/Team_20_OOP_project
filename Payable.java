package com.mycompany.oop_project;

// Interface to define a contract for anything that can be paid
public interface Payable {
    void processPayment(Invoice.PaymentMethod method);
    double getTotalAmount();
}


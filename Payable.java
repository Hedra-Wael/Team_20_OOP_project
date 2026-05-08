package com.mycompany.oop_project;

public interface Payable {
    void processPayment(PaymentMethod method);
    double getTotalAmount();
}


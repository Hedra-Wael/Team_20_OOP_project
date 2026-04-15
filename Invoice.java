package com.mycompany.oop_project;

public class Invoice {
    public enum PaymentMethod { CASH, CREDIT_CARD, DEBIT_CARD, ONLINE_TRANSFER }/*هو كان طلب enum ودا فيديو بيشرحها */
   /*https://youtu.be/wq9SJb8VeyM?si=Aqhmb_djRgV3rzVm*//* <---------------------دا الفيديو */

    private int invoiceId;
    private Reservation reservation;
    private double pricePerNight;
    private double totalAmount;
    private boolean isPaid;
    private PaymentMethod paymentMethod;

    public Invoice(int invoiceId, Reservation reservation,
                   double pricePerNight, double taxRate) {
        if (pricePerNight <= 0) {
            System.out.println("Price per night must be positive.");
        }
        this.invoiceId     = invoiceId;
        this.reservation   = reservation;
        this.pricePerNight = pricePerNight;
        this.isPaid        = false;
        calculateTotal();
    }

    private void calculateTotal() {
        long nights      = reservation.getNumberOfNights();
        this.totalAmount = nights * pricePerNight;
    }

    public void processPayment(PaymentMethod method) {
        if (isPaid) {
            System.out.println("Invoice #" + invoiceId + " is already paid.");
            return;
        }
        if (reservation.getGuest().getBalance() < totalAmount) {
            System.out.println("Insufficient balance. Total due: " + totalAmount);
            return;
        }
        reservation.getGuest().setBalance(
            reservation.getGuest().getBalance() - totalAmount
        );
        this.paymentMethod = method;
        this.isPaid        = true;
        reservation.setStatus("COMPLETED");
        System.out.println("Payment successful via " + method +
                           ". Amount charged: " + String.format("%.2f", totalAmount));
    }

    // Getters
    public int getInvoiceId()               { return invoiceId; }
    public Reservation getReservation()     { return reservation; }
    public double getTotalAmount()          { return totalAmount; }
    public boolean isPaid()                 { return isPaid; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }

    @Override
    public String toString() {
        return "Invoice #" + invoiceId +
               " | Reservation #" + reservation.getReservationId() +
               " | Nights: "      + reservation.getNumberOfNights() +
               " | Price/Night: " + pricePerNight +
               " | Total: "       + String.format(totalAmount) +
               " | Paid: "        + isPaid +
               (isPaid ? " | Method: " + paymentMethod : "");
    }
}

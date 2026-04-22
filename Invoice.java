package com.mycompany.oop_project;

public class Invoice {
    public enum PaymentMethod { CASH, CREDIT_CARD, DEBIT_CARD, ONLINE_TRANSFER }

    private int invoiceId;
    private Reservation reservation;
    private double pricePerNight;
    private double totalAmount;
    private boolean isPaid;
    private PaymentMethod paymentMethod;
    private LocalDate paymentDate;

    public Invoice(int invoiceId, Reservation reservation, double pricePerNight, double taxRate) {
        this.invoiceId = invoiceId;
        this.reservation = reservation;
        this.pricePerNight = pricePerNight;
        this.isPaid = false;
        calculateTotal();
    }

//    private void calculateTotal() {
//        long nights = reservation.getNumberOfNights();
//        this.totalAmount = nights * pricePerNight;
//    }

    public void processPayment(PaymentMethod method) {
        if (reservation.getGuest().getBalance() >= totalAmount) {
            reservation.getGuest().setBalance(reservation.getGuest().getBalance() - totalAmount);
            this.isPaid = true;
            this.paymentMethod = method;
            this.paymentDate = LocalDate.now();
            reservation.setStatus(Reservation.ReservationStatus.COMPLETED);
            reservation.setStatus("COMPLETED");
            System.out.println("Payment successful via " + method);
        } else {
            System.out.println("Insufficient balance for payment.");
        }
    }

    public double getTotalAmount() { return totalAmount; }
    public boolean isPaid() { return isPaid; }
    public LocalDate getPaymentDate() { return paymentDate; }










    //hedra extra

    private void calculateTotal() {
        long nights = reservation.getNumberOfNights();
        double baseTotal = nights * pricePerNight;

        if (reservation.isAllInclusive()) {
            baseTotal += (nights * 50.0); // Adding $50 per night for All-Inclusive
        }
        this.totalAmount = baseTotal;
    }

}

package com.mycompany.oop_project;

import java.time.LocalDate;

public class Receptionist extends Staff {
    
    // Automatically set the role to RECEPTIONIST
    public Receptionist(String username, String password, LocalDate dob, int hours) {
        super(username, password, dob, hours, Role.RECEPTIONIST);
    }

    public void makeReservation(Reservation res) {
        HotelDatabase.reservations.add(res);
    }

    public void handleCheckIn(Reservation res) {
        // Fixed: Use setAvailable(false)
        res.getRoom().setAvailable(false);
        res.setStatus(ReservationStatus.CONFIRMED);
        System.out.println("Guest checked in to room " + res.getRoom().getRoomNumber());
    }

    public void handleCheckOut(Reservation res, double pricePerNight) {
        // Fixed: Use setAvailable(true)
        res.getRoom().setAvailable(true);
        res.setStatus(ReservationStatus.COMPLETED);
        
        // Fixed: Match the Invoice constructor we built earlier
        int newInvoiceId = HotelDatabase.invoices.size() + 5000;
        Invoice inv = new Invoice(newInvoiceId, res, pricePerNight, 0.14);
        HotelDatabase.invoices.add(inv);
        
        System.out.println("Guest checked out. Invoice generated for $" + inv.getTotalAmount());
    }
}
    public LocalDate getCheckOutDate() { return checkOutDate; }

    @Override
    public String toString() {
        return "Reservation #" + reservationId + " | Guest: " + guest.getUsername() +
                " | Nights: " + getNumberOfNights() + " | Status: " + status;
    }




    //hedra extra
    private int numberOfGuests;
    private boolean isAllInclusive;

    // Update your constructor or add a setter
    public void setStayDetails(int numberOfGuests, boolean isAllInclusive) {
        this.numberOfGuests = numberOfGuests;
        this.isAllInclusive = isAllInclusive;
    }

    public boolean isAllInclusive() {
        return isAllInclusive;
    }
}

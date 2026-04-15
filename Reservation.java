package com.mycompany.oop_project;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private int reservationId;
    private Guest guest;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String status; 

    public Reservation(int reservationId, Guest guest, Room room,
                       LocalDate checkInDate, LocalDate checkOutDate) {
        if (checkInDate == null || checkOutDate == null) {
            System.out.println("Dates cannot be null.");
        }
        if (checkOutDate != null && !checkOutDate.isAfter(checkInDate)) {
            System.out.println("Check-out must be after check-in.");
        }
        this.reservationId = reservationId;
        this.guest         = guest;
        this.room          = room;
        this.checkInDate   = checkInDate;
        this.checkOutDate  = checkOutDate;
        this.status        = "CONFIRMED";
    }

    public long getNumberOfNights() {
        return ChronoUnit.DAYS.between(checkInDate, checkOutDate);/*بتحسب عدد  الايام ولازم تكون long */
    }

    public void cancelReservation() {
        if (this.status.equals("CANCELLED")) {
            System.out.println("Reservation is already cancelled.");
            return;
        }
        this.status = "CANCELLED";
        System.out.println("Reservation #" + reservationId + " has been cancelled.");
    }

    // Getters
    public int getReservationId()      { return reservationId; }
    public Guest getGuest()            { return guest; }
    public Room getRoom()              { return room; }
    public LocalDate getCheckInDate()  { return checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; }
    public String getStatus()          { return status; }

    // Setters
    public void setCheckInDate(LocalDate checkInDate) {
        if (checkInDate == null) {
            System.out.println("Invalid check-in date.");
            return;
        }
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        if (checkOutDate == null || !checkOutDate.isAfter(this.checkInDate)) {
            System.out.println("Check-out must be after check-in.");
            return;
        }
        this.checkOutDate = checkOutDate;
    }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Reservation #" + reservationId +
               " | Guest: "     + guest.getUsername() +
               " | Check-in: "  + checkInDate +
               " | Check-out: " + checkOutDate +
               " | Nights: "    + getNumberOfNights() +
               " | Status: "    + status;
    }
}

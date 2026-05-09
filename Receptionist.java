package com.mycompany.oop_project;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
enum ReservationStatus {
    PENDING, CONFIRMED, CANCELLED, COMPLETED

        }

public class Reservation {
    private int reservationId;
    private Guest guest;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private ReservationStatus  status;

    public Reservation(int reservationId, Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.reservationId = reservationId;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        status = ReservationStatus.CONFIRMED;
    }

    public long getNumberOfNights() {
        return ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    public Guest getGuest() { return guest; }
    public int getReservationId() { return reservationId; }
    public ReservationStatus getStatus() { return status; }
    public void setStatus(ReservationStatus status) { this.status = status; }
    public Room getRoom() { return room; }
    public LocalDate getCheckInDate() { return checkInDate; }
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

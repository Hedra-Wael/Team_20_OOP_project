package com.mycompany.oop_project;

import java.time.LocalDate;

public abstract class Staff {
    // Required Enum
    public enum Role {
        ADMIN, RECEPTIONIST
    }

    protected String username;
    protected String password;
    protected LocalDate dateOfBirth;
    protected int workingHours;
    protected Role role; // Added role attribute

    public Staff(String username, String password, LocalDate dob, int hours, Role role) {
        this.username = username;
        this.password = password;
        this.dateOfBirth = dob;
        this.workingHours = hours;
        this.role = role;
    }

    public String getUsername() { return username; }
    public Role getRole() { return role; }

    // --- Shared Behaviors for all Staff ---
    
    public void viewAllGuests() {
        System.out.println("\n--- All Registered Guests ---");
        for (Guest g : HotelDatabase.guests) {
            System.out.println("Guest: " + g.getUsername() + " | Balance: $" + g.getBalance());
        }
    }

    public void viewAllRooms() {
        System.out.println("\n--- All Rooms ---");
        for (Room r : HotelDatabase.rooms) {
            String status = r.isAvailable() ? "Available" : "Occupied";
            System.out.println("Room " + r.getRoomNumber() + " | Status: " + status);
        }
    }

    public void viewAllReservations() {
        System.out.println("\n--- All Reservations ---");
        for (Reservation res : HotelDatabase.reservations) {
            System.out.println(res.toString());
        }
    }
}

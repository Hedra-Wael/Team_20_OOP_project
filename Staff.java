package com.mycompany.oop_project;

import java.time.LocalDate;

public abstract class Staff {

    // Required Enum for Roles
    public enum Role {
        ADMIN, RECEPTIONIST
    }

    protected String username;
    protected String password;
    protected LocalDate dateOfBirth;
    protected int workingHours;
    protected Role role; 

    // Constructor
    public Staff(String username, String password, LocalDate dob, int hours, Role role) {
        this.username = username;
        this.password = password;
        this.dateOfBirth = dob;
        this.workingHours = hours;
        this.role = role;
    }

    // --- Getters ---
    public String getUsername() { 
        return username; 
    }

    public String getPassword() { 
        return password; 
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public Role getRole() { 
        return role; 
    }

    // --- Setters ---
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    public void setRole(Role role) {
        this.role = role;
    }

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
            // Assuming your Reservation class has a toString() method
            System.out.println(res.toString());
        }
    }
}

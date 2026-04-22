package com.mycompany.oop_project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Guest {

    public enum Gender {
        MALE,
        FEMALE
    }

    private String username;
    private String password;
    private LocalDate dateOfBirth;
    private double balance;
    private String address;
    private Gender gender;
    private List<String> roomPreferences;

    public Guest() {
        roomPreferences = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid username");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Invalid password");
        }
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Invalid date of birth");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid address");
        }
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        if (gender == null) {
            throw new IllegalArgumentException("Invalid gender");
        }
        this.gender = gender;
    }

    public List<String> getRoomPreferences() {
        return roomPreferences;
    }

    public void addRoomPreference(String preference) {
        if (preference == null || preference.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid room preference");
        }
        roomPreferences.add(preference);
    }

    public void register(String username, String password) {
        setUsername(username);
        setPassword(password);
        System.out.println("Registration successful!");
    }

    public boolean login(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password");
            return false;
        }
    }
}

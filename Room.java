package com.mycompany.oop_project;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int roomNumber;
    private RoomType roomType;
    private List<Amenity> amenities;
    private boolean isAvailable;

    // Constructor
    public Room(int roomNumber, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.amenities = new ArrayList<>();
        this.isAvailable = true; // Default to available
    }

    // Requirement: A room should be associated with a list of Amenity objects
    public void addAmenity(Amenity amenity) {
        if (amenity != null) {
            this.amenities.add(amenity);
        }
    }

    // --- Getters ---
    public int getRoomNumber() { return roomNumber; }
    public RoomType getRoomType() { return roomType; }
    public List<Amenity> getAmenities() { return amenities; }
    public boolean isAvailable() { return isAvailable; }

    // --- Setters ---
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }
    public void setRoomType(RoomType roomType) { this.roomType = roomType; }
    public void setAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }
}

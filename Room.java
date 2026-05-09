package com.mycompany.oop_project;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int roomNumber;
    private RoomType roomType;
    private List<Amenity> amenities;
    private boolean isAvailable;

    public RoomType getRoomType() {
        return roomType;
    }

    public Room(int roomNumber, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.amenities = new ArrayList<>();
        this.isAvailable = true;
    }

    public void addAmenity(Amenity amenity) {
        if (amenity != null) this.amenities.add(amenity);
    }
    

    public int getRoomNumber() { return roomNumber; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { this.isAvailable = available; }
    @Override
    public String toString() {
        String status = this.isAvailable() ? "Available" : "Occupied";
        // Adjust the variable names if your Room class uses different getters!
        return "Room " + this.getRoomNumber() + " - " + this.getRoomType().getName() + " [" + status + "]";
    }
}


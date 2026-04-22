package com.mycompany.oop_project;

import java.time.LocalDate;

public class Admin extends Staff {
    
    // Automatically set the role to ADMIN
    public Admin(String username, String password, LocalDate dob, int hours) {
        super(username, password, dob, hours, Role.ADMIN);
    }

    // CRUD for Room Types
    public void addRoomType(RoomType rt) { HotelDatabase.roomTypes.add(rt); }
    public void updateRoomType(int index, RoomType rt) { HotelDatabase.roomTypes.set(index, rt); }
    public void deleteRoomType(int index) { HotelDatabase.roomTypes.remove(index); }

    // CRUD for Amenities
    public void addAmenity(Amenity a) { HotelDatabase.amenities.add(a); }
    public void updateAmenity(int index, Amenity a) { HotelDatabase.amenities.set(index, a); }
    public void deleteAmenity(int index) { HotelDatabase.amenities.remove(index); }
    
    // CRUD for Rooms
    public void addRoom(Room r) { HotelDatabase.rooms.add(r); }
    public void updateRoom(int index, Room newRoom) { 
        HotelDatabase.rooms.set(index, newRoom); 
    }
    public void deleteRoom(int index) { HotelDatabase.rooms.remove(index); }
}

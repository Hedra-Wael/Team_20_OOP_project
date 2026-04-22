package com.mycompany.oop_project;

import java.time.LocalDate;
import java.util.ArrayList;

public class HotelDatabase {
    // Required static ArrayLists for all entities
    public static ArrayList<Guest> guests = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();
    public static ArrayList<Reservation> reservations = new ArrayList<>();
    public static ArrayList<Invoice> invoices = new ArrayList<>();
    
    // Additional lists to match your UML diagram
    public static ArrayList<RoomType> roomTypes = new ArrayList<>();
    public static ArrayList<Amenity> amenities = new ArrayList<>();

    // Pre-populate with dummy data for immediate testing
    public static void loaddata() {
        // Clear lists in case this method is called multiple times
        guests.clear();
        rooms.clear();
        reservations.clear();
        invoices.clear();
        roomTypes.clear();
        amenities.clear();

        // 1. Create Room Types
        RoomType single = new RoomType("Single", 80.0);
        RoomType doubleRoom = new RoomType("Double", 120.0);
        RoomType suite = new RoomType("Suite", 250.0);
        roomTypes.add(single);
        roomTypes.add(doubleRoom);
        roomTypes.add(suite);

        // 2. Create Amenities
        Amenity wifi = new Amenity("WiFi");
        Amenity tv = new Amenity("TV");
        Amenity miniBar = new Amenity("Mini-bar");
        amenities.add(wifi);
        amenities.add(tv);
        amenities.add(miniBar);

        // 3. Create Rooms & Assign Amenities
        Room room101 = new Room(101, single);
        room101.addAmenity(wifi);
        room101.addAmenity(tv);
        rooms.add(room101);
        
        rooms.add(new Room(201, single));
        rooms.add(new Room(301, single));
        rooms.add(new Room(102, doubleRoom));
        rooms.add(new Room(202, doubleRoom));
        rooms.add(new Room(302, doubleRoom));
        rooms.add(new Room(103, suite));
        rooms.add(new Room(203, suite));
        rooms.add(new Room(303, suite));

        // 4. Create Dummy Guests
        Guest guest1 = new Guest();
        guest1.setUsername("johndoe");
        guest1.setPassword("password123");
        guest1.setBalance(1500.0);
        guest1.setAddress("123 Main St");
        guests.add(guest1);

        // 5. Create a Dummy Reservation & Invoice
        // We set the room to unavailable since it is booked
        room101.setAvailable(false);
        Reservation res1 = new Reservation(1000, guest1, room101, LocalDate.now(), LocalDate.now().plusDays(3));
        reservations.add(res1);
        
        // Creating an unpaid invoice for the reservation
        Invoice inv1 = new Invoice(5000, res1, 80.0, 0.14);
        invoices.add(inv1);
        
        System.out.println("HotelDatabase successfully pre-populated with dummy data.");
    }
}

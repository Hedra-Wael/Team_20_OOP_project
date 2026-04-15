package com.mycompany.oop_project;
import java.util.ArrayList;
public class HotelDatabase {
    public static ArrayList<Guest> guests = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();
    public static ArrayList<Reservation> reservations = new ArrayList<>();
    public static ArrayList<Invoice> invoices = new ArrayList<>();
    public static void loaddata(){
        guests.clear();
        rooms.clear();
        reservations.clear();
        invoices.clear();

        // Adding Dummy Guests
        guests.add(new Guest());
        guests.add(new Guest());
        guests.add(new Guest());

        rooms.add(new Room());
        rooms.add(new Room());
        rooms.add(new Room());
        rooms.add(new Room());
        rooms.add(new Room());
        
        reservations.add(new Reservation());
        reservations.add(new Reservation());
        
        invoices.add(new Invoice());
    }
}


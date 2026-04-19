package com.mycompany.oop_project;

import java.util.ArrayList;

// just putting all the main lists here so the whole team can grab data easily
public class HotelDatabase {
    
    // making these static so we all share the exact same lists across the app.
    // saves us from having to pass a database object around everywhere.
    public static ArrayList<Guest> guests = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();
    public static ArrayList<Reservation> reservations = new ArrayList<>();
    public static ArrayList<Invoice> invoices = new ArrayList<>();
    
    // loading dummy data so we don't have to type it in manually every time we run the code
    public static void loaddata(){
        
        // clear lists first so we don't get duplicates if we accidentally call this twice
        guests.clear();
        rooms.clear();
        reservations.clear();
        invoices.clear();

        // throwing in some blank guests just so member 1's login stuff doesn't crash
        guests.add(new Guest());
        guests.add(new Guest());
        guests.add(new Guest());

        // dropping in 5 dummy rooms so the room search actually shows results
        rooms.add(new Room());
        rooms.add(new Room());
        rooms.add(new Room());
        rooms.add(new Room());
        rooms.add(new Room());
        
        // fake reservations to test the cancel and checkout features
        reservations.add(new Reservation());
        reservations.add(new Reservation());
        
        // need at least one invoice to test the payment method stuff
        invoices.add(new Invoice());
    }
}

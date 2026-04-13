/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oop_project;

/**
 *
 * @author ahmad
 */
 class Reservation { /*task member 3 */
    private int Days ;
    private Guest guest =new Guest(); 
    private Room room =new Room();   
    private LocalDate checkInDay;
    private LocalDate checkOutDay;
   public int getDays(LocalDate S ,LocalDate E ) {
    int startDay = S.getDayOfMonth(); 
    int endDay = E.getDayOfMonth();
    return endDay-startDay;
    /*incomplete */
   }
   
    private ReservationStatue status;
    public Reservation(Guest guest, Room room, LocalDate checkIn, LocalDate checkOut) {
        this.guest = guest;
        this.room = room;
        this.checkInDay = checkIn;
        this.checkOutDay = checkOut;
        this.status = ReservationStatue.PENDING; 
        Days = getDays(checkIn,checkOut);
    }
    private enum ReservationStatue{
        PENDING,
        CONFIRMED,
        CANCELLED,
        COMPLETED;
    }
    public Guest getGuest() { return guest; }
    public Room getRoom() { return room; }
    public LocalDate getCheckInDay() { return checkInDay; }
    public LocalDate getCheckOutDay() { return checkOutDay; }
    public ReservationStatue getStatus() { return status; }
    public void setStatus(ReservationStatue status) { this.status = status; }
}

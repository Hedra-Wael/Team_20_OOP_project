/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oop_project;

/**
 *
 * @author ahmad
 */
class Invoice  {
   private  enum PaymentMethod{
       cash,
       credit_card,
       online;
   }
   
   private final LocalDate paymentDate =localDate.now();
   private Reservation reservation = new Reservation() ; /*not finished*/
   Invoice (Reservation reservation)
   {
       this.reservation= reservation ;
   }
  public printInvoce (){
      System.out.println ("||=========Invoice=========||");
      System.out.println ("||guest name :" + reservation.guest.name + "   ||");
      System.out.println (" total price = " + reservation.room.pricePerNight*reservation.Days + "EGP" );
      System.out.println("Payment Method: " + paymentMethod); /*same here */
      System.out.println("Date: " + paymentDate);
  }

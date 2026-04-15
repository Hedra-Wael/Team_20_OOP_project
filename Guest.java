package com.mycompany.oop_project;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Guest {
    private String username;
    private String password;
    private String dateOfBirth;
    private double balance;
    private String address;
    private char gender;
    
    public String getUsername() {
    return username;
    }
    public void setUsername(String username) {
        if (username == null || username.isEmpty()) {
            System.out.println("Please enter a valid username");
        }
        this.username = username;
    }
   public String getPassword(){
       return password;
   }
 
   public void setPassword(String password)
   {
       if (password == null || password.isEmpty()) {
           System.out.println("enter a valid password");
       }
       if (password.length() < 6) {
           System.out.println("Please enter a password more than 6 characters");
       }
       this.password = password;
   }
   public String getDateofBirth(){
       return dateOfBirth;
   }
   public void settDateofBirth(String dateOfBirth){
       if (dateOfBirth == null){
           System.out.println("Please enter a valid date of birth");
       }
           this.dateOfBirth = dateOfBirth;
   }
   
    public double getBalance()
    {
        return balance;
    }
    public void setBalance(double balance)
    {
     if (balance < 0) {
     System.out.println("Enter a positive balance");
     }
     this.balance = balance;
     }
       public String getaddress()
       {
           return address;
       }
       public void setAddress(String address){
       if (address == null || address.trim().isEmpty()) {
          
           System.out.println("enter valid address");       
       this.address = address;
       }    
       }
       public char getGender()
       {
       return gender;
       }
    public void setGender(char gender){
            if (gender != 'M' && gender != 'F') {
               System.out.println("Please enter a valid gender");
             }
          this.gender = gender;
               }

       }

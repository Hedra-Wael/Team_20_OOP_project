package com.mycompany.oop_project;


public class RoomType {
    private String name;
    private double basePrice;

    public RoomType(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }
    
    public String getName() { 
        return name; 
    }
    
    public double getBasePrice() { 
        return basePrice; 
    }
    

    public void setName(String name) {
        this.name = name;
    }
    
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
    

    @Override
    public String toString() {
        return name + " ($" + basePrice + ")";
    }
}

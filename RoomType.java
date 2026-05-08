package com.mycompany.oop_project;

// You can delete the ArrayList and LocalDate imports, they aren't needed here

public class RoomType {
    private String name;
    private double basePrice;

    public RoomType(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }
    
    // --- Added Getters so the GUI can actually read the data ---
    public String getName() { 
        return name; 
    }
    
    public double getBasePrice() { 
        return basePrice; 
    }
    
    // Optional Setters depending on if your Admin can edit prices
    public void setName(String name) {
        this.name = name;
    }
    
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
    
    // Overriding toString is super helpful for JavaFX ComboBoxes (Dropdowns)!
    @Override
    public String toString() {
        return name + " ($" + basePrice + ")";
    }
}

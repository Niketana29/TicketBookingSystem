package com.hexaware.bean;

public class Venue {
    private String venueName;
    private String address;
    private String name;

    // Default constructor
    public Venue() {
        this.venueName = "Default Venue";
        this.address = "Default Address";
        
    }

    // Parameterized constructor
    public Venue(String venueName, String address) {
        this.venueName = venueName;
        this.name = venueName; // Set name same as venueName
        this.address = address;
    }

    // Getters and Setters
    public String getVenueName() { return venueName; }
    public void setVenueName(String venueName) { this.venueName = venueName; }

    public String getName() {
        return name;
    }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    // Display venue details
    public void displayVenueDetails() {
        System.out.println("Venue: " + venueName);
        System.out.println("Address: " + address);
        System.out.println("Venue: " + name);
    }
}

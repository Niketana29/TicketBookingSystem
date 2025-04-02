package com.hexaware.ticketbooking;

public class Venue {
    private String venueName;
    private String address;

    // Default constructor
    public Venue() {}

    // Parameterized constructor
    public Venue(String venueName, String address) {
        this.venueName = venueName;
        this.address = address;
    }

    // Getters & Setters
    public String getVenueName() { return venueName; }
    public String getAddress() { return address; }

    // Display venue details
    public void displayVenueDetails() {
        System.out.println("Venue: " + venueName);
        System.out.println("Address: " + address);
    }
}


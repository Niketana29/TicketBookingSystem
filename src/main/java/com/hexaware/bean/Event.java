package com.hexaware.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Event {
    public enum EventType { MOVIE, SPORTS, CONCERT }

    protected String eventName;
    protected LocalDate eventDate;
    protected LocalTime eventTime;
    protected Venue venue;
    protected int totalSeats;
    protected int availableSeats;
    protected double ticketPrice;
    protected EventType eventType;

    // Default constructor
    public Event() {}

    // Parameterized constructor
    public Event(String eventName, LocalDate eventDate, LocalTime eventTime, Venue venue, int totalSeats, double ticketPrice, EventType eventType) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.venue = venue;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.ticketPrice = ticketPrice;
        this.eventType = eventType;
    }

    // Getters and Setters
    public String getEventName() { return eventName; }
    public LocalDate getEventDate() { return eventDate; }
    public LocalTime getEventTime() { return eventTime; }
    public Venue getVenue() { return venue; }
    public int getTotalSeats() { return totalSeats; }
    public int getAvailableSeats() { return availableSeats; }
    public double getTicketPrice() { return ticketPrice; }
    public EventType getEventType() { return eventType; }

    // Book tickets
    public boolean bookTickets(int numTickets) {
        if (numTickets > availableSeats) {
            System.out.println("Not enough seats available.");
            return false;
        }
        availableSeats -= numTickets;
        return true;
    }

    // Cancel tickets
    public void cancelBooking(int numTickets) {
        availableSeats += numTickets;
    }

    // Revenue
    public double calculateTotalRevenue() {
        return (totalSeats - availableSeats) * ticketPrice;
    }

    public int getBookedNoOfTickets() {
        return totalSeats - availableSeats;
    }

    // Display event details
    public void displayEventDetails() {
        System.out.println("Event: " + eventName + " | Date: " + eventDate + " | Time: " + eventTime);
        System.out.println("Venue: " + venue.getVenueName() + " | Available Seats: " + availableSeats + "/" + totalSeats);
        System.out.println("Ticket Price: Rs." + ticketPrice + " | Type: " + eventType);
        System.out.println("Venue Address: " + venue.getAddress());
    }

    // Abstract
    public abstract void displayEventTypeDetails();
}

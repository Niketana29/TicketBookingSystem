package com.hexaware.ticketbooking;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Event {
    public enum EventType { MOVIE, SPORTS, CONCERT }

    protected String eventName;
    protected LocalDate eventDate;
    protected LocalTime eventTime;
    protected String venueName;
    protected int totalSeats;
    protected int availableSeats;
    protected double ticketPrice;
    protected EventType eventType;

    // Constructor
    public Event(String eventName, LocalDate eventDate, LocalTime eventTime, String venueName, int totalSeats, double ticketPrice, EventType eventType) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.venueName = venueName;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.ticketPrice = ticketPrice;
        this.eventType = eventType;
    }

    // Getters
    public String getEventName() { return eventName; }
    public LocalDate getEventDate() { return eventDate; }
    public LocalTime getEventTime() { return eventTime; }
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

    // Calculate total revenue
    public double calculateTotalRevenue() {
        return (totalSeats - availableSeats) * ticketPrice;
    }

    // Display common event details
    public void displayEventDetails() {
        System.out.println("Event: " + eventName + " | Date: " + eventDate + " | Time: " + eventTime);
        System.out.println("Venue: " + venueName + " | Available Seats: " + availableSeats + "/" + totalSeats);
        System.out.println("Ticket Price: Rs." + ticketPrice + " | Type: " + eventType);
    }

    // Abstract method for event-specific details
    public abstract void displayEventTypeDetails();
}

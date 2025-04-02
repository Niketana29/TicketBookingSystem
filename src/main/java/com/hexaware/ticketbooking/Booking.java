package com.hexaware.ticketbooking;

public class Booking {
    private Event event;
    private int numTickets;
    private double totalCost;

    // Constructor
    public Booking(Event event, int numTickets) {
        this.event = event;
        this.numTickets = numTickets;
        calculateBookingCost();
    }

    // Calculate booking cost
    public void calculateBookingCost() {
        this.totalCost = numTickets * event.getTicketPrice();
    }

    // Book tickets
    public boolean bookTickets() {
        if (event.bookTickets(numTickets)) {
            System.out.println("Booking successful! Total Cost: Rs." + totalCost);
            return true;
        }
        return false;
    }

    // Cancel booking
    public void cancelBooking() {
        event.cancelBooking(numTickets);
        System.out.println("Booking cancelled. Tickets refunded.");
    }

    // Get available tickets
    public int getAvailableNoOfTickets() {
        return event.getAvailableSeats();
    }

    // Get event details
    public void getEventDetails() {
        event.displayEventDetails();
    }
}

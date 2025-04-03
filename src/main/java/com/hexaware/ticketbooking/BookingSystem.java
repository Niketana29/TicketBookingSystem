package com.hexaware.ticketbooking;

public abstract class BookingSystem {
    public abstract void createEvent(String type, String name, String date, String time, String venue, int seats, double price);
    public abstract boolean bookTickets(String eventName, int numTickets);
    public abstract void cancelBooking(String eventName, int numTickets);
    public abstract int getAvailableSeats(String eventName);
}

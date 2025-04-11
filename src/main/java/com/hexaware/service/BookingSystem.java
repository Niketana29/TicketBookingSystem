package com.hexaware.service;

import com.hexaware.bean.Customer;
import com.hexaware.bean.Event;
import com.hexaware.bean.Venue;

public abstract class BookingSystem {
    public abstract Event createEvent(String type, String name, String date, String time, int seats, double price, Venue venue);
    public abstract boolean bookTickets(String eventName, int numTickets, Customer[] customers);
    public abstract boolean cancelBooking(int bookingId);
    public abstract int getAvailableSeats(String eventName);
    public abstract void getEventDetails(String eventName);
}

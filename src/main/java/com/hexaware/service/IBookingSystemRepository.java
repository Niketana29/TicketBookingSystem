package com.hexaware.service;

import com.hexaware.bean.*;
import java.sql.SQLException;
import java.util.List;

public interface IBookingSystemRepository {
    Event createEvent(String name, String date, String time, int seats, double price, String type, Venue venue) throws SQLException;

    List<Event> getEventDetails() throws SQLException;

    Event getEventDetails(String eventName) throws SQLException; // ✅ ADDED

    int getAvailableNoOfTickets(String eventName) throws SQLException;

    double calculateBookingCost(Event event, int numTickets);

    boolean bookTickets(String eventName, int numTickets, List<Customer> customers) throws SQLException;

    boolean cancelBooking(int bookingId) throws SQLException;

    Booking getBookingDetails(int bookingId) throws SQLException;
}

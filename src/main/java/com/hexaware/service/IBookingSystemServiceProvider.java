package com.hexaware.service;

import com.hexaware.bean.*;
import java.util.List;

public interface IBookingSystemServiceProvider {
    double calculateBookingCost(Event event, int numTickets);

    boolean bookTickets(String eventName, int numTickets, List<Customer> customers) throws EventNotFoundException;

    boolean cancelBooking(int bookingId) throws InvalidBookingIDException;

    void getBookingDetails(int bookingId) throws InvalidBookingIDException;
}

package com.hexaware.bean;

import com.hexaware.service.*;
import java.util.*;

public class BookingSystemServiceProviderImpl extends EventServiceProviderImpl implements IBookingSystemServiceProvider {
    private Map<Integer, Booking> bookings = new HashMap<>();

    @Override
    public boolean bookTickets(String eventName, int numTickets, List<Customer> customers) throws EventNotFoundException {
        Event event = getEventByName(eventName);
        if (event == null) {
            throw new EventNotFoundException("Event '" + eventName + "' not found.");
        }

        Booking booking = new Booking(customers, event, numTickets); // List passed to Booking constructor
        if (booking.bookTickets()) {
            bookings.put(booking.getBookingId(), booking);
            return true;
        }
        return false;
    }

    @Override
    public boolean cancelBooking(int bookingId) throws InvalidBookingIDException {
        Booking booking = bookings.get(bookingId);
        if (booking == null) {
            throw new InvalidBookingIDException("Booking ID " + bookingId + " is invalid.");
        }

        booking.cancelBooking();
        bookings.remove(bookingId);
        return true;
    }

    @Override
    public void getBookingDetails(int bookingId) throws InvalidBookingIDException {
        Booking booking = bookings.get(bookingId);
        if (booking == null) {
            throw new InvalidBookingIDException("Booking ID " + bookingId + " is invalid.");
        }
        booking.displayBookingDetails();
    }

    @Override
    public double calculateBookingCost(Event event, int numTickets) {
        return event.getTicketPrice() * numTickets;
    }
}

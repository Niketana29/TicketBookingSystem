package com.hexaware.bean;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Booking {
    private static int bookingCounter = 1;

    private int bookingId;
    private Map<String, Customer> customers; // email → customer
    private Event event;
    private int numTickets;
    private double totalCost;
    private LocalDateTime bookingDate;

    public Booking(List<Customer> customerList, Event event, int numTickets) {
        this.bookingId = bookingCounter++;
        this.customers = new HashMap<>();
        for (Customer customer : customerList) {
            customers.put(customer.getEmail(), customer); // email as key
        }
        this.event = event;
        this.numTickets = numTickets;
        this.totalCost = event.getTicketPrice() * numTickets;
        this.bookingDate = LocalDateTime.now();
    }

    public int getBookingId() {
        return bookingId;
    }

    public void cancelBooking() {
        event.cancelBooking(numTickets);
        System.out.println("Booking canceled. Tickets refunded.");
    }

    public boolean bookTickets() {
        return event.bookTickets(numTickets);
    }

    public void displayBookingDetails() {
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Event: " + event.getEventName());
        System.out.println("Number of Tickets: " + numTickets);
        System.out.println("Total Cost: Rs." + totalCost);
        System.out.println("Booking Date: " + bookingDate);
        System.out.println("Customers:");
        for (Customer customer : customers.values()) {
            customer.displayCustomerDetails();
        }
    }
}

package com.hexaware.ticketbooking;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Available Tickets
        System.out.print("Enter available tickets: ");
        int availableTickets = scanner.nextInt();

        // Input: Number of tickets user wants to book
        System.out.print("Enter number of tickets to book: ");
        int noOfBookingTickets = scanner.nextInt();

        // Conditional logic for ticket booking
        if (availableTickets >= noOfBookingTickets) {
            availableTickets -= noOfBookingTickets;
            System.out.println("Booking successful! Remaining tickets: " + availableTickets);
        } else {
            System.out.println("Ticket unavailable! Only " + availableTickets + " tickets left.");
        }

        scanner.close();
    }
}
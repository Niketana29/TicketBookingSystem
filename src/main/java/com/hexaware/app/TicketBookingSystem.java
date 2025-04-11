package com.hexaware.app;

import com.hexaware.bean.*;
import com.hexaware.service.*;

import java.sql.SQLException;
import java.util.*;

public class TicketBookingSystem {
    public static void main(String[] args) {
        IBookingSystemRepository system = new BookingSystemRepositoryImpl(); // JDBC version
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n====== Ticket Booking System ======");
            System.out.println("1. Create Event");
            System.out.println("2. Book Tickets");
            System.out.println("3. Cancel Tickets");
            System.out.println("4. View Available Seats");
            System.out.println("5. View Event Details");
            System.out.println("6. Exit");
            System.out.println("7. View All Events (Sorted)");
            System.out.println("8. View Booking Details");


            System.out.print("Enter choice: ");
            String choice = sc.nextLine();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("Enter event type (Movie/Concert/Sports): ");
                        String type = sc.nextLine();
                        System.out.print("Enter event name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter event date (yyyy-mm-dd): ");
                        String date = sc.nextLine();
                        System.out.print("Enter event time (HH:mm): ");
                        String time = sc.nextLine();
                        System.out.print("Enter venue name: ");
                        String venueName = sc.nextLine();
                        System.out.print("Enter venue address: ");
                        String venueAddress = sc.nextLine();
                        Venue venue = new Venue(venueName, venueAddress);
                        System.out.print("Enter total seats: ");
                        int seats = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter ticket price: ");
                        double price = Double.parseDouble(sc.nextLine());

                        system.createEvent(name, date, time, seats, price, type, venue);
                        System.out.println("Event created and saved to database successfully.");
                        break;

                    case "2":
                        System.out.print("Enter event name to book: ");
                        String bookEvent = sc.nextLine();
                        System.out.print("Enter number of tickets: ");
                        int ticketsToBook = Integer.parseInt(sc.nextLine());
                        List<Customer> customers = new ArrayList<>();
                        for (int i = 0; i < ticketsToBook; i++) {
                            System.out.println("Enter details for customer " + (i + 1));
                            System.out.print("Name: ");
                            String cname = sc.nextLine();
                            System.out.print("Email: ");
                            String cemail = sc.nextLine();
                            System.out.print("Phone: ");
                            String cphone = sc.nextLine();
                            customers.add(new Customer(cname, cemail, cphone));
                        }
                        if (system.bookTickets(bookEvent, ticketsToBook, customers)) {
                            System.out.println("Booking successful and saved in database.");
                        } else {
                            System.out.println("Booking failed.");
                        }
                        break;

                    case "3":
                        System.out.print("Enter Booking ID to cancel: ");
                        int bookingId = Integer.parseInt(sc.nextLine());
                        if (system.cancelBooking(bookingId)) {
                            System.out.println("Booking canceled and updated in database.");
                        }
                        break;

                    case "4":
                        System.out.print("Enter event name: ");
                        String eName = sc.nextLine();
                        int available = system.getAvailableNoOfTickets(eName);
                        if (available >= 0)
                            System.out.println("Available seats: " + available);
                        else
                            System.out.println("Event not found.");
                        break;

                    case "5":
                        System.out.print("Enter event name: ");
                        String eventName = sc.nextLine();
                        system.getEventDetails(eventName);
                        break;

                    case "6":
                        System.out.println("Exiting... Thank you!");
                        sc.close();
                        return;

                    case "7":
                        List<Event> events = system.getEventDetails();
                        if (events.isEmpty()) {
                            System.out.println("No events found.");
                        } else {
                            System.out.println("----- Events from Database -----");
                            for (Event e : events) {
                                e.displayEventDetails();
                                e.getVenue().displayVenueDetails();
                            }
                        }
                        break;

                    case "8":
                        System.out.print("Enter Booking ID to view: ");
                        int viewBookingId = Integer.parseInt(sc.nextLine());
                        Booking booking = system.getBookingDetails(viewBookingId);
                        if (booking != null) {
                            booking.displayBookingDetails();
                        } else {
                            System.out.println("No booking found with ID " + viewBookingId);
                        }
                        break;
                    

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (NullPointerException e) {
                System.out.println("NullPointerException occurred: " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }
}

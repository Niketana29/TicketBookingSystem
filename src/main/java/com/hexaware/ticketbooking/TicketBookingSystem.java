package com.hexaware.ticketbooking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicketBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an Event
        Event event = new Concert("Music Concert", LocalDate.of(2025, 4, 15), LocalTime.of(18, 30), "Grand Hall", 100, 1500.0, "John Doe");


        // Create a Venue
        Venue venue = new Venue("Grand Hall", "123 Main Street, City");

        // Create a Customer
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();
        Customer customer = new Customer(name, email, phone);

        // Display Customer Details
        System.out.println("\nCustomer Details:");
        customer.displayCustomerDetails();

        // Booking System Menu
        while (true) {
            System.out.println("\n=============================");
            System.out.println("    TICKET BOOKING MENU   ");
            System.out.println("=============================");
            System.out.println("1 View Event Details");
            System.out.println("2 Book Tickets");
            System.out.println("3 Cancel Booking");
            System.out.println("4 Choose Ticket Category");
            System.out.println("5 Show Total Revenue");
            System.out.println("6 Exit");
            System.out.print(" Choose an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println(" Invalid input! Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    event.displayEventDetails();
                    venue.displayVenueDetails();
                    break;
                case 2:
                    System.out.print("Enter number of tickets to book: ");
                    int numTickets;
                    try {
                        numTickets = scanner.nextInt();
                        scanner.nextLine();
                        if (numTickets <= 0) throw new IllegalArgumentException("Number of tickets must be greater than 0.");
                    } catch (InputMismatchException e) {
                        System.out.println(" Invalid input! Please enter a valid number.");
                        scanner.nextLine();
                        continue;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    Booking booking = new Booking(event, numTickets);
                    if (booking.bookTickets()) {
                        System.out.println(" Booking confirmed for " + numTickets + " tickets!");
                    } else {
                        System.out.println(" Not enough seats available. Booking failed.");
                    }
                    break;
                case 3:
                    System.out.print("Enter number of tickets to cancel: ");
                    int cancelTickets;
                    try {
                        cancelTickets = scanner.nextInt();
                        scanner.nextLine();
                        if (cancelTickets <= 0) throw new IllegalArgumentException("Number of tickets must be greater than 0.");
                    } catch (InputMismatchException e) {
                        System.out.println(" Invalid input! Please enter a valid number.");
                        scanner.nextLine();
                        continue;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    event.cancelBooking(cancelTickets);
                    System.out.println(" Successfully canceled " + cancelTickets + " tickets.");
                    break;
                case 4:
                    // Ticket Category Selection
                    System.out.println("\n Choose a ticket category:");
                    System.out.println("1 Silver - Rs.500");
                    System.out.println("2 Gold - Rs.1000");
                    System.out.println("3 Diamond - Rs.2000");
                    System.out.print(" Enter your choice: ");

                    int ticketPrice = 0;
                    String categoryChoice = scanner.nextLine();
                    switch (categoryChoice) {
                        case "1":
                            ticketPrice = 500;
                            break;
                        case "2":
                            ticketPrice = 1000;
                            break;
                        case "3":
                            ticketPrice = 2000;
                            break;
                        default:
                            System.out.println(" Invalid choice. Please select a valid category.");
                            continue;
                    }

                    // Ask for number of tickets
                    System.out.print(" Enter the number of tickets you want to book: ");
                    int categoryTickets;
                    try {
                        categoryTickets = scanner.nextInt();
                        scanner.nextLine();
                        if (categoryTickets <= 0) throw new IllegalArgumentException("Number of tickets must be greater than 0.");
                    } catch (InputMismatchException e) {
                        System.out.println(" Invalid input! Please enter a valid number.");
                        scanner.nextLine();
                        continue;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    // Calculate total cost
                    int totalCost = ticketPrice * categoryTickets;
                    System.out.println(" Total cost for " + categoryTickets + " tickets: Rs." + totalCost);
                    break;
                case 5:
                    System.out.println("\n Total Revenue: Rs." + event.calculateTotalRevenue());
                    break;
                case 6:
                    System.out.println(" Thank you for using the Ticket Booking System! Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println(" Invalid choice. Try again.");
            }
        }
    }
}

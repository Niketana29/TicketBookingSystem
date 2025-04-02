package com.hexaware.ticketbooking;

public class Customer {
    private String customerName;
    private String email;
    private String phoneNumber;

    // Default constructor
    public Customer() {}

    // Parameterized constructor
    public Customer(String customerName, String email, String phoneNumber) {
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters & Setters
    public String getCustomerName() { return customerName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }

    // Display customer details
    public void displayCustomerDetails() {
        System.out.println("Customer: " + customerName + " | Email: " + email + " | Phone: " + phoneNumber);
    }
}


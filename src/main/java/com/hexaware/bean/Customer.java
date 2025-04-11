package com.hexaware.bean;

import java.util.Objects;

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

    // Getters and Setters
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    // Display
    public void displayCustomerDetails() {
        System.out.println("Customer: " + customerName + " | Email: " + email + " | Phone: " + phoneNumber);
    }

    // Override equals() and hashCode() to prevent duplicates in Set
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Customer other = (Customer) obj;
        return Objects.equals(email.toLowerCase(), other.email.toLowerCase()); // email is unique
    }

    @Override
    public int hashCode() {
        return Objects.hash(email.toLowerCase());
    }
}

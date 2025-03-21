CREATE DATABASE TicketBookingSystem;
USE TicketBookingSystem;

CREATE TABLE Venue (
    venue_id INT AUTO_INCREMENT PRIMARY KEY,
    venue_name VARCHAR(255) NOT NULL,
    address TEXT NOT NULL
);

CREATE TABLE Event (
    event_id INT AUTO_INCREMENT PRIMARY KEY,
    event_name VARCHAR(255) NOT NULL,
    event_date DATE NOT NULL,
    event_time TIME NOT NULL,
    venue_id INT NOT NULL,
    total_seats INT NOT NULL,
    available_seats INT NOT NULL,
    ticket_price DECIMAL(10,2) NOT NULL,
    event_type ENUM('Movie', 'Sports', 'Concert') NOT NULL,
    FOREIGN KEY (venue_id) REFERENCES Venue(venue_id) ON DELETE CASCADE
);

CREATE TABLE Customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(15) UNIQUE NOT NULL
);

CREATE TABLE Booking (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    event_id INT NOT NULL,
    num_tickets INT NOT NULL,
    total_cost DECIMAL(10,2) NOT NULL,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (event_id) REFERENCES Event(event_id) ON DELETE CASCADE
);

SHOW TABLES;

INSERT INTO Venue (venue_name, address) VALUES
('Madison Square Garden', '4 Pennsylvania Plaza, New York, NY'),
('Staples Center', '1111 S Figueroa St, Los Angeles, CA'),
('Wembley Stadium', 'London HA9 0WS, United Kingdom'),
('Sydney Opera House', 'Bennelong Point, Sydney, Australia'),
('Tokyo Dome', '1-3-61 Koraku, Bunkyo City, Tokyo, Japan'),
('MGM Grand Garden Arena', 'Las Vegas, NV'),
('Mercedes-Benz Stadium', 'Atlanta, GA'),
('O2 Arena', 'Peninsula Square, London, UK'),
('United Center', '1901 W Madison St, Chicago, IL'),
('Madison Square Garden', 'New York, NY');

INSERT INTO Event (event_name, event_date, event_time, venue_id, total_seats, available_seats, ticket_price, event_type) VALUES
('Rock Concert', '2025-04-10', '19:00:00', 1, 20000, 15000, 1200.00, 'Concert'),
('Jazz Night', '2025-05-15', '20:00:00', 2, 15000, 10000, 1500.00, 'Concert'),
('World Cup Final', '2025-06-30', '17:00:00', 3, 80000, 50000, 5000.00, 'Sports'),
('Movie Premiere', '2025-03-25', '18:30:00', 4, 1000, 500, 900.00, 'Movie'),
('Classic Opera', '2025-07-10', '19:00:00', 5, 5000, 2500, 2000.00, 'Concert'),
('Comedy Night', '2025-08-20', '21:00:00', 6, 800, 400, 1200.00, 'Movie'),
('Basketball Finals', '2025-09-05', '19:30:00', 7, 30000, 25000, 2500.00, 'Sports'),
('Live Orchestra', '2025-10-15', '18:00:00', 8, 4000, 3500, 1600.00, 'Concert'),
('Football Championship', '2025-11-25', '16:00:00', 9, 75000, 70000, 4500.00, 'Sports'),
('Broadway Show', '2025-12-10', '20:00:00', 10, 2000, 1800, 1300.00, 'Movie');

INSERT INTO Customer (customer_name, email, phone_number) VALUES
('Alice Johnson', 'alice.johnson@example.com', '9876543210'),
('Bob Smith', 'bob.smith@example.com', '9876500000'),
('Charlie Brown', 'charlie.brown@example.com', '9123456789'),
('David Wilson', 'david.wilson@example.com', '9988776655'),
('Emma Watson', 'emma.watson@example.com', '9000000000'),
('Franklin White', 'franklin.white@example.com', '9012345678'),
('Grace Hall', 'grace.hall@example.com', '9876598765'),
('Harry Lewis', 'harry.lewis@example.com', '8765432109'),
('Isabella Clark', 'isabella.clark@example.com', '9876549876'),
('Jack Miller', 'jack.miller@example.com', '9012987654');

INSERT INTO Booking (customer_id, event_id, num_tickets, total_cost) VALUES
(1, 1, 2, 2400.00),
(2, 2, 4, 6000.00),
(3, 3, 6, 30000.00),
(4, 4, 1, 900.00),
(5, 5, 3, 6000.00),
(6, 6, 5, 6000.00),
(7, 7, 2, 5000.00),
(8, 8, 8, 12800.00),
(9, 9, 7, 31500.00),
(10, 10, 3, 3900.00);

SELECT * FROM Event;

SELECT * FROM Event WHERE available_seats > 0;

SELECT * FROM Event WHERE event_name LIKE '%cup%';

SELECT * FROM Event WHERE ticket_price BETWEEN 1000 AND 2500;

SELECT * FROM Event WHERE event_date BETWEEN '2025-05-01' AND '2025-09-01';

SELECT * FROM Event WHERE available_seats > 0 AND event_type = 'Concert';

SELECT * FROM Customer LIMIT 5 OFFSET 5;

SELECT * FROM Booking WHERE num_tickets > 4;

SELECT * FROM Customer WHERE phone_number LIKE '%000';

SELECT * FROM Event WHERE total_seats > 15000 ORDER BY total_seats DESC;

SELECT * FROM Event WHERE event_name NOT LIKE 'x%' 
AND event_name NOT LIKE 'y%' 
AND event_name NOT LIKE 'z%';
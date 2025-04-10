DROP DATABASE IF EXISTS golf_club;
CREATE DATABASE golf_club;
USE golf_club;

DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS tournaments;
DROP TABLE IF EXISTS tournament_members;

CREATE TABLE members (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_name VARCHAR(100) NOT NULL,
    member_address VARCHAR(255) NOT NULL,
    member_email VARCHAR(100) UNIQUE NOT NULL,
    member_phone VARCHAR(20) UNIQUE NOT NULL,
    member_start_date DATE NOT NULL
);

CREATE TABLE tournaments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    location VARCHAR(255) NOT NULL,
    entry_fee DECIMAL(10,2) NOT NULL,
    cash_prize_amount DECIMAL(10,2) NOT NULL,
    CONSTRAINT unique_tournament_dates UNIQUE (start_date, end_date)
);

CREATE TABLE tournament_members (
    tournament_id BIGINT NOT NULL,
    member_id BIGINT NOT NULL,
    PRIMARY KEY (tournament_id, member_id),
    FOREIGN KEY (tournament_id) REFERENCES tournaments(id) ON DELETE CASCADE,
    FOREIGN KEY (member_id) REFERENCES members(id) ON DELETE CASCADE
);

INSERT INTO members (member_name, member_address, member_email, member_phone, member_start_date) VALUES
('John Doe', '123 Maple Street, Toronto', 'johndoe1@email.com', '647-555-1234', '2024-01-10'),
('Jane Smith', '456 Oak Avenue, Vancouver', 'janesmith@email.com', '604-555-5678', '2023-12-15'),
('Robert Brown', '789 Pine Road, Calgary', 'robertbrown@email.com', '403-555-9012', '2024-02-20'),
('Emily Davis', '123 Maple Street, Toronto', 'emilydavis@email.com', '647-555-3456', '2024-03-05'),
('Michael Wilson', '456 Oak Avenue, Vancouver', 'michaelwilson@email.com', '604-555-7890', '2024-04-12'),
('Sarah Johnson', '101 Birch Lane, Montreal', 'sarahjohnson@email.com', '514-555-1234', '2023-11-25'),
('David Lee', '789 Pine Road, Calgary', 'davidlee@email.com', '403-555-5678', '2024-05-10'),
('Sophia Martinez', '202 Cedar Street, Ottawa', 'sophiamartinez@email.com', '613-555-2345', '2024-06-08'),
('James White', '101 Birch Lane, Montreal', 'jameswhite@email.com', '514-555-6789', '2023-10-30'),
('Olivia Hall', '202 Cedar Street, Ottawa', 'oliviahall@email.com', '613-555-7891', '2024-07-15');

INSERT INTO tournaments (start_date, end_date, location, entry_fee, cash_prize_amount) VALUES
('2024-08-01', '2024-08-05', 'Toronto', 50.00, 1000.00),
('2024-09-10', '2024-09-15', 'Vancouver', 75.00, 1500.00),
('2024-10-05', '2024-10-10', 'Calgary', 60.00, 1200.00),
('2024-11-20', '2024-11-25', 'Montreal', 80.00, 2000.00),
('2024-12-15', '2024-12-20', 'Ottawa', 65.00, 1300.00);

INSERT INTO tournament_members (tournament_id, member_id) VALUES
(1, 1), (1, 3), (1, 5), (1, 7), (1, 9),  -- Tournament 1 participants
(2, 2), (2, 4), (2, 6), (2, 8), (2, 10), -- Tournament 2 participants
(3, 1), (3, 2), (3, 5), (3, 6), (3, 9),  -- Tournament 3 participants
(4, 3), (4, 4), (4, 7), (4, 8), (4, 10), -- Tournament 4 participants
(5, 1), (5, 2), (5, 3), (5, 4), (5, 5);  -- Tournament 5 participants
# Plane Management System

## Overview
The Plane Management System is a Java application designed to manage and track seat reservations for a private plane. It includes features for buying, canceling, and displaying seat reservations, as well as managing passenger ticket information.

## Features
- **Buy a Seat**: Purchase a seat by specifying the row and seat number.
- **Cancel a Seat**: Cancel a seat reservation by specifying the row and seat number.
- **Find First Available Seat**: Locate the first available seat starting from row A.
- **Show Seating Plan**: Display the current seating plan showing available and sold seats.
- **Print Tickets Information**: Print information of all tickets sold during the session and calculate the total price.
- **Search for a Ticket**: Search for a ticket by row and seat number to check if it's sold and display the ticket information.
- **Save Ticket Information**: Save the ticket information to a file when a ticket is sold.

## Classes and Methods

### Main Class: PlaneManagement
- **buy_seat()**: Prompts user for row and seat number, checks availability, and records the seat as sold.
- **cancel_seat()**: Prompts user for row and seat number, checks if the seat is sold, and records the seat as available.
- **find_first_available()**: Finds the first available seat starting from row A.
- **show_seating_plan()**: Displays the seating plan with 'O' for available seats and 'X' for sold seats.
- **print_tickets_info()**: Prints all sold ticket information and calculates the total price of sold tickets.
- **search_ticket()**: Prompts user for row and seat number, searches for the ticket, and displays ticket and person information if found.

### Class: Person
- **Attributes**: name, surname, email.
- **Methods**: Constructor, getters, setters, printPersonInfo().

### Class: Ticket
- **Attributes**: row, seat, price, person (Person object).
- **Methods**: Constructor, getters, setters, printTicketInfo(), save().

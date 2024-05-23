import java.util.Scanner;

/**
 * Created a class named PlaneManagement.
 * Starts with the display message.
 * Initializes the seats as available.
 */
public class PlaneManagement {
    private static final int ROWS = 4;   // Defining a constant for rows
    private static final int[] SEATS_PER_ROW = new int[]{14, 12, 12, 14};   // Defining an array for the seat capacities of each rows
    private static final char[] ROW_LABELS = new char[]{'A', 'B', 'C', 'D'};   // Defining an array for the seat rows
    private static int[][] seats;   // Defining an array to keep track of the seats availability
    private static Ticket[] tickets;   /* Task 09 - Defining an array to store all the tickets sold in the session */
    private static int ticket_count = 0;   // Defining a variable to keep track of the ticket counts

    /**
     * Displays the menu and asks the user for input.
     * Option 0 terminates the program.
     * @param args
     */
    public static void main(String[] args) {
        initialize_seats();
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Plane Management application");
        int option = 7;   // INITIALIZED TO KEEP THE LOOP ON
        do {
            try {
                System.out.println();
                System.out.println("""
                        ******************************************************
                        *                   MENU OPTIONS                     *
                        ******************************************************
                            1) Buy a seat
                            2) Cancel a seat
                            3) Find first available seat
                            4) Show seating plan
                            5) Print tickets information and total sales
                            6) Search ticket
                            0) Quit
                        ******************************************************
                        """);
                System.out.print("Please select an option: ");
                option = input.nextInt();
                System.out.println();   //Code Clarity

                switch (option) {
                    case 0:
                        System.out.println("Exiting the program.");
                        break;
                    case 1:
                        buy_seat(input);
                        break;
                    case 2:
                        cancel_seat(input);
                        break;
                    case 3:
                        find_first_available();
                        break;
                    case 4:
                        show_seating_plan();
                        break;
                    case 5:
                        print_tickets_info();
                        break;
                    case 6:
                        search_ticket(input);
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            }
            catch (Exception e){
                System.out.println("\nError: Invalid option.");
                input.nextLine();   // Clears the input buffer
            }
        } while (option != 0);   // Quits the loop only when 0 is entered
    }

    /**
     * Task 03
     * @param input
     * Asks the User to enter the seat details.
     * Asks the user to enter their personal details.
     * Records the seat as sold if it's available.
     */
    private static void buy_seat(Scanner input) {
        try {
            int[] input_values = get_input(input);   // Asks the user to enter the seat details.
            char row_letter = (char) input_values[0];
            int row_index = input_values[1];
            int seat_number = input_values[2];

            /*
              Task 09.1
              Asks the User's personal details.
              Creates a new ticket.
              Adds the ticket to the array.
             */
            if (seats[row_index][seat_number]==0) {
            System.out.println();   // Code clarity
            System.out.print("Enter name: ");
            String name = input.next();
            System.out.print("Enter surname: ");
            String surname = input.next();
            System.out.print("Enter email: ");
            String email = input.next();

                Person person = new Person(name, surname, email);   // Creates a new person in Person class
                double price = seat_price(seat_number);   // gets the seat price
                Ticket ticket = new Ticket(row_letter, seat_number + 1, price, person);  // Creates a new ticket
                tickets[ticket_count++] = ticket;   // Stores the ticket object to the array and increments the ticketCount value
                seats[row_index][seat_number] = 1;   // Save the seat as sold
                ticket.save();   // Creates a .txt file
                System.out.println("\nSeat " + row_letter + (seat_number+1) + " is successfully purchased.");
            }
            else {
                System.out.println("Seat " + row_letter + (seat_number+1) + " has already been booked");
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            input.nextLine();
        }
    }

    /**
     * Task 04
     * @param input
     * Asks the User to enter the seat details.
     * Records the seat as available if it's already sold.
     * Removes the reference of the ticket if available.
     */
    private static void cancel_seat(Scanner input) {
        try {
            int[] input_values = get_input(input);   // Asks the user to enter the seat details.
            char row_letter = (char) input_values[0];
            int row_index = input_values[1];
            int seat_number = input_values[2];

            /*
              Task 09.2
              Removes the ticket from the array.
              Deletes the text file.
             */
            if (seats[row_index][seat_number]==1) {
                for (int i = 0; i < ticket_count; ++i) {   // Checks if the row and seat matches
                    if (tickets[i] != null && tickets[i].getRow() == row_letter && tickets[i].getSeat() == seat_number + 1) {
                        tickets[i].delete();   // Delete the saved .txt file
                        tickets[i] = null;   // Remove the reference of this ticket from the array
                        ticket_count--;
                        for (int j = i; j<ticket_count;++j){
                            tickets[j] = tickets[j + 1];
                        }
                        seats[row_index][seat_number] = 0;   // Save the seat as available
                        System.out.println("\nSeat " + row_letter + (seat_number+1) + " has been cancelled.");
                        return;
                    }
                }
                System.out.println("\nTicket not found.");
            }
            else {
                System.out.println("Seat is available.");
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            input.nextLine();
        }
    }


    /**
     * Task 05
     * Finds the first seat which is still available.
     */
    private static void find_first_available() {
        for(int i = 0; i < ROWS; ++i) {   // Iterates through the rows
            for(int j = 0; j < SEATS_PER_ROW[i]; ++j) {   // iterates through the seats
                if (seats[i][j] == 0) {
                    System.out.println("First available seat: Row " + ROW_LABELS[i] + ", Seat " + (j + 1));
                    return;
                }
            }
        }
        System.out.println("No available seats.");
    }

    /**
     * Task 06
     * Shows the seats that are available and sold.
     * "0" indicates available.
     * "X" indicates sold.
     */
    private static void show_seating_plan() {
        System.out.println("Seating Plan:\n");

        for(int i = 0; i < ROWS; ++i) {
            System.out.print(ROW_LABELS[i] + " ");
            for(int j = 0; j < SEATS_PER_ROW[i]; ++j) {
                if (seats[i][j] == 0) {
                    System.out.print("O ");   // Available seats
                }
                else {
                    System.out.print("X ");   // Booked seats
                }
            }
            System.out.println();   // Divides the rows

            if (i == 1) {
                System.out.println();   // Have a space between row B and C to match the seating plan
            }
        }

    }

    /**
     * Task 10
     * Prints the information of all the tickets that have been sold in the session
     * Calculates the total price of the tickets
     */
    private static void print_tickets_info() {
        double total_price = 0.0;   // Defining total price of the ticket

        for(int i = 0; i < ticket_count; ++i) {   // iterates over the array of tickets
            if (tickets[i] != null) {   // Ensure only valid ticket objects are processed
                total_price += tickets[i].getPrice();   //Adds up the booked ticket prices
                tickets[i].print_ticket();   // Prints the ticket information
                System.out.println();   // Code clarity
            }
        }
        System.out.println("Total price of tickets sold: £" + total_price);
    }

    /**
     * Task 11
     * @param input
     * Asks the user to enter seat details.
     * Searches if the ticket is sold.
     * If it's sold, then prints the information of ticket.
     */
    private static void search_ticket(Scanner input) {
        try {
            int[] input_values = get_input(input);   // Asks the user to enter the seat details.
            char row_letter = (char) input_values[0];
            int seat_number = input_values[2];

            System.out.println();   // Code clarity
            for(int i = 0; i < ticket_count; ++i) {   // Checks if the seat is available
                if (tickets[i] != null && tickets[i].getRow() == row_letter && tickets[i].getSeat() == seat_number + 1) {
                    System.out.println("Ticket found:");
                    tickets[i].print_ticket();   // Prints the ticket information
                    return;
                }
            }
            System.out.println("No Ticket found.");
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            input.nextLine();
        }
    }

    /**
     * Make sure that all the seats are available before the start of the program.
     * Will be called at the start of the program.
     */
    private static void initialize_seats() {
        seats = new int[ROWS][];   // Declare a new array of seats

        for(int i = 0; i < ROWS; ++i) {
            seats[i] = new int[SEATS_PER_ROW[i]];
            for (int j = 0; j < SEATS_PER_ROW[i]; ++j) {
                seats[i][j] = 0;   // Iterates through the seats and make it available
            }
        }
        // Creates a new ticket with the total number of seats
        tickets = new Ticket[SEATS_PER_ROW[0] + SEATS_PER_ROW[1] + SEATS_PER_ROW[2] + SEATS_PER_ROW[3]];
    }

    /**
     * Save the price of the seat based on its seat number.
     * @return price
     */
    private static double seat_price(int seat_number){
        double price = 0.0;
        if (seat_number >= 9 && seat_number <= 13) {
            price = 180.0;
        }
        else if (seat_number >= 5 && seat_number <= 8) {
            price = 150.0;
        }
        else if (seat_number >= 0 && seat_number <= 4) {
            price = 200.0;
        }
        return price;   // Returns price
    }

    /**
     * Asks the user to input the seat details and validates it.
     * @param input
     * @return int[]
     */
    private static int[] get_input(Scanner input){
        char row_letter = 0;
        String row_input;
        int row_index = 0;
        int seat_number = 0;
        boolean valid_input = false;   // Loops until a valid input has entered
        while (!valid_input) {
            System.out.print("Enter row: ");
            row_input = input.next().toUpperCase();
            if (!(row_input.length() == 1)) {
                System.out.println("Invalid row.");
            } else {
                row_letter = row_input.charAt(0);
                row_index = row_letter - 65;   // Convert the user input of row letters into numeric indices
                if (row_index >= 0 && row_index < ROWS) {   // Validates the row_letter
                    System.out.print("Enter seat number: ");
                    if (input.hasNextInt()) {
                        seat_number = input.nextInt() - 1;   // Make it access through indexing
                        if (seat_number >= 0 && seat_number < SEATS_PER_ROW[row_index]) {   // Validates the seat_number
                            valid_input = true;   // Ends the loop
                        } else {
                            System.out.println("Invalid seat number.");
                        }
                    } else {
                        System.out.println("Invalid seat number.");
                        input.next(); // Consume invalid input
                    }
                } else {
                    System.out.println("Invalid row.");
                }
            }
        }
        return new int[]{row_letter, row_index, seat_number};   // Returns an array with the seat details
    }

}

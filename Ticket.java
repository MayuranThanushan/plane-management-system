import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
    * Task 08
    * @attributes row, seat, price, person
    * Prints the information of the ticket including the person
 */
class Ticket {
    private char row;
    private int seat;
    private double price;
    private Person person;

    public Ticket(char row, int seat, double price, Person person) {   // Creates an object of ticket
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public char getRow() {   // Returns the row character
        return row;
    }

    public void setRow(char row) {   // Modifies the row letter
        this.row = row;
    }

    public int getSeat() {   // Returns the seat number
        return seat;
    }

    public void setSeat(int seat) {   // Modifies the seat number
        this.seat = seat;
    }

    public double getPrice() {   // Returns the seat price
        return price;
    }

    public void setPrice(double price) {   // Modifies the seat price
        this.price = price;
    }

    public Person getPerson() {   // Returns the person location
        return person;
    }

    public void setPerson(Person person) {   // Modifies the person location
        this.person = person;
    }

    public void print_ticket() {   // Prints the information of the ticket including the person
        System.out.println("Ticket Information: " +
                "Row: " + getRow() +
                ", Seat: " + getSeat() +
                ", Price: £" + getPrice());
        person.print_person();   // Gets the method from Person class
    }

    /**
     * Task 12
     * Saves the ticket information including the person in a file.
     * Saves the file as same as the seat.
     */
    public void save() {
        String folder_name = "./TextFiles/Row" + row;
        String fileName = folder_name + "/" + row + seat + ".txt";
        File folder = new File(folder_name);
        if (!folder.exists()) {
            folder.mkdirs(); // Create folder and parent folders if necessary
        }
        try {
            FileWriter writer = new FileWriter(fileName);   // Create a writable file
            writer.write("Ticket Information:\n" +
                    "Row: " + row + "\n"
                    +"Seat: " + seat + "\n" +
                    "Price: £" + price + "\n" +
                    "\n" +
                    "Person Information:\n" +
                    "Name: " + person.getName() + "\n" +
                    "Surname: " + person.getSurname() + "\n" +
                    "Email: " + person.getEmail() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    /**
     * Deletes the .txt file if the user cancels the booked seat.
     */
    public void delete() {
        String folder_name = "./TextFiles/Row" + row;
        String fileName = folder_name + "/" + row + seat + ".txt";
        File file = new File(fileName);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File " + fileName + " deleted successfully.");
            } else {
                System.out.println("File " + fileName + " couldn't be deleted.");
            }
        } else {
            System.out.println("File " + fileName + " does not exist.");
        }
    }
}

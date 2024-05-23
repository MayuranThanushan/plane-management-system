/**
    * Task 07
    * @attributes name, surname, email
    * Creates an object using the user's personal details.
 */
class Person {
    private String name;
    private String surname;
    private String email;

    public Person(String name, String surname, String email) {   // Creates an object of person
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {   // Returns the user's name
        return name;
    }

    public void setName(String name) {   // Modifies the user's name
        this.name = name;
    }

    public String getSurname() {   // Returns the user's surname
        return surname;
    }

    public void setSurname(String surname) {   // Modifies the user's surname
        this.surname = surname;
    }

    public String getEmail() {   // Returns the user's email
        return email;
    }

    public void setEmail(String email) {   // Modifies the user's email
        this.email = email;
    }

    public void print_person() {   // Prints the information from the person
        System.out.println("Person Information: " +
                "Name: " + getName() +
                ", Surname: " + getSurname() +
                ", Email: " + getEmail());
    }
}
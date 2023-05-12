// Class: CS 145
// Assignment: Phonebook
// Authors: Troy Brunette, Clay Molitor
//
// Phonebook program that the user can add contacts to, search for a contact, remove contacts, and view contacts.
// It uses a LinkedList data structure and ListNode class that follows the pattern for the LinkedIntList
// from the book. The program also uses an Entry class (the data part of the ListNode) for each contact
// in the phonebook.

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RollADexManager {
    public static void main(String[] args) throws IOException {
        // Create an empty LinkedList for the phonebook
        LinkedList list = new LinkedList();

        // add pre-made contacts to the phonebook
        addEntryFromFile(list);

        // display the menu
        printMenu(menuText());
        menuSelection(list);
    } // end of main method

    public static void menuSelection(LinkedList list) throws IOException {
        Scanner input = new Scanner(System.in);
        String choice;
        System.out.print("Enter a choice:  ");
        do { // START OF MENU DO WHILE LOOP
            choice = input.next().toUpperCase();
            switch (choice) {
                case "1" -> addNewContact(input, list); // Add a new contact to the List
                case "2" -> removeContact(input, list); // Remove a contact from the list
                case "3" -> searchForContact(input, list); // Search for a contact in the list
                case "4" -> { // View all contacts
                    System.out.println("This is your current contact list");
                    System.out.println(list.toString());
                }
                case "Q" -> { // Quit the program
                    System.out.println("Good-Bye");
                }
                default -> System.out.println("Try that again");
            }
            System.out.println("(1.) Add a new contact, (2.) Remove a contact, (3.) Search for a contact, (4.) View all Contacts, (Q)uit ");
        } while (!choice.equals("Q")); // END OF LOOP
    }

    // method for adding a new contact from user input
    public static void addNewContact(Scanner input, LinkedList list) {
        System.out.println("Enter a new contact");
        System.out.print("First name: ");
        // prompt user to enter contact information
        String firstName = input.next();

        System.out.print("Last name: ");
        String lastName = input.next();

        System.out.print("Phone Number: ");
        String phoneNumber = input.next();

        System.out.print("Address: ");
        String address = input.next();
        address += input.nextLine();

        System.out.print("Zip code: ");
        String zipCode = input.next();

        System.out.print("City: ");
        String city = input.next();
        city += input.nextLine();

        System.out.print("State: ");
        String state = input.next();

        // Chaining methods technique
        list.add(new Entry()
                .setFullName(firstName, lastName)
                .newAddress(address)
                .setPhone(phoneNumber)
                .setCity(city)
                .setZipCode(zipCode)
                .setState(state));

        System.out.println("New entry added for: " + firstName + " " + lastName);
    }

    // Reads a csv file and adds the contacts to the phonebook
    public static void addEntryFromFile(LinkedList list) throws IOException {
        try {
            String path = "RollADex/src/entry.csv";
            readFile(path, list);
        } catch (IOException e) {
            System.out.println("That file was not found.");
        }
    }

    // Search phonebook for an Entry and displays it
    public static void searchForContact(Scanner input, LinkedList list) {
        System.out.println("Please enter the name you want to search for");
        String name = input.next(); // Prompt user for a name to search for
        // Adds the contacts to an ArrayList
        ArrayList<Entry> names = list.searchForName(name);
        if (names.size() > 0) {
            System.out.println("Names that start with " + name);
            // Print out the search results
            for (Entry entry : names) {
                System.out.println(entry);
            }
        } else {
            System.out.println(name + " was not found in the phonebook.");
        }
    }

    // User can remove a contact from the phonebook
    // Prints a list of contacts with an index number
    // uses try / catch to handle user input
    public static void removeContact(Scanner input, LinkedList list) {
        int num;
        System.out.println("This is your current contact list");
        System.out.println(list.toString());
        while (true) {
            System.out.print("Enter the index for the contact you want to remove: ");
            try {
                // Prompt user for an index number to remove
                num = input.nextInt();
                // Remove the contact
                list.remove(num - 1);
                System.out.println("Removing contact " + num);
                // If we got to this point without issue, we can break the loop
                break;
            } catch (Exception e) {
                System.out.println("Something went wrong, let's try that again.");
                // tell Scanner to go to next line to prevent infinite loop
                input.nextLine();
            }
        }
    }

    // Reads a csv file with contacts and adds to the phonebook list
    public static void readFile(String path, LinkedList list) throws IOException {
        Scanner scanner = new Scanner(new FileReader(path));
        // Grab each line from the file
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // Split the lines at each comma, and store them in a String array
            String[] lines = line.split(",");
            String lName = lines[0];
            String fName = lines[1];
            String city = lines[2];
            String phone = lines[3];

            // Adding new entries to the phonebook
            list.add(new Entry()
                    .setFullName(fName, lName)
                    .setPhone(phone)
                    .setCity(city));
        }

    }

    // method for storing the menu and return it
    public static ArrayList<String> menuText() {
        ArrayList<String> menu = new ArrayList<String>();
        menu.add("+-------------------------------------------------------------------------+\n" +
                "|                       Welcome to Phonebook Directory.                   |\n" +
                "+-------------------------------------------------------------------------+");
        menu.add("| #    | Menu Selection                                                |  |");
        menu.add("+======+===============================================================+==+");
        menu.add("| 1.   | Add a new contact                                             |  |");
        menu.add("+======+===============================================================+==+");
        menu.add("| 2.   | Remove a contact                                              |  |");
        menu.add("+======+===============================================================+==+");
        menu.add("| 3.   | Search for a contact                                          |  |");
        menu.add("+======+===============================================================+==+");
        menu.add("| 4.   | View all contacts                                             |  |");
        menu.add("+======+===============================================================+==+");
        return menu;
    }

    public static void printMenu(ArrayList<String> menu) {
        for (String line : menu) {
            System.out.println(line);
        }
    }
}


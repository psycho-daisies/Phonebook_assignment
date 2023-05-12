// Class: CS 145
// Assignment: Phonebook
// Authors: Troy Brunette, Clay Molitor
//
// Entry class represents a contact in a phonebook
//      - the data part of the linked list
//      - has private fields, constructor methods, getter/setter methods

public class Entry {
    // CLASS FIELDS
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String zipCode;
    private String city;
    private String state;

    // CONSTRUCTORS
    public Entry() {}

    public Entry(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public String toString() {
        return String.format("|%-18s%2$2s|", getName(), phone);
    }


    //SETTER METHODS:
    //  * each method returns the Entry object
    //  * allows for chaining methods when creating a new Entry
    public Entry setPhone(String phone) {
        this.phone = phone;
        return this;
    }
    public Entry setFullName(String firstName, String lastName) {
        
        this.firstName = firstName;
        this.lastName = lastName;
        return this;
    }
    public Entry newAddress(String address) {
        this.address = address;
        return this;
    }
    public Entry setCity(String city) {
        this.city = city;
        return this;
    }
    public Entry setState(String state) {
        this.state = state;
        return this;
    }
    public Entry setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    // GETTER METHODS ////////////////////////////////////
    public Entry getEntry() { return this; }
    public String getName() { return firstName + " " + lastName; }
    public String getPhoneNumber() { return phone; }
    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZipCode() { return zipCode; }

}

// TYPICAL SETTER METHODS - not using
//   void setPhoneNumber(String phone) {
//        this.phone = phone;
//    }
//    void setAddress(String address) {
//        this.address = address;
//    }
//    void setZipCode(String zipCode) {
//        this.zipCode = zipCode;
//    }
//    void setState(String state) {
//        this.state = state;
//    }
//    void setName(String name) {
//        this.name = name;
//    }
//    void setName(String firstName, String lastName) {
//        this.name = firstName + " " + lastName;
//    }
//    void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//    void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//    void setCity(String city) {
//        this.city = city;
//    }

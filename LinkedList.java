// Class: CS 145
// Assignment: Phonebook
// Authors: Troy Brunette, Clay Molitor
//
// This is the LinkedList data structure for the phonebook program
// It uses various methods for adding and removing items from the list
// There is a ListNode subclass located at the bottom of this file

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class LinkedList {
    private ListNode front;
    private ListNode lastNode;
    private int size;

    public LinkedList() {
        front = null;
    }
    public int getSize() {
        return size;
    }

    public String toString() {
        ListNode current = front;
        String temp = "";
        int index = 0;
        while (current != null) {
            index++;
            String entry = current.getEntry().toString();
            temp += index + " " + entry + "\n";
            current = current.next;
        }
        return temp;
    }

    // Adds the given Entry to the end of the list
    public void add(Entry entry) {
        if (front == null) {
            // adding to an empty list
            front = new ListNode(entry);
            size++;
        } else {
            // adding to the end of an existing list
            ListNode current = front;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new ListNode(entry);
            size++;
        }
    }

    // Adds the given Entry at the given index
    public void add(int index, Entry entry) {
        if (index <= 0 || index > size) {
            return;
        } else if (index == 1) {
            prepend(entry);
        } else if (index == size) {
            append(entry);
        } else {
            // inserting into an existing list
            ListNode current = front;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = new ListNode(entry, current.next);
            size++;
        }
    }

    // Inserts the given Entry at the given index
    // Precondition: 0 <= index < size()
    public void insertAt(int index, Entry entry) {
        if (index == 0) {
            // adding to an empty list
            front = new ListNode(entry, front);
        } else {
            // inserting into an existing list
            ListNode current = front;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = new ListNode(entry, current.next);
        }
    }

    public void prepend(Entry entry) {
        ListNode current = new ListNode(entry);
        if (size == 0) {
            front.next = current;
            lastNode = current;
            size++;
        } else {
            ListNode temp = front.next;
            front.next = current;
            current.next = temp;
            size++;
        }
    }

    public void append(Entry entry) {
        ListNode current = new ListNode(entry);
        if (size == 0) {
            front.next = current;
            lastNode = current;
            size++;
        } else {
            lastNode.next = current;
            lastNode = current;
            size++;
        }
    }

    // Removes and return the first value
    // Throws a NoSuchElementException on empty list.
    public Entry remove() {
        if (front == null) {
            throw new NoSuchElementException();
        } else {
            Entry result = front.data;
            front = front.next;
            return result;
        }
    }

    // Removes Entry at the given index from list.
    // Precondition: 0 <= index < size
    public void remove(int index) {
        if (index == 0) {
            // special case: removing first element
            front = front.next;
        } else {
            // removing from elsewhere in the list
            ListNode current = front;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            size--;
        }
    }

    // Removes the first Entry in the list
    public void removeFirst() {
        if (size != 0) {
            front.next = front.next.next;
            size--;
        }
    }

    // Removes the last Entry in the list
    public void removeLast() {
        if (size == 1) {
            front.next = null;
            lastNode = front;
            size--;
        } else if (size != 0) {
            ListNode current = front.next;
            int count = 1;
            while (count != size - 1) {
                current = current.next;
                count++;
            }
            lastNode = current;
            lastNode.next = null;
            size--;
        }
    }

    // Returns value in list at given index
    // Precondition: 0 <= index < size()
    public Entry get(int index) {
        ListNode current = front;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public int indexOf(String name) {
        int index = 0;
        ListNode current = front;
        while (current != lastNode) {
            if (current.getEntry().getName().equals(name)) {
                return index;
            }

            current = current.next;
            index++;
        }
        return -1;
    }

    // Searches the phonebook for a name
    // Returns a list of all the names that match
    // the given search String
    public ArrayList<Entry> searchForName(String name) {
        ArrayList<Entry> phoneList = new ArrayList<>();

        int index = 0;
        ListNode current = front;
        while (current != lastNode) {
            if (current.getEntry().getName().contains(name)) {
                phoneList.add(current.getEntry());
            }
            current = current.next;
            index++;
        }
        if (phoneList.size() != 0) {
            return phoneList;
        } else {
            return new ArrayList<>();
        }
    }

    // ListNode implementation:
    // has two fields:
    //      - data
    //      - pointer to the next node
    // initially was in a separate java file
    // added it to the LinkedList class as a private subclass
    private class ListNode {
        Entry data;
        ListNode next;

        // CONSTRUCTORS
        public ListNode(Entry data, ListNode next) {
            this.data = data;
            this.next = next;
        }

        public ListNode(Entry data) {
            this.data = data;
            this.next = null;
        }

        // GETTER AND SETTER METHODS
        public Entry getEntry() {
            return data;
        }

        public void setEntry(Entry data) {
            this.data = data;
        }
    }
}


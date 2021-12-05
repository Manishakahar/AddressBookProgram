package com.blz.address;

import java.util.ArrayList;
import java.util.Scanner;

// Declaring Variable
public class ContactPersonDetails {
    String firstName;
    String lastName;
    String city;
    String state;
    String address;
    String emailId;
    long phoneNo;
    int zipCode;
    int count = 1;

    public void ContactPerson() {
    }

    // Display User Details
    public void addressBook(ArrayList<ContactPersonDetails> contactPerson) {
        for (int i = 0; i < contactPerson.size(); i++) {
            System.out.println("contact" + count);
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("Address: " + address);
            System.out.println("City Name : " + city);
            System.out.println("State Name : " + state);
            System.out.println("Email-Id : " + emailId);
            System.out.println("Zip Code : " + zipCode);
            System.out.println("phone Number Name : " + phoneNo);
            count++;
        }
    }

    //          Update the Contact Details
    public void updateData(String name, ArrayList<ContactPersonDetails> contactDetails) {
        for (int i = 0; i < contactDetails.size(); i++) {
            if (name.equals(contactDetails.get(i).firstName)) {
                contactDetails.remove(i);
                contactDetails.add(i, getInput());
            }
        }
        System.out.println("Record Update Successfully.......");
    }

    // Creating function to get user Input

    public ContactPersonDetails getInput() {
        ContactPersonDetails addressBookMain = new ContactPersonDetails();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the First Name : ");
        addressBookMain.firstName = sc.next();

        System.out.print("Enter the Last Name : ");
        addressBookMain.lastName = sc.next();

        System.out.print("Enter the Address: ");
        addressBookMain.address = sc.next();

        System.out.print("Enter the City Name : ");
        addressBookMain.city = sc.next();

        System.out.print("Enter the state Name : ");
        addressBookMain.state = sc.next();

        System.out.print("Enter the Email-Id : ");
        addressBookMain.emailId = sc.next();

        System.out.print("Enter the Zip Code : ");
        addressBookMain.zipCode = sc.nextInt();

        System.out.print("Enter the  Phone Number: ");
        addressBookMain.phoneNo = sc.nextLong();
        return addressBookMain;
    }
        //   Delete the Contact Details
    public void deleteRecord(String name, ArrayList<ContactPersonDetails> contactDetails) {
        if(contactDetails.size()>0) {
            for (int i = 0; i < contactDetails.size(); i++) {
                if (name.equals(contactDetails.get(i).firstName)) {
                    contactDetails.remove(i);
                }
                else System.out.println("There is no any person contact for "+name);
            }
        }
        else System.out.println("There is no any person address to delete");
        System.out.println("Record Delete Successfully.......");
    }
}

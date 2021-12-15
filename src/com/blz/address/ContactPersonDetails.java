package com.blz.address;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Map;
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

    // Display User Details
    public void addressBook(ArrayList<ContactPersonDetails> contactPerson) {
        for (int i = 0; i < contactPerson.size(); i++) {
            System.out.println("contact" +  count);
            System.out.println("First Name: "  +contactPerson.get(i).firstName);
            System.out.println("Last Name: " +contactPerson.get(i).lastName);
            System.out.println("Address: " +contactPerson.get(i).address);
            System.out.println("City Name : " + contactPerson.get(i).city);
            System.out.println("State Name : " + contactPerson.get(i).state);
            System.out.println("Email-Id : " +contactPerson.get(i).emailId);
            System.out.println("Zip Code : " + contactPerson.get(i).zipCode);
            System.out.println("phone Number Name : " + contactPerson.get(i).phoneNo);
            count++;
        }
    }

    //  Update the Contact Details
    public void updateData(String name, ArrayList<ContactPersonDetails> contactDetails) {
        for (int i = 0; i < contactDetails.size(); i++) {
            if (name.equals(contactDetails.get(i).firstName)) {
                contactDetails.remove(i);
                contactDetails.add(i, getInput());
            }
        }
        System.out.println("Record Update Successfully");
    }

    // Creating function to get user Input
    public ContactPersonDetails getInput() {
        ContactPersonDetails contactPersonDetails = new ContactPersonDetails();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the First Name : ");
        contactPersonDetails.firstName = sc.next();

        System.out.print("Enter the Last Name : ");
        contactPersonDetails.lastName = sc.next();

        System.out.print("Enter the Address: ");
        contactPersonDetails.address = sc.next();

        System.out.print("Enter the City Name : ");
        contactPersonDetails.city = sc.next();

        System.out.print("Enter the state Name : ");
        contactPersonDetails.state = sc.next();

        System.out.print("Enter the Email-Id : ");
        contactPersonDetails.emailId = sc.next();

        System.out.print("Enter the Zip Code : ");
        contactPersonDetails.zipCode = sc.nextInt();

        System.out.print("Enter the  Phone Number: ");
        contactPersonDetails.phoneNo = sc.nextLong();
        return contactPersonDetails;
    }
        //Delete the Contact Details
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
        System.out.println("Record Delete Successfully");
    }


    @Override
    public String toString() {
        return "ContactPersonDetails{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", address='" + address + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phoneNo=" + phoneNo +
                ", zipCode=" + zipCode +
                ", count=" + count +
                '}';
    }
}

package com.blz.address;

import java.util.ArrayList;
import java.util.Scanner;

// Declaring Variable
public class AddressBookMain {
    String firstName;
    String lastName;
    String city;
    String state;
    String address;
    String emailId;
    long phoneNo;
    int zipCode;
    ArrayList<AddressBookMain> contactDetails=new ArrayList<>();
    String userWish="y";
    int count=1;

    // Creating function to get user Input
    private void getInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the First Name : ");
        firstName = sc.next();

        System.out.print("Enter the Last Name : ");
        lastName = sc.next();

        System.out.print("Enter the Address: ");
        address = sc.next();

        System.out.print("Enter the City Name : ");
        city = sc.next();

        System.out.print("Enter the state Name : ");
        state = sc.next();

        System.out.print("Enter the Email-Id : ");
        emailId = sc.next();

        System.out.print("Enter the Zip Code : ");
        zipCode = sc.nextInt();

        System.out.print("Enter the  Phone Number: ");
        phoneNo = sc.nextLong();
    }
       // Display User Details
    private void displayDetails(AddressBookMain display){
        System.out.println("contact"+count);
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

    // Main Method
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");
        AddressBookMain addressBookMain=new AddressBookMain();
        Scanner userInput =new Scanner(System.in);
        while(addressBookMain.userWish.matches("y")) {
            addressBookMain.getInput();
            addressBookMain.contactDetails.add(addressBookMain);
            System.out.println("IF YOU WANT TO ADD NEW RECORD PLEASE INSERT " + "y " + "OR else " + "n");
            addressBookMain.userWish = userInput.next();
            if (addressBookMain.userWish == "n")
            {
                break;
            }
        }
        for (int i = 0; i < addressBookMain.contactDetails.size(); i++) {
            addressBookMain.displayDetails(addressBookMain.contactDetails.get(i));
        }
    }
}

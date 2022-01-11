package com.blz.address;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBookMain {

    public static ContactPersonDetails contactPersonDetails = new ContactPersonDetails();
    public static Map<String, ContactPersonDetails> addressBookHashMap = new HashMap<>();
    public static ArrayList<ContactPersonDetails> contactDetails = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);
    public static boolean flag = true;
    public static String name;
    public static int userChoice = 1;
    public String city;
    public String state;

    public static ArrayList<ContactPersonDetails> contactBookOptions() {

        // User Choose the Option

        while (flag) {
            System.out.print(" 1 - Add more contact \n2 - Edit Contact \n3 - Delete Person Contact \n4 - Show AddressBook " + "\n0 - for exist \n Enter your choice");
            userChoice = sc.nextInt();

            switch (userChoice) {
                case 1:
                    contactDetails.add(contactPersonDetails.getInput(contactDetails));
                    break;
                case 2:
                    System.out.println("Enter first name that you want to edit contactDetails");
                    name = sc.next();
                    contactPersonDetails.updateData(name, contactDetails);
                    break;
                case 3:
                    System.out.println("Enter the first name that you want to delete Contact details ");
                    name = sc.next();
                    contactPersonDetails.deleteRecord(name, contactDetails);
                    break;
                case 4:
                    System.out.println("contactDetails : " + contactDetails.size());
                    contactPersonDetails.addressBook(contactDetails);
                    break;
                case 0:
                    flag = false;
                    break;
            }
        }
        return contactDetails;
    }
}
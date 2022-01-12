package com.blz.address;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
            System.out.println("contact" + count);
            System.out.println("First Name: " + contactPerson.get(i).firstName);
            System.out.println("Last Name: " + contactPerson.get(i).lastName);
            System.out.println("Address: " + contactPerson.get(i).address);
            System.out.println("City Name : " + contactPerson.get(i).city);
            System.out.println("State Name : " + contactPerson.get(i).state);
            System.out.println("Email-Id : " + contactPerson.get(i).emailId);
            System.out.println("Zip Code : " + contactPerson.get(i).zipCode);
            System.out.println("phone Number Name : " + contactPerson.get(i).phoneNo);
            count++;
        }
    }

    //          Update the Contact Details
    public void updateData(String name, ArrayList<ContactPersonDetails> contactDetails) {
        for (int i = 0; i < contactDetails.size(); i++) {
            if (name.equals(contactDetails.get(i).firstName)) {
                contactDetails.remove(i);
                contactDetails.add(i, getInput(contactDetails));
            }
        }
        System.out.println("Record Update Successfully");
    }

    // Creating function to get user Input

    public ContactPersonDetails getInput(ArrayList<ContactPersonDetails> contactDetails) {
        ContactPersonDetails contactPersonDetails = new ContactPersonDetails();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the First Name : ");
        contactPersonDetails.firstName = sc.next();

        System.out.print("Enter the Last Name : ");
        contactPersonDetails.lastName = sc.next();
        if (count > 1) {
            checkDuplicateRecords(contactPersonDetails.firstName, contactPersonDetails.lastName, contactDetails);
        }

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
        count++;
        return contactPersonDetails;
    }

    //   Delete the Contact Details
    public void deleteRecord(String name, ArrayList<ContactPersonDetails> contactDetails) {
        if (contactDetails.size() > 0) {
            for (int i = 0; i < contactDetails.size(); i++) {
                if (name.equals(contactDetails.get(i).firstName)) {
                    contactDetails.remove(i);
                } else System.out.println("There is no any person contact for " + name);
            }
        } else System.out.println("There is no any person address to delete");
        System.out.println("Record Delete Successfully");
    }

    public void getAddressbook(Map<String, ContactPersonDetails> addressBookHashMap) {
        for (String addressbook : addressBookHashMap.keySet()) {
            System.out.println("Person contact for the " + addressbook + " is " + addressBookHashMap.get(addressbook).toString());
        }
    }

    //  Duplicate Records of the Contact Details
    public void checkDuplicateRecords(String name, String lastName, ArrayList<ContactPersonDetails> contactDetails) {
        for (int i = 0; i < contactDetails.size(); i++) {
            String existingRecordName = contactDetails.get(i).firstName + " " + contactDetails.get(i).lastName;
            String newRecordName = name + " " + lastName;
            if (newRecordName.equals(existingRecordName)) {
                System.out.println("Record with same Name already exists");
                AddressBookMain.contactBookOptions();
                break;
            } else {
                continue;
            }
        }
    }

    // Search contact Person using city name
    public static void search(String city, Map<String, ArrayList<ContactPersonDetails>> addressHashMap) {
        addressHashMap.values()
                .stream().
                forEach(value -> {
                    value.stream()
                            .forEach(person -> {
                                if (person.city.contains(city) || person.state.contains(city))
                                    System.out.println(person);
                            });
                });
    }

    // Show the Persons by City or State
    static Map<String, ContactPersonDetails> cityStateRelatedData(String cityStateName, Map<String, ArrayList<ContactPersonDetails>> addressHashMap) {
        Map<String, ContactPersonDetails> CityName = new HashMap<>();
        AtomicInteger cityCounter = new AtomicInteger(1);
        addressHashMap
                .values()
                .forEach(value -> {
                    value.forEach(person -> {
                        if (person.city.equals(cityStateName) || person.state.equals(cityStateName))
                            CityName.put((cityCounter.getAndIncrement() + ""), person);
                    });
                });
        return CityName;
    }
    //Count by City or state person data
    static int countOfContactPersonData(String cityStateName, Map<String, ArrayList<ContactPersonDetails>> addressHashMap) {
        AtomicInteger cityCounter = new AtomicInteger();
        addressHashMap
                .values()
                .forEach(value -> {
                    value.forEach(person -> {
                        if (person.city.equals(cityStateName) || person.state.equals(cityStateName))
                            cityCounter.getAndIncrement();
                    });
                });
        return cityCounter.get();
    }
    // Sort entries in address book alphabetically data
    public static void sortContactPersonDetails(List<ContactPersonDetails> contacts) {
        List<ContactPersonDetails> listObject=
                contacts.stream().sorted((o1,o2)->o1.firstName.compareTo(o2.firstName)).collect(Collectors.toList());
        for(ContactPersonDetails cp:listObject){
            System.out.println(cp);
        }
    }
    // Sort entries in address book by city,state or zipcode
    public static void sortAddressBookCityStateZip(int nextInt, List<ContactPersonDetails> contact) {
        List<ContactPersonDetails> listObject = null;
        int option = 0;
        if (option == 1) {
            listObject = contact.stream().sorted((o1,o2) -> o1.city.compareTo(o2.city)).collect(Collectors.toList());
        }
        if (option == 2) {
            listObject = contact.stream().sorted((o1,o2) -> o1.state.compareTo(o2.state)).collect(Collectors.toList());
        }
        if (option == 3) {
            listObject = contact.stream().sorted((o1, o2) -> o1.zipCode - (o2.zipCode)).collect(Collectors.toList());
        }
        for (ContactPersonDetails cp : listObject) {
            System.out.println(cp);
        }
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



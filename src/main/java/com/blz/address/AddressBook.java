package com.blz.address;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.IntStream;

public class AddressBook {
    private static ContactPersonDetails<Object> arrayList;

    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\Dell\\Desktop\\manisha\\AddressBook\\src\\main\\resources\\AddressBook";
        IntStream.range(1, 6).forEach(value -> {
            Path fileName = Paths.get(path + "/Book" + value + ".text");
            try {
                if (Files.notExists(fileName))
                    Files.createFile(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Welcome to Address Book");
        Map<String, ArrayList<AddressBookMain>> addressHashMap = new HashMap();
        Map<String, ArrayList<ContactPersonDetails>> personDetailsMap = new HashMap<>();
        ArrayList record = new ArrayList();
        Scanner sc = new Scanner(System.in);
        String bookName;
        boolean flag = true;

        while (flag) {
            System.out.println("--------------------------------------------");
            System.out.println("1 - Add more Address Book  \n2 - Edit Address Book \n3 - Delete Address Book \n4 - Show AddressBook " + "\n5 - Search Using City or State" +
                    "\n6- Show City related data" + "\n0 -  for exit \nEnter your Choice.....");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Please Enter the Address book Name");
                    bookName = sc.next();
                    addressHashMap.put(bookName, null);
                    break;
                case 2:
                    System.out.println("Enter Address book Name for Edit");
                    bookName = sc.next();
                    record = AddressBookMain.contactBookOptions();
                    ArrayList temp = addressHashMap.get(bookName);
                    if (temp != null) {
                        record.add(temp);

                    }
                    addressHashMap.put(bookName, record);
                    break;
                case 3:
                    System.out.println("Enter Address book Name for Delete...");
                    bookName = sc.next();
                    if (addressHashMap.containsKey(bookName)) {
                        addressHashMap.remove(bookName);
                    } else {
                        System.out.println("No such Book Found, Please enter a Valid AddressBook name");
                    }
                    break;
                case 4:
                    System.out.println("Address Book List");
                    readFile(path);
                    writeFile(path);
                    if (addressHashMap != null) {

                        for (String name : addressHashMap.keySet()) {
                            String value = addressHashMap.get(name).toString();
                            System.out.println(name + " --> " + value);

                        }
                    }else {
                        System.out.println("No Entries in Address book");
                    }
                    break;
                case 5:
                    System.out.print("Enter City name : ");
                    ContactPersonDetails.search((new Scanner(System.in).next()), addressHashMap);
                    break;
                case 6:
                    System.out.print("Enter City or State name : ");
                    Map<String, ContactPersonDetails> cityStateMap = ContactPersonDetails.cityStateRelatedData((new Scanner(System.in).next()), personDetailsMap);
                    for (String cityCount : cityStateMap.keySet()) {
                        System.out.println(cityCount + " - " + cityStateMap.get(cityCount));
                    }
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("Please enter valid input");
            }
        }
    }

    private static void readFile(String path) {
        Path filepath = Paths.get(path + "/Book1.text");
        try {
            byte[] byteArray = Files.readAllBytes(filepath);
            System.out.println(new String(byteArray));
            List<String> list = Files.readAllLines(filepath);
            System.out.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void writeFile(String path){
        Path filepath = Paths.get(path + "/Book2.text");
        String addData = "This My Address book project";
        try {
           Files.write(filepath,addData.getBytes(), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

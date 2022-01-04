package com.blz.address;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AddressBook {

    private static final String PATH ="C:\\Users\\Dell\\Desktop\\manisha\\AddressBook\\src\\main\\resources\\AddressBook";
    public static void main(String[] args) throws IOException, CsvValidationException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        // File io
        IntStream.range(1, 6).forEach(value -> {
            Path fileName = Paths.get(PATH + "/Book" + value + ".text");
            try {
                if (Files.notExists(fileName))
                    Files.createFile(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // User CSV files
        String csvPath = "C:\\Users\\Dell\\Desktop\\manisha\\AddressBook\\src\\main\\resources\\AddressBook\\BookUser5.csv";
        Reader reader = Files.newBufferedReader(Paths.get(csvPath));
        CSVReader csvReader = new CSVReader(reader);
        String[] nextRecord;
        // print Welcome Message
        System.out.println("Welcome to Address Book");
        Map<String, ArrayList<AddressBookMain>> addressHashMap = new HashMap();
        Map<String, ArrayList<ContactPersonDetails>> personDetailsMap = new HashMap<>();
        ArrayList record = new ArrayList();
        Scanner sc = new Scanner(System.in);
        String bookName;
        boolean flag = true;

        while (flag) {
            System.out.println("----------------BOOKS----------------------------");
            System.out.println("1 - Add more Address Book  \n2 - Edit Address Book \n3 - Delete Address Book \n4 - Show AddressBook " + "\n5 - Search Using City or State" +
                    "\n6- Show City related data" + "\n7 - Enter city or state name " +"\n0 -  for exit \nEnter your Choice.....");
            // User select the Choice
            int choice = sc.nextInt();
            switch (choice) {
                // For adding Address book
                case 1:
                    System.out.println("Please Enter the Address book Name");
                    bookName = sc.next();
                    addressHashMap.put(bookName, null);
                    break;
                    //For Edit Address Book
                case 2:
                    System.out.println("Enter Address book Name for Edit");
                    bookName = sc.next();
                    record = AddressBookMain.contactBookOptions();
                    ArrayList temp = addressHashMap.get(bookName);
                    if (temp != null) {
                        record.add(temp);

                    }
                    // write the user data in csv file
                    writeCsvFileInAddressBook(bookName, record);
                    //write the user data in json file
                    writeJsonFileInAddressBook(bookName,record);
                    addressHashMap.put(bookName, record);
                    break;
                    //For Delete Address Book
                case 3:
                    System.out.println("Enter Address book Name for Delete...");
                    bookName = sc.next();
                    if (addressHashMap.containsKey(bookName)) {
                        addressHashMap.remove(bookName);
                    } else {
                        System.out.println("No such Book Found, Please enter a Valid AddressBook name");
                    }
                    break;
                    // For Show AddressBook
                case 4:
                    System.out.println("Address Book List");
                     // read method to show the data in NIO file
                    readFile(PATH);
                    writeFile(PATH);
                    if (addressHashMap != null) {
                        for (String name : addressHashMap.keySet()) {
                            String value = addressHashMap.get(name).toString();
                            System.out.println(name + " --> " + value);

                        }
                    }else {
                        System.out.println("No Entries in Address book");
                    }
                    // Display the Record's in CSV file
                    int count = 0;
                    csvReader.readNext();
                    while ((nextRecord = csvReader.readNext()) != null) {
                        count++;
                        if (count==1){
                            continue;
                        }
                        System.out.println("firstName " + nextRecord[0]);
                        System.out.println("lastName " + nextRecord[1]);
                        System.out.println("Email " + nextRecord[2]);
                        System.out.println("Phone " + nextRecord[3]);
                        System.out.println("Address " + nextRecord[4]);
                        System.out.println("City " + nextRecord[5]);
                        System.out.println("State " + nextRecord[6]);
                        System.out.println("Zip " + nextRecord[7]);
                        System.out.println();
                    }
                    break;
                    // For Search Using City or State
                case 5:
                    System.out.print("Enter City name : ");
                    ContactPersonDetails.search((new Scanner(System.in).next()), addressHashMap);
                    break;
                    //For Show City related data
                case 6:
                    System.out.print("Enter City or State name : ");
                    Map<String, ContactPersonDetails> cityStateMap = ContactPersonDetails.cityStateRelatedData((new Scanner(System.in).next()), personDetailsMap);
                    for (String cityCount : cityStateMap.keySet()) {
                        System.out.println(cityCount + " - " + cityStateMap.get(cityCount));
                    }
                    break;
                case 7:
                    System.out.print("Enter City or State name : ");
                    int numberOfContact= countOfContactPersonData((new Scanner(System.in).next()),addressHashMap);
                    System.out.println("Total number of contact in given City is : "+numberOfContact);
                    // For exit
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("Please enter valid input");
            }
        }
    }

    private static int countOfContactPersonData(String cityStateName, Map<String, ArrayList<AddressBookMain>> addressHashMap) {
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

    // readFile
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
    //   write NIO Files using Address Book
    private static void writeFile(String path){
        Path filepath = Paths.get(path + "/Book2.text");
        String addData = "This My Address book project";
        try {
           Files.write(filepath,addData.getBytes(), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //   write CSV Files using Address Book
    private static void writeCsvFileInAddressBook(String addressBookName, ArrayList personDetails) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        Path csvFile = Paths.get(PATH + "/" + addressBookName + ".csv");
        Writer writer = Files.newBufferedWriter(csvFile);
        StatefulBeanToCsv<ContactPersonDetails> beanToCSV = new StatefulBeanToCsvBuilder<ContactPersonDetails>(writer)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .build();

        for (Object cp : personDetails) {
            beanToCSV.write((ContactPersonDetails) cp);
        }
        writer.close();
    }
   //    write Json Files using Address Book
    private static void writeJsonFileInAddressBook (String addressBookName, ArrayList arrayList) throws IOException {
        String jsonFile=(PATH + "/" + addressBookName + ".json");
        Gson gson= new Gson();
        String json=gson.toJson(arrayList);
        if (Files.notExists(Paths.get(jsonFile))) {
            Files.createFile(Paths.get(jsonFile));
            FileWriter writer=new FileWriter(jsonFile);
            writer.write(json);
            writer.close();
        }
    }
}

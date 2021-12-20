package com.blz.address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBook {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book");
        Map<String, ArrayList<AddressBookMain>> addressHashMap=new HashMap();
        ArrayList record= new ArrayList();
        Scanner sc=new Scanner(System.in);
        String bookName;
        boolean flag=true;

        while(flag){
            System.out.println("--------------------------------------------");
            System.out.println("1 - Add more Address Book  \n2 - Edit Address Book \n3 - Delete Address Book \n4 - Show AddressBook " +
                    "\n0 -  for exit \nEnter your Choice.....");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Please Enter the Address book Name");
                    bookName = sc.next();
                    addressHashMap.put(bookName,null);
                    break;
                case 2:
                    System.out.println("Enter Address book Name for Edit");
                    bookName = sc.next();
                    record=AddressBookMain.contactBookOptions();
                    ArrayList temp=addressHashMap.get(bookName);
                    if(temp!=null)
                    {
                        record.add(temp);

                    }
                    addressHashMap.put(bookName,record);
                    break;
                case 3:
                    System.out.println("Enter Address book Name for Delete...");
                    bookName = sc.next();
                    if(addressHashMap.containsKey(bookName)){
                        addressHashMap.remove(bookName);
                    }
                    else{
                        System.out.println("No such Book Found, Please enter a Valid AddressBook name");
                    }
                    break;
                case 4:
                    System.out.println("Address Bool List");

                    for(String name:addressHashMap.keySet()){
                        String value=addressHashMap.get(name).toString();
                        System.out.println(name+" --> "+value);
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
}

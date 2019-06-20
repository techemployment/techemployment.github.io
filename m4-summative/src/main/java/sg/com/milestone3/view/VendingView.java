/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.milestone3.view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import sg.com.milestone3.dtos.Item;

/**
 *
 * @author Bill Gates
 */
public class VendingView {
    private UserIO io;
    private int promptForInt(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return Integer.parseInt(input);
    }
    
    public VendingView(UserIO myIo){
        this.io = myIo;
    }
    
    public void printBanner(String title){
        System.out.println("============"+title+"============");    
    }
    
    public int getMainMenuChoice(){
        printBanner("Main Menu");
        System.out.println("1. View Items");
        System.out.println("2. Buy an Item");
        System.out.println("3. Exit");
        printBanner("");
        return promptForInt();
    }
    
    public int viewItems(List<Item> items){
        printBanner("Items");
        int i = 0;
        for (i = 0; i < items.size(); i++){
           System.out.println((i+1)+". " + items.get(i).getName() + "  $" +items.get(i).getCost()); 
        }
        
        printBanner("");
        return promptForInt();
    }
    
        public void DisplayMsg(String msg, String banner){
        //Method overloading
        printBanner(banner);
        System.out.println(msg);
        Scanner sc = new Scanner(System.in);
        System.out.println("Press Enter to Continue..");
        printBanner("");
        sc.nextLine();
        
    }
     public void DisplayMsg(String msg){
        System.out.println(msg);
    } 
     
      public void displayUnknownCommandBanner() {
             System.out.println("Unknown Command!!!");
        }
      public void displayExitBanner(){
        System.out.println("GOOD BYE!");
      }
      
      public BigDecimal getMoney(){
          System.out.println("Insert money");
          Scanner sc = new Scanner(System.in);
          String input = sc.nextLine();
          System.out.println ("You've inserted $"+input);
          
          return new BigDecimal(input);
      }
      
}

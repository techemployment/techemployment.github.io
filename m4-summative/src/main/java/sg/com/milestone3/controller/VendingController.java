/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.milestone3.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.com.milestone3.dao.VendingException;
import sg.com.milestone3.dtos.Change;
import sg.com.milestone3.dtos.Item;
import sg.com.milestone3.services.VendingServiceLayer;
import sg.com.milestone3.view.VendingView;

/**
 *
 * @author Bill Gates
 */
public class VendingController {
    private VendingView view;
    private VendingServiceLayer service;
    private Change output;
    public VendingController(VendingView view, VendingServiceLayer s){
        this.view = view;
        this.service = s;
    }
    
    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;
        
            while (keepGoing) {

                menuSelection = view.getMainMenuChoice();

                switch(menuSelection){
                    case 1: 
                        viewItems();
                        break;
                    case 2: 
                        getMoney();
                        break;
                 
                    case 3:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
   
                }

            }
            exitMessage ();
       
    }
    
    private void getMoney (){
        BigDecimal money = view.getMoney();
        
        BigDecimal change = buyItem(money); 
        getChange (change);
    } 
    
    private BigDecimal buyItem (BigDecimal money){
        System.out.println("Which item would you like to buy?");
        int choice = viewItems();
        
        switch (choice) {
            case 1:
                return service.buyItem ("Snickers", money); 
            case 2:
                return service.buyItem ("Skittles", money);
            case 3:
                return service.buyItem ("Dorito's Purple Bag", money);
            case 4:
                return service.buyItem ("Hershey's Chocolate Bar", money);
            case 5:
                return service.buyItem ("Lay's Salt and Vinegar Chips", money);
            default:
                unknownCommand();
                break;
        }
        
        return money;
    }
    
    
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }    

    public int viewItems(){
        
        List<Item> items = service.getItems();
        int choice = view.viewItems(items);
        return choice;
    }  
    
    public void getChange (BigDecimal change){
        
        System.out.println ("Here is your change/money back : $" + change);
        output = new Change (change);
        output.calculateChange();
    }
    
    public void exitMessage() {
        view.displayExitBanner();
    }

}

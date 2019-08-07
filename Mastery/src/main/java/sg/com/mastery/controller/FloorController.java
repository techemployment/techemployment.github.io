/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.mastery.controller;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.com.mastery.dao.FloorException;
import sg.com.mastery.dto.Order;
import sg.com.mastery.service.Service;
import sg.com.mastery.service.ServiceImpl;
import sg.com.mastery.view.View;

/**
 *
 * @author Bill Gates
 */
public class FloorController {
    private View view;
    private Service service;
    
    public FloorController (View view, ServiceImpl service){
        this.view = view;
        this.service = service;
    }
    
    public void run (){
        boolean keepGoing = true;
        int menuSelection = 0;
        
            while (keepGoing) {

                menuSelection = view.printMenuAndGetSelection();

                switch(menuSelection){
                    case 1: 
                        displayOrders();
                        break;
                    case 2: 
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        save();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
   
                }

            }
            exitMessage ();
    }
    
    public void displayOrders (){
        
            view.displayOrdersBanner();
            String date = view.getDate();
            
            Collection <Order> orderList= service.getOrders (date);
            view.displayOrdersList(orderList);
        
    }
    
    public void addOrder(){
        view.displayAddOrderBanner ();
        String date = view.getDate();
        
        //figure out the Order Number.
        //If date exists with no order in it, write more code...
        String orderNumber;
        if (service.validateDate(date)){
            orderNumber = service.generateOrderNumber (date);
        }else{
            orderNumber = "1";}

        //create an order
        Order order = view.getNewOrderInfo(orderNumber);
        //if user does not want to commit, it returns null        
        if (order!=null){
            service.addOrder (date, order);
            view.displayAddSuccessBanner ();
        }else{
            System.out.println("The new order was not committed");
        }
        
          
        
    }
    
    public void editOrder (){
      
        Order order;
        view.displayEditOrderBanner();
        String date = view.getDate();
        String orderNumber = view.getOrderNumber();
        //fetch the order if it exists.
        order = service.getOrder (date, orderNumber);
        //gather new info and update the old order 
        order = view.getEditOrderInfo(order);
        service.editOrder(date, orderNumber, order);
        view.displayEditSuccessBanner();
            
        
    }
    
    public void removeOrder (){
       
            view.displayRemoveOrderBanner();
            String date = view.getDate();
            String orderNumber = view.getOrderNumber();
            Order order = service.getOrder(date, orderNumber);
            boolean ok = view.getRemoveOrderInfo(order);

            if (ok) {
                service.removeOrder (date, orderNumber,order);
                view.displayRemoveSuccessBanner ();
            }
            else System.out.println ("The order was not removed");
       
    }
    
    public void save (){
        service.save();
        view.displaySaveBanner();
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }   
    
     public void exitMessage() {
        view.displayExitBanner();
    }
    
}

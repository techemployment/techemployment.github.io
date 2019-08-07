/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.mastery.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.com.mastery.dao.Dao;
import sg.com.mastery.dao.DaoImpl;
import sg.com.mastery.dao.FloorException;
import sg.com.mastery.dto.Order;

/**
 *
 * @author Bill Gates
 */
public class ServiceImpl implements Service {
    private Dao dao = new DaoImpl();
    
    public String generateOrderNumber (String date) {
        String sizeInString ="";
        try{
            Collection <Order> orderList = dao.getOrders(date);
            /*
            int currentSize = orderList.size();
            currentSize++;
            sizeInString = currentSize+"";
            */
            Order lastOrder=null;
            for (Order o:orderList){
                lastOrder=o;
            }
            
            int lastOrderNumber = Integer.parseInt(lastOrder.getOrderNumber());
            lastOrderNumber++;
            sizeInString= lastOrderNumber+"";
            return sizeInString;
        }catch (FloorException ex){
                System.out.println ("Trouble obtaining order number");
        }
    
        return sizeInString;
    }
    
    public Collection <Order> getOrders (String date) {
        Collection <Order> orderList= new ArrayList <>(); 
        
        try{
            orderList = dao.getOrders(date);
            
            return orderList;
        }catch (FloorException ex){
                System.out.println ("Trouble obtaining orders");
        }
        
        return orderList;
    }

    
    public Order getOrder(String date, String orderNumber) {
        Order order = null;
        
        try {
            return dao.getOrder(date, orderNumber);
        } catch (FloorException ex) {
            System.out.println ("Could not obtain order");
        }
        
        return order;
    }
    
    public void addOrder (String date, Order order){
        try {
            dao.addOrder(date, order);
        } catch (FloorException ex) {
            System.out.println ("Could not add order");
        }
    }
    
    public Order editOrder (String date, String orderNumber, Order order){
        
        try {
            dao.editOrder(date, orderNumber, order);
        } catch (FloorException ex) {
            System.out.println ("Could not edit the order");
        }
        
        return order;
    }
    
    public Order removeOrder (String date, String orderNumber, Order order)
    {
        try {
            dao.removeOrder(date, orderNumber, order);
        } catch (FloorException ex) {
            System.out.println ("Could not edit the order");
        }
        
        return order;
    }  
    
    public boolean validateDate (String date){
        return dao.validateDate (date);
    }
    
    public void save() {
        try {
                dao.save();
        }catch (FloorException ex) {
                System.out.println ("Could not edit the order");}
       
    }
 
      
}

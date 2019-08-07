/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.mastery.service;

import java.util.Collection;
import java.util.List;
import sg.com.mastery.dao.FloorException;
import sg.com.mastery.dto.Order;

/**
 *
 * @author Bill Gates
 */
public interface Service {
    
    
    
    Collection <Order> getOrders (String date);
    String generateOrderNumber (String date);
    //List <Order> getOrders (String date) throws FloorException;
    
    Order getOrder(String date, String orderNumber);
    void addOrder (String date, Order order);
    Order editOrder (String date, String orderNumber, Order order);
     Order removeOrder (String date, String orderNumber, Order order);
   void save ();
   boolean validateDate(String date);
    
}

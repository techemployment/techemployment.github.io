/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.mastery.dao;

import java.util.Collection;
import java.util.List;
import sg.com.mastery.dto.Order;

/**
 *
 * @author Bill Gates
 */
public interface Dao {
    
    Collection <Order> getOrders(String date) throws FloorException;
    
     Order addOrder (String date, Order order) throws FloorException;
    
     Order editOrder (String date, String orderNumber, Order order) throws FloorException;
    
     Order removeOrder (String date, String orderNumber, Order order) throws FloorException;

     Order getOrder (String date, String orderNumber) throws FloorException;

     boolean validateDate (String date);
     
     void save() throws FloorException;
}
    

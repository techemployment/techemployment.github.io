/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.mastery.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.com.mastery.dto.Order;

/**
 *
 * @author Bill Gates
 */
public class DaoImplIT {
    
    public DaoImplIT() {
    }

    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        String date="06012013";
        DaoImpl instance = new DaoImpl();
           Order order = instance.getOrder(date, "1");
           order.setTotal("0.00");
           instance.editOrder(date, "1", order);
           instance.save();
        DaoImpl instance2 = new DaoImpl();
           order = instance2.getOrder(date, "1");
           BigDecimal zero = new BigDecimal("0.00");
           assertEquals (order.getTotal(), zero);
    }

    @Test
    public void testGetOrder() throws Exception {
        System.out.println("getOrder");
        DaoImpl instance = new DaoImpl();
        //1,Test1,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        Order order = instance.getOrder("06012013", "1");
        assertEquals (order.getCustomerName(), "Wise");
        
    }

    @Test
    public void testGetOrders() throws Exception {
        System.out.println("getOrders");
        
        DaoImpl instance = new DaoImpl();
        Collection<Order> result = instance.getOrders("06012013");
        
        assertEquals(4, result.size());
       
    }

    @Test
    public void testAddOrder() throws Exception {
        System.out.println("addOrder");
        DaoImpl instance = new DaoImpl();
           Order order = new Order ("5");
            order.setCustomerName("Test5");
            order.setState("OH");
            order.setTaxRate("6.25");
            order.setProductType("Wood");
            order.setArea("100.00");
            order.setCostPerSquareFoot("5.15");
            order.setLaborCostPerSquareFoot("4.75");
            order.setMaterialCost("515.00");
            order.setLaborCost("475.00");
            order.setTax("61.88");
            order.setTotal("1051.88");
            
        instance.addOrder("06012013", order);
        
        Order sameOrder = instance.getOrder("06012013", "5");
        assertEquals("Test5", sameOrder.getCustomerName());
    }

    @Test
    public void testEditOrder() throws Exception {
        System.out.println("editOrder");
        String date="06012013";
        DaoImpl instance = new DaoImpl();
           Order order = instance.getOrder(date, "1");
           order.setTotal("2.00");
           instance.editOrder(date, "1", order);
        
            BigDecimal two = new BigDecimal("2.00");
           assertEquals (order.getTotal(), two);
    }

    @Test
    public void testRemoveOrder() throws Exception {
        System.out.println("removeOrder");
        DaoImpl instance = new DaoImpl();
        String date="06012013";
        Collection<Order> result = instance.getOrders(date);
        int sizeB4= result.size();
        sizeB4--;
        Order lastOrder= instance.getOrder(date, "5");
        instance.removeOrder("06012013", "5", lastOrder);
        Collection<Order> list = instance.getOrders(date);
        assertEquals(sizeB4, list.size());
    }

    @Test
    public void testValidateDate() {
        System.out.println("validateDate");
        String date = "06012013";
        DaoImpl instance = new DaoImpl();
        boolean expResult = true;
        boolean result = instance.validateDate(date);
        assertEquals(expResult, result);
        
        
    }

    
}

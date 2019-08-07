/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.mastery.service;

import java.math.BigDecimal;
import java.util.Collection;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.com.mastery.dao.DaoImpl;
import sg.com.mastery.dto.Order;

/**
 *
 * @author Bill Gates
 */
public class ServiceImplIT {
    
    public ServiceImplIT() {
    }

    @Test
    public void testGenerateOrderNumber() {
        System.out.println("generateOrderNumber");
        String date = "06012013";
        ServiceImpl instance = new ServiceImpl();
        
        String result = instance.generateOrderNumber(date);
        assertEquals("6", result);
        
    }

    @Test
    public void testGetOrders() {
        System.out.println("getOrders");
        String date = "";
        ServiceImpl instance = new ServiceImpl();
        Collection<Order> result = instance.getOrders("06012013");
        
        assertEquals(4, result.size());
    }

    @Test
    public void testGetOrder() {
        System.out.println("getOrder");
       
        ServiceImpl instance = new ServiceImpl();
        Order order = instance.getOrder("06012013", "1");
        assertEquals (order.getCustomerName(), "Wise");

    }

    @Test
    public void testAddOrder() {
        System.out.println("addOrder");
        
        ServiceImpl instance = new ServiceImpl();
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
    public void testEditOrder() {
        System.out.println("editOrder");
        String date="06012013";
        ServiceImpl instance = new ServiceImpl();
        Order order = instance.getOrder(date, "1");
           order.setTotal("2.00");
           instance.editOrder(date, "1", order);
        
            BigDecimal two = new BigDecimal("2.00");
           assertEquals (order.getTotal(), two);
    }

    @Test
    public void testRemoveOrder() {
        System.out.println("removeOrder");
        String date="06012013";
        
        
        ServiceImpl instance = new ServiceImpl();
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
        ServiceImpl instance = new ServiceImpl();
        boolean expResult = true;
        boolean result = instance.validateDate(date);
        assertEquals(expResult, result);
    }

    @Test
    public void testSave() {
        System.out.println("save");
        String date="06012013";
        ServiceImpl instance = new ServiceImpl();
        Order order = instance.getOrder(date, "1");
           order.setTotal("0.00");
           instance.editOrder(date, "1", order);
           instance.save();
        ServiceImpl instance2 = new ServiceImpl();
           order = instance2.getOrder(date, "1");
           BigDecimal zero = new BigDecimal("0.00");
           assertEquals (order.getTotal(), zero);
        
    }

    
}

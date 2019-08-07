/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.milestone3.dao;

import java.math.BigDecimal;
import java.util.List;
import junit.framework.Assert;
import junit.framework.TestCase;
import sg.com.milestone3.dtos.Item;

/**
 *
 * @author Bill Gates
 */
public class VendingDaoTest extends TestCase {
    
    public VendingDaoTest(String testName) {
        super(testName);
    }

    public void testGetItem() throws Exception {
        System.out.println("getItem- Just checking if the item name and price match");
        String name = "Snickers";
        BigDecimal cost = new BigDecimal ("1.25");
        VendingDao instance = new VendingDao();
        
        Item result = instance.getItem(name);
        Assert.assertEquals(name, result.getName());
         Assert.assertEquals(cost, result.getCost());
        // TODO review the generated test code and remove the default call to fail.
         Assert.fail("The test case is a prototype.");
    }

    public void testGetItems() throws VendingException {
        System.out.println("getItems - checking size of the item list");
        VendingDao instance = new VendingDao();
        List<Item> result = instance.getItems();
         Assert.assertEquals(5, result.size());
        // TODO review the generated test code and remove the default call to fail.
         Assert.fail("The test case is a prototype.");
    }

    public void testBuyItem() throws Exception {
        System.out.println("buyItem");
        String name = "Snickers";
        BigDecimal money = new BigDecimal("2.00");
        VendingDao instance = new VendingDao();
        int quantityBefore = instance.getItem(name).getQuantity();
        //buying Snickers and checking if the quantity got updated
        instance.buyItem(name, money);
         Assert.assertEquals(instance.getItem(name).getQuantity(), quantityBefore-1);
        // TODO review the generated test code and remove the default call to fail.
         Assert.fail("The test case is a prototype.");
    }
    
}

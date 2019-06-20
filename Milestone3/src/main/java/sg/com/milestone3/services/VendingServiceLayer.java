/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.milestone3.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.com.milestone3.dao.Dao;
import sg.com.milestone3.dao.InsufficientFundsException;
import sg.com.milestone3.dao.ItemOutOfStockException;
import sg.com.milestone3.dao.VendingAuditDao;
import sg.com.milestone3.dao.VendingDao;
import sg.com.milestone3.dao.VendingException;
import sg.com.milestone3.dtos.Change;
import sg.com.milestone3.dtos.Item;

/**
 *
 * @author Bill Gates
 */
public class VendingServiceLayer {
    
    private Dao dao;
    private VendingAuditDao auditDao; // import
    /*
            inside buyItem() method,
            auditDao.writeAuditEntry("Item "+ item.getName() + " BOUGHT");
    */
    public VendingServiceLayer(VendingAuditDao auditDao) {
        try {
            this.dao= new VendingDao ();
            
        } catch (VendingException ex) {
            System.out.println ("Trouble initializing VendingDao");
        }
           this.auditDao= auditDao;
    }
    private void validateItemData(Item item) throws VendingException {
        //name, num, cost
        if(item.getName() == null || item.getName().trim().length()==0||
                item.getCost()== null) { 
                throw new VendingException ("ERROR: ALL FIELDS ARE REQUIRED");}
}


    public Item getItem(String itemId) throws VendingException{
            return dao.getItem(itemId);
    }

   
    public List<Item> getItems(){
                      
        return dao.getItems();
       
    }
    
    public BigDecimal buyItem (String name, BigDecimal money){
        try{
            return dao.buyItem(name,money);
        }catch(InsufficientFundsException a){
            System.out.println("NOT ENOUGH MONEY");
            
        }catch(ItemOutOfStockException b){
            System.out.println("SOLD OUT");
        }catch(VendingException c){
             System.out.println ("ERROR");
        }
        return money;
    }
}

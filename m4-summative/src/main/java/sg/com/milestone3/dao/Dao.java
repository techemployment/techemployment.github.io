/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.milestone3.dao;

import java.math.BigDecimal;
import java.util.List;
import sg.com.milestone3.dtos.Item;

/**
 *
 * @author Bill Gates
 */
public interface Dao {

    
    
    public Item getItem(String name) throws VendingException;
    
    public List<Item> getItems();
    
    public BigDecimal buyItem(String name, BigDecimal money) throws VendingException, ItemOutOfStockException, InsufficientFundsException;
}

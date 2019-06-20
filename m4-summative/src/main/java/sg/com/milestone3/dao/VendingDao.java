/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.milestone3.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import sg.com.milestone3.dtos.Item;

/**
 *
 * @author Bill Gates
 */
public class VendingDao implements Dao{
   
    public static final String FILENAME = "VendingMachine.txt";
    public static final String DELIMITER = "::";
    
    private List<Item> items;

    public VendingDao() throws VendingException{
        this.items = readFromFile();
    }

    private List<Item> readFromFile() throws VendingException{
        List<Item> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));
            
            while(sc.hasNextLine()){
                String row = sc.nextLine();
                String[] cols = row.split(DELIMITER);
                Item item = new Item();
                item.setName(cols[0]);
                item.setCost(new BigDecimal(cols[1]));
                item.setQuantity(Integer.parseInt(cols[2]));
                list.add(item);
            }   
                
            } catch (FileNotFoundException ex) {
            
                throw new VendingException("Error reading from file");
        }
        return list;
    }
    
    private void saveToFile(List<Item> items) throws VendingException{
        try {
            PrintWriter out = new PrintWriter(new FileWriter(FILENAME));
            for(Item item : items) {
                String row = item.getName()+ DELIMITER+ item.getCost()+ DELIMITER+ item.getQuantity();
                out.println(row);
                out.flush();
            }
            
            out.close();
        } catch (IOException ex) {
            throw new VendingException("Error writing to file");
        }
        
    }
    
    public Item getItem(String name) throws VendingException {
        
        Item result=null;
        for (Item item: items){
            if(name.equals(item.getName())){
                result=item;
                break;
            }
        }
        
        return result;
        
        
                
         
    }
    
    public List<Item> getItems(){
        return items;
    }
    
    public BigDecimal buyItem(String name, BigDecimal money) throws VendingException, ItemOutOfStockException, InsufficientFundsException{
        Item toBuy = getItem(name);
        
        //System.out.println ("Fetched item: "+ toBuy.getName());
        
        if (toBuy.getQuantity() == 0) throw new ItemOutOfStockException("SOLD OUT");
        if(toBuy.getCost().compareTo(money)>0) throw new InsufficientFundsException("NOT ENOUGH MONEY");
        
        
        int quantity = toBuy.getQuantity();
        
        quantity--;
        
        toBuy.setQuantity(quantity);
        
        
        saveToFile (items);
        
        money = money.subtract(toBuy.getCost());
        System.out.println("SUCCESS! Here is your " + name);
        return money;
    }
    
    
}

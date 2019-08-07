/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.mastery.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.com.mastery.dto.Order;
import sg.com.mastery.dto.Product;
import sg.com.mastery.dto.StateTax;

/**
 *
 * @author Bill Gates
 */
public class DaoImpl implements Dao{
    
    //private Map<String, Order> smallerhm = new HashMap<>(); //String = orderNumber, Order =order
    private Map <String, Map <String,Order>> biggerhm = new HashMap<String, Map<String,Order>> ();//String= date, HashMap of Date files
    public static final String DELIMITER = ",";
     
    public DaoImpl(){
        try {
            loadBiggerhm();
        } catch (FloorException ex) {
            Logger.getLogger(DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadBiggerhm() throws FloorException{
        
        List<String>listOfDates = getListOfDates();
        for(String s:listOfDates){
            retrieveOrdersFromFile(s);
        }
    }
    
    private void retrieveOrdersFromFile (String date) throws FloorException {
        Map<String,Order> smallerhm= new HashMap<>();    
        String appendedDate = date+".txt";
        try {                
                Scanner sc = new Scanner (new BufferedReader (new FileReader (appendedDate)));
                String row;
                String[]cols;
                while (sc.hasNextLine()){
                    row = sc.nextLine();
                    cols = row.split(DELIMITER);
                    
                    Order order = new Order (cols[0]);
                    order.setCustomerName(cols[1]);
                    order.setState(cols[2]);
                    order.setTaxRate(cols[3]);
                    order.setProductType(cols[4]);
                    order.setArea(cols[5]);
                    order.setCostPerSquareFoot(cols[6]);
                    order.setLaborCostPerSquareFoot(cols[7]);
                    order.setMaterialCost(cols[8]);
                    order.setLaborCost(cols[9]);
                    order.setTax(cols[10]);
                    order.setTotal(cols[11]);
                    
                    smallerhm.put(order.getOrderNumber(),order);
                     /*
        OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCost
        PerSquareFoot,MaterialCost,LaborCost,StateTax,Total
    */
                }
                
                biggerhm.put(date,smallerhm);
                
                sc.close();
       
        }catch(FileNotFoundException ex){
            System.out.println ("Cannot find a file named " + date);
        }
    } 
    
    public void save () throws FloorException{
        Collection <String>listOfDates = biggerhm.keySet();
        for (String s:listOfDates){
            writeOrdersToFile(s);
        }
    }
    
    private void writeOrdersToFile (String date)throws FloorException{
        String appendedDate= date+".txt";
        
        try{
            PrintWriter out = new PrintWriter (new FileWriter(appendedDate));
            Collection <Order> orders = getOrders(date);
           
            /*
        OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCost
        PerSquareFoot,MaterialCost,LaborCost,StateTax,Total
    */
         for (Order currentOrder: orders){
           String row=
                    currentOrder.getOrderNumber() + DELIMITER+
                    currentOrder.getCustomerName()+ DELIMITER+
                    currentOrder.getState()+ DELIMITER +
                    currentOrder.getTaxRate()+ DELIMITER +
                    currentOrder.getProductType()+DELIMITER+
                    currentOrder.getArea()+DELIMITER+
                    currentOrder.getCostPerSquareFoot()+ DELIMITER+
                    currentOrder.getLaborCostPerSquareFoot()+ DELIMITER+
                    currentOrder.getMaterialCost()+DELIMITER+
                    currentOrder.getLaborCost()+DELIMITER+
                    currentOrder.getTax()+DELIMITER+
                    currentOrder.getTotal();
                   
            out.println(row);
                            
            out.flush();
        }
        
         out.close();
        }catch (IOException e){
            throw new FloorException("Could not save orders to "+ "date");
        }
      
    }
   
    @Override
    public Order getOrder (String date, String orderNumber) throws FloorException{
        if (validateDate(date)){
            Map<String, Order> smallhm = biggerhm.get(date);
            return smallhm.get(orderNumber); // returns null if order number DNE
            
        }else
            throw new FloorException ("No such date exists");
    }
    
    
    @Override
    public Collection <Order> getOrders (String date) throws FloorException{
        
        Map<String, Order> smallhm = biggerhm.get(date);
        if (validateDate(date)){
            Collection <Order> orders = smallhm.values();
            return orders;
        }else
            throw new FloorException ("No order has been made under that date");
    }
    
    @Override
    public Order addOrder (String date, Order order) throws FloorException {
        if (validateDate(date)){//orders were added to this date previously
            Map<String,Order> smallhm= biggerhm.get(date);//retrieve these previous orders
            smallhm.put(order.getOrderNumber(),order);//then add the new order 
        }else{
            Map<String,Order> smallhm= new HashMap<>();
            smallhm.put(order.getOrderNumber(), order);
            biggerhm.put(date, smallhm);
        }
        
        return order;
    }
    
    @Override
    public Order editOrder (String date, String orderNumber, Order newOrder) throws FloorException{
        Order orderBeforeEdit = getOrder(date, orderNumber);
        if (orderBeforeEdit==null) throw new FloorException ("No order has been made under that date and order number");
        Map<String,Order> smallhm=biggerhm.get(date);
        smallhm.put(orderNumber, newOrder);
        
        return orderBeforeEdit;
    }
       
    @Override
    public Order removeOrder (String date, String orderNumber, Order order) throws FloorException{
        Order orderBeforeRemove = getOrder(date, orderNumber);
        if (orderBeforeRemove==null) throw new FloorException ("No order has been made under that date and order number");
        Map<String,Order> smallhm=biggerhm.get(date);
        smallhm.remove(orderNumber, order);
        
        return orderBeforeRemove;
    }

    public boolean validateDate (String date){
        return biggerhm.containsKey(date);
    }
    
    public List<String> getListOfDates() throws FloorException{
        List<String> listOfDates = new ArrayList<>();
        String dateToLoad = ""; 
        String fileDirectory = System.getProperty("user.dir");  // get the directory of where java was ran (this project's folder)
        
        File folder = new File(fileDirectory);  // turn the directory string into a file
        File[] listOfFiles = folder.listFiles();    // get the list of files in the directory
        
        
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
      
                String nameOfFile = listOfFiles[i].getName();   // get the name of the file
                String numbersOnly = nameOfFile.replaceAll("[\\D]", "");  // replace all non-number characters with whitespace

                if(numbersOnly.equals("")){  // if there were no numbers in the file name (ex. pom.xml), do nothing
    
                }else{
                    int dateOfFile = Integer.parseInt(numbersOnly);  // get the numbers part of the file name
                    int lengthOfdate = String.valueOf(dateOfFile).length();    // get the length of the int by converting it to a String and using .length
                    if(lengthOfdate < numbersOnly.length()){     // if the leading 0 got chopped off when parsing
                            dateToLoad = "0"+ dateOfFile;       // add it back
                        }else{
                            dateToLoad = Integer.toString(dateOfFile);    // otherwise if there were no leading 0s, set to the String version of dateOfFile, NOT dateToLoad, as that will have the previous date
                        }
                    listOfDates.add(dateToLoad);
                    }
        
                }
    
            }
                
        return listOfDates;

        }
    
        
        
        
    }    
 

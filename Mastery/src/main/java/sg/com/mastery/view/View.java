/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.mastery.view;

import static java.lang.Character.isDigit;
import java.util.Collection;
import java.util.List;
import sg.com.mastery.dto.Order;
import sg.com.mastery.dto.Product;
import sg.com.mastery.dto.StateTax;

/**
 *
 * @author Bill Gates
 */



public class View {
    private UserIO io;
    
    public View (UserIO io){
        this.io = io;
    }
    
    public int printMenuAndGetSelection(){
        
       io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
       io.print("* <<Flooring Program>>");
       io.print("* 1. Display Orders" );
       io.print("* 2. Add an Order" );
       io.print("* 3. Edit an Order" );
       io.print("* 4. Remove an Order" );
       io.print("* 5. Save Current Work" );
       io.print("* 6. Quit" );
       io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
       
       return io.readInt("Please select from the above choices.", 1, 6);
    }
    
    public Order getNewOrderInfo (String newOrderNumber){
        String customerName = io.readString ("Please enter customer's name");
        String state = io.readString ("Please enter state");  
        StateTax sTax = new StateTax(state);
        String taxRate= sTax.getTaxRate()+"";
        String productType = io.readString("Please enter product type (Carpet, Laminate, Tile, Wood)");
        Product p = new Product (productType);
        String area = io.readString("Please enter floor area(ft)");
        String cost= p.getCostPerSquareFoot()+"";
        String laborCost= p.getLaborCostPerSquareFoot()+"";
        
        
        io.print("You have entered:\n"+
                  "Customer Name: "+ customerName+ "\n"+   
                  "State: "+ state+ "\n"+
                  "Product Type: "+ productType+"\n"+
                  "Area: "+ area +"ft"+"\n"+
                  "Material Cost(/ft): "+ cost+"\n"+
                  "Labor Cost(/ft) "+ laborCost
        );
        
        
        String answer = io.readString("Would you like to commit this data? (y/n)");
        if (answer.equalsIgnoreCase("y")){
            Order order = new Order (newOrderNumber);
            order.setCustomerName (customerName);
            order.setState(state);
            order.setTaxRate(taxRate);
            order.setProductType(productType);
            order.setArea (area);
            order.setCostPerSquareFoot(cost);
            order.setLaborCostPerSquareFoot(laborCost);
            order.calculateMaterialCost();
            order.calculateLaborCost();
            order.calculateTax ();
            order.calculateTotal();
            return order;
        } else return null;
    }
    
    public void displayOrdersBanner (){
        io.print ("=== DISPLAY ORDERS ===");
        
    }
    
    public void displayAddOrderBanner (){
        io.print ("=== ADD AN ORDER ===");
        
    }
    
    public void displayAddSuccessBanner (){
        io.print ("Order successfully added. Please hit enter to continue");
        
    }
    
    public void displayRemoveSuccessBanner (){
        io.print ("Order successfully removed. Please hit enter to continue");
        
    }
    
    public void displayEditSuccessBanner (){
        io.print ("Order successfully edited. Please hit enter to continue");
        
    }
    
    public void displaySaveSuccessBanner (){
        io.print ("Order successfully saved. Please hit enter to continue");
        
    }
    
    public void displayEditOrderBanner (){
        io.print ("=== EDIT AN ORDER ===");
        
    }
    
    public void displayRemoveOrderBanner (){
        io.print ("=== REMOVE AN ORDER ===");
        
    }
    
    public void displaySaveBanner (){
        io.print ("=== SAVED ===");
        
    }
    
    public void displayExitBanner() {
            io.print("Good Bye!!!");
        }
     
    public void displayUnknownCommandBanner() {
             io.print("Unknown Command!!!");
        }
        
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
        }
    
    public void displayOrdersList (Collection<Order> orderList){
        displayOrdersBanner ();
        
        for (Order currentOrder: orderList){
            io.print (currentOrder.toString());
        }
        
        io.readString("Please hit enter to continue");
    }
    
    public String getDate() {
           String input="";    
        
        do {
             input = io.readString("Please enter the date (MMDDYYYY)");
        
        }while(!checkDate (input));
        
        return input;
    }
    
    public boolean checkDate (String input){
        //check the length 
            if (input.length()< 8) {
                System.out.println ("You must pad single digits in a month and date with 0 and write out the entire year");
                return false;
            }
            
            if (input.length()>8){
                System.out.println ("Your date is too long");
                return false;
            }
            //check if these are numeric
            try {
                Double.parseDouble(input);
            }catch (NumberFormatException e){
                System.out.print("You've entered non-numeric value");
                return false;
            }
            int number = Integer.parseInt (input.substring(0,2));
            //check if MM is in the range (1-12)
            if (number>12 || number<1){
                System.out.println ("month has to be within the range (1-12)");
                return false;
            }
            //check if DD is in the range (1-31)
            number = Integer.parseInt (input.substring(3,4));
            if (number>31 || number<1){
                System.out.println ("date has to be within the range (1-31)");
                return false;
            }
            
            return true;
    }
    
    public String getOrderNumber() {
	String input =io.readString("Please enter the Order Number.");
            
               
        return input;
    }
    
  
    public Order getEditOrderInfo (Order currentOrder){
        io.print (currentOrder.toString()); 
        
        io.print ("Simply press ENTER if there is no change\n");
        String customerName = io.readString ("Please enter customer's name");
            if (customerName.length()!=0) currentOrder.setCustomerName(customerName);
        String state = io.readString ("Please enter state"); 
            if (state.length()!=0){
                StateTax sTax = new StateTax (state);
                
                currentOrder.setState(sTax.getState());
                
                currentOrder.setTaxRate(sTax.getTaxRate()+""); 
            }
            
        String productType = io.readString("Please enter product type (Carpet, Laminate, Tile, Wood)");
            if (productType.length()!=0) {
                currentOrder.setProductType(productType);
                
                Product p = new Product (productType);
                String cost = p.getCostPerSquareFoot()+"";
                String laborCost = p.getLaborCostPerSquareFoot()+"";
                currentOrder.setCostPerSquareFoot(cost);
                currentOrder.setLaborCostPerSquareFoot(laborCost);
            }
        String area = io.readString("Please enter floor area(ft)");
            if (area.length()!=0) currentOrder.setArea(area);
        
        currentOrder.calculateMaterialCost();
        currentOrder.calculateLaborCost();
        currentOrder.calculateTax ();
        currentOrder.calculateTotal();
        
        io.print("Edited order info:\n"+ currentOrder.toString()
                    
        /*
            currentOrder.getOrderNumber() + ", "+
            currentOrder.getCustomerName()+ ", "+
            currentOrder.getState()+ ", "+
            currentOrder.getTaxRate()+", "+
            currentOrder.getProductType()+ ", "+
            currentOrder.getArea()+ ", "+
            currentOrder.getCostPerSquareFoot()+ ", "+
            currentOrder.getLaborCostPerSquareFoot()+ ", "+
            currentOrder.getMaterialCost()+ ", "+
            currentOrder.getLaborCost()+ ", "+
            currentOrder.getTax()+ ", "+
            currentOrder.getTotal()
         */             
                   
        );
      
         return currentOrder;
    }
    
    public boolean getRemoveOrderInfo (Order currentOrder){
        io.print (currentOrder.toString());
        String answer = io.readString("You sure you want to remove this order?");
        
        boolean ok;
        if (answer.equalsIgnoreCase("Y")){
            return true;
        }else return false;
        
    }
    
}

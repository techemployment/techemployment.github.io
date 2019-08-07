/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.mastery.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 *
 * @author Bill Gates
 */
public class Order {
    private String orderNumber;//Unique ID, key for map
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;
    
     
    public Order (String orderNumber){
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = new BigDecimal(taxRate);
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = new BigDecimal(area);
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(String costPerSquareFoot) {
        this.costPerSquareFoot = new BigDecimal(costPerSquareFoot);
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(String laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = new BigDecimal(laborCostPerSquareFoot);
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(String materialCost) {
        this.materialCost = new BigDecimal(materialCost);
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(String laborCost) {
        this.laborCost = new BigDecimal(laborCost);
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = new BigDecimal(tax);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = new BigDecimal(total);
    }

    public BigDecimal calculateMaterialCost (){
         materialCost= area.multiply(costPerSquareFoot);
         return materialCost;
    }
    
    public BigDecimal calculateLaborCost(){
        laborCost = area.multiply(laborCostPerSquareFoot);
        return laborCost;
    }
    
    private BigDecimal calculateSum(){
        
        BigDecimal sum = calculateMaterialCost().add(calculateLaborCost());
        return sum;
    }
    
    public BigDecimal calculateTax (){
        
        BigDecimal taxDecimal= taxRate.divide(new BigDecimal("100"));
        tax = calculateSum().multiply(taxDecimal);
        BigDecimal twoDecimalPlaces = tax.setScale(2, RoundingMode.HALF_UP);
        tax = twoDecimalPlaces;
        return twoDecimalPlaces;
    }
    
    public BigDecimal calculateTotal(){
        total = calculateSum().add(calculateTax());
        return total;
    }
    
    public String toString() {
        return "  Order{" + "ORDER NUMBER  =  " + orderNumber + ",  CUSTOMER NAME  =  " + customerName + 
                ",  STATE  =  " + state + ",  TAX RATE  =  " + taxRate + ",  PRODUCT TYPE  =  " + productType + 
                ",  MATERIAL COST  =  " + costPerSquareFoot + ",  LABOR COST  =  " + laborCostPerSquareFoot + " , AREA  =  " + area +
                ",  TOTAL MATERIAL COST  =  " + materialCost + ",  TOTAL LABOR COST  =  " + laborCost + 
                ",  TOTAL TAX  =  " + tax + ",  TOTAL  =  " + total + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.orderNumber);
        hash = 89 * hash + Objects.hashCode(this.customerName);
        hash = 89 * hash + Objects.hashCode(this.state);
        hash = 89 * hash + Objects.hashCode(this.taxRate);
        hash = 89 * hash + Objects.hashCode(this.productType);
        hash = 89 * hash + Objects.hashCode(this.area);
        hash = 89 * hash + Objects.hashCode(this.costPerSquareFoot);
        hash = 89 * hash + Objects.hashCode(this.laborCostPerSquareFoot);
        hash = 89 * hash + Objects.hashCode(this.materialCost);
        hash = 89 * hash + Objects.hashCode(this.laborCost);
        hash = 89 * hash + Objects.hashCode(this.tax);
        hash = 89 * hash + Objects.hashCode(this.total);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.orderNumber, other.orderNumber)) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.costPerSquareFoot, other.costPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.laborCostPerSquareFoot, other.laborCostPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.tax, other.tax)) {
            return false;
        }
        if (!Objects.equals(this.total, other.total)) {
            return false;
        }
        return true;
    }
    
  
   
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.mastery.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Bill Gates
 */
public class Product {
    //ProductType,CostPerSquareFoot,LaborCostPerSquareFoot
    private String ProductType;
    private BigDecimal CostPerSquareFoot;
    private BigDecimal LaborCostPerSquareFoot;

    public Product (String ProductType){
        this.ProductType= ProductType;
        if (ProductType.equalsIgnoreCase("Carpet")){
            setCostPerSquareFoot("2.25");
            setLaborCostPerSquareFoot("2.10");
        }
        else if (ProductType.equalsIgnoreCase("Laminate")){
            setCostPerSquareFoot("1.75");
            setLaborCostPerSquareFoot("2.10");
        }
        else if (ProductType.equalsIgnoreCase("Tile")){
            setCostPerSquareFoot("3.50");
            setLaborCostPerSquareFoot("4.15");
        }
        else if (ProductType.equalsIgnoreCase("Wood")){
            setCostPerSquareFoot("5.15");
            setLaborCostPerSquareFoot("4.75");
        }
        
        else{
            setCostPerSquareFoot("0.00");
            setLaborCostPerSquareFoot("0.00");
        }
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.ProductType);
        hash = 11 * hash + Objects.hashCode(this.CostPerSquareFoot);
        hash = 11 * hash + Objects.hashCode(this.LaborCostPerSquareFoot);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.ProductType, other.ProductType)) {
            return false;
        }
        if (!Objects.equals(this.CostPerSquareFoot, other.CostPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.LaborCostPerSquareFoot, other.LaborCostPerSquareFoot)) {
            return false;
        }
        return true;
    }
    
    public String getProductType() {
        return ProductType;
    }

    public BigDecimal getCostPerSquareFoot() {
        return CostPerSquareFoot;
    }

    public void setCostPerSquareFoot(String CostPerSquareFoot) {
        this.CostPerSquareFoot = new BigDecimal(CostPerSquareFoot);
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return LaborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(String LaborCostPerSquareFoot) {
        this.LaborCostPerSquareFoot = new BigDecimal(LaborCostPerSquareFoot);
    }
}

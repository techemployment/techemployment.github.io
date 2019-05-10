/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icecreamshop.dto;

/**
 *
 * @author acetip
 */
public class IceCream {
    private String[] flavors;
    private String name;
    private double price;

    public String[] getFlavors() {
        return flavors;
    }

    public void setFlavors(String[] flavors) {
        this.flavors = flavors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
}

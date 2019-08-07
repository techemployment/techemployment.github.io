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
public class StateTax {
    private String state;
    private BigDecimal taxRate;

    public StateTax(String state){
        this.state = state;
        if (state.equalsIgnoreCase("OH")) setTaxRate("6.25");
        else if (state.equalsIgnoreCase("PA")) setTaxRate("6.75");
        else if (state.equalsIgnoreCase("MI")) setTaxRate("5.75");
        else if (state.equalsIgnoreCase("IN")) setTaxRate("6.00");
        else setTaxRate("0.00");
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
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.state);
        hash = 71 * hash + Objects.hashCode(this.taxRate);
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
        final StateTax other = (StateTax) obj;
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        return true;
    }
}

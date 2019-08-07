/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.milestone3.dtos;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *
 * @author Bill Gates
 */
public class Change {
    private BigDecimal initial;
    private int nQuarter;
    private int nDime;
    private int nNickle;
    public static final BigDecimal QUARTER= new BigDecimal("25"); 
    public static final BigDecimal DIME= new BigDecimal("10"); 
    public static final BigDecimal NICKLE = new BigDecimal("5"); 
    //constructor
    
    public Change (BigDecimal initial){
        this.initial= initial;
        nQuarter = 0;
        nDime = 0;
        nNickle=0;
    }
    //getters

    public int getQuarter (){
        return nQuarter;
    }

    public int getDime(){
        return nDime;
    }

    public int getNickle(){
        return nNickle;
    }
    

    public void calculateChange (){
        MathContext mc = new MathContext(2); 
        initial = initial.multiply(new BigDecimal("100"));
        
	nQuarter =initial.divide(QUARTER, 2, RoundingMode.HALF_UP).intValue();
	initial= initial.remainder (QUARTER, mc);
	System.out.println("Number of Quarter(s): "+ nQuarter);
	nDime = initial.divide (DIME, 2, RoundingMode.HALF_UP).intValue();
	initial = initial.remainder (DIME, mc);
	System.out.println("Number of Dime(s): "+ nDime);
	nNickle = initial.divide (NICKLE, 2, RoundingMode.HALF_UP).intValue();
	initial = initial.remainder (NICKLE, mc);
	System.out.println("Number of Nickle(s): "+ nNickle);

        

    }
}

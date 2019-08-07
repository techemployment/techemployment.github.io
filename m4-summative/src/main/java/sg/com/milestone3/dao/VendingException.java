/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.milestone3.dao;

/**
 *
 * @author Bill Gates
 */
public class VendingException extends Exception{
    
    public VendingException(String message) {
        super(message);
    }

    public VendingException(String message, Throwable cause) {
        super(message, cause);
    }
}

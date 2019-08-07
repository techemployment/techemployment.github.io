/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.mastery.dao;

/**
 *
 * @author Bill Gates
 */
public class FloorException extends Exception{
    public FloorException(String message) {
        super(message);
    }

    public FloorException(String message, Throwable cause) {
        super(message, cause);
    }
}

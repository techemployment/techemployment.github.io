/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.milestone3.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author Bill Gates
 */
public class VendingAuditDao {
    
	public static final String AUDIT_FILE= "audit.txt";	

	public void writeAuditEntry (String entry) throws VendingException{
		PrintWriter out; 
		try{
			out = new PrintWriter (new FileWriter (AUDIT_FILE, true));	
		}catch(IOException ex){
			throw new VendingException ("Could not persist audit info", ex);
		}

		LocalDateTime timestamp= LocalDateTime.now();
		out.println(timestamp.toString() + ":" + entry);
		out.flush();
        }
}

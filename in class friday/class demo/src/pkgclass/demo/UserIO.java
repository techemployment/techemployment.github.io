package pkgclass.demo;


import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acetip
 */
public class UserIO {
    
    public void display(String message){
            System.out.println(message);
    }
    public String prompt(String message){
         display(message);
         Scanner sc = new Scanner(System.in);
         return sc.nextLine();
    }
    public boolean promptQuestion(String message){
        String answer = prompt(message);
        return "Y".equalsIgnoreCase(answer);
    }
    public int promptInt(String message){
        Scanner sc = new Scanner(System.in);
        display(message);
        return sc.nextInt();
    }
    
    
    
    
    public String promptQuestion(String message, String[] options){
        while(options.length>0){
            String answer = prompt(message);
        
        for (int i = 0; i < options.length; i++) {
            if (options[i].equalsIgnoreCase(answer)) {
                return options[i];
            }           
        }
        display("invalid choice");
    }
        
        return null;
    }
}

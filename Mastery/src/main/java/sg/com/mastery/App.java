/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.mastery;

import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sg.com.mastery.controller.FloorController;
import sg.com.mastery.dto.Order;
import sg.com.mastery.view.UserIO;
import sg.com.mastery.view.UserIOConsoleImpl;
import sg.com.mastery.view.View;

/**
 *
 * @author Bill Gates
 */
public class App {
    public static void main(String[] args) {
       /* 
        Scanner sc = new Scanner (System.in);
        System.out.println("Try putting enter");
        String line = sc.nextLine();
        
        if (line.length()==0) System.out.println("User entered nothing");

        */
       
       UserIO io = new UserIOConsoleImpl();
       View v = new View(io);
       String something="1234";
       Order o = new Order(something);
       ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            FloorController controller = ctx.getBean("controller", FloorController.class);
            controller.run();
       
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classrosterservicelayer;

import com.sg.classrosterservicelayer.DAO.ClassRosterAuditDao;
import com.sg.classrosterservicelayer.DAO.ClassRosterAuditDaoFileImpl;
import com.sg.classrosterservicelayer.DAO.ClassRosterDAO;
import com.sg.classrosterservicelayer.DAO.ClassRosterDAOFileImpl;
import com.sg.classrosterservicelayer.controller.ClassRosterController;
import com.sg.classrosterservicelayer.servicelayer.ClassRosterService;
import com.sg.classrosterservicelayer.servicelayer.ClassRosterServiceImpl;
import com.sg.classrosterservicelayer.view.ClassRosterView;
import com.sg.classrosterservicelayer.view.UserIO;
import com.sg.classrosterservicelayer.view.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author stephenespinal
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        
//        // Instantiate the UserIO implementation
//        UserIO myIo = new UserIOConsoleImpl();
//        // Instantiate the View and wire the UserIO implementation into it
//        ClassRosterView myView = new ClassRosterView(myIo);
//        // Instantiate the DAO
//        ClassRosterDAO myDao = new ClassRosterDAOFileImpl();
//        // Instantiate the Audit DAO
//        ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
//        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
//        ClassRosterService myService = new ClassRosterServiceImpl(myDao, myAuditDao);

        // Instantiate the Controller and wire the Service Layer into it
//        ClassRosterController controller = new ClassRosterController(myService, myView);
//        // Kick off the Controller
//        controller.run();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassRosterController controller = ctx.getBean("controller", ClassRosterController.class);
        controller.run();
    }
}

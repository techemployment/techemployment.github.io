/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.com.milestone3;

import sg.com.milestone3.controller.VendingController;
import sg.com.milestone3.dao.VendingAuditDao;
import sg.com.milestone3.dao.VendingDao;
import sg.com.milestone3.services.VendingServiceLayer;
import sg.com.milestone3.view.UserIO;
import sg.com.milestone3.view.UserIOConsoleImpl;
import sg.com.milestone3.view.VendingView;

/**
 *
 * @author Bill Gates
 */
public class App {
    public static void main(String[] args){
		UserIO myIo = new UserIOConsoleImpl();
		VendingAuditDao myAuditDao= new VendingAuditDao();
                VendingView myView= new VendingView(myIo);
                VendingServiceLayer myService= new VendingServiceLayer (myAuditDao);
		
		VendingController controller= new VendingController(myView, myService);
		controller.run();
		
	}
}

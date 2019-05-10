/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgclass.demo;

/**
 *
 * @author acetip
 */
public class ClassDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        UserIO io = new UserIO();
        
        
        
        Hero myHero = new Hero();
        myHero.setGoblinsKilled(10);
        String name = io.prompt("what is your hero's name?");
        myHero.setName(name);
        int age = io.promptInt("what is your hero's age?");
        myHero.setAge(age);
        
        Hero myHero2 = new Hero();
        String name2 = io.prompt("what is your second hero's name");
        myHero2.setName(name);
        int age2 = io.promptInt("what is your second hero's age?");
        myHero2.setAge(age);
        myHero2.setGoblinsKilled(20);
    }
    
}

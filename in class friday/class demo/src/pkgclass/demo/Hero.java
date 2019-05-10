/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgclass.demo;

import java.util.Random;

/**
 *
 * @author acetip
 */
public class Hero {
    private static int goblinsKilled;
    private String heroName;
    private boolean alive;
    private int age;
    private int []potions;
    private int maxHealth;
    private int health;
    private double gold;

    public Hero(){
        alive = true;
        age =1;
        potions = new int[] {5,5,5,5,5};
        Random r = new Random();
        maxHealth = r.nextInt(40)+10;
        health = maxHealth;
    }

    public int getGoblinsKilled() {
        return goblinsKilled;
    }

    public void setGoblinsKilled(int goblinsKilled) {
        Hero.goblinsKilled = goblinsKilled;
    }

    
    
    
    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int[] getPotions() {
        return potions;
    }

    public void setPotions(int[] potions) {
        this.potions = potions;
    }

    public int getMaxHealth() {
        return maxHealth;
    }



    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return alive;
    }

   

    public double getGold() {
        return gold;
    }
    
    

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    void setName(String name) {
        
    }
    

    
}

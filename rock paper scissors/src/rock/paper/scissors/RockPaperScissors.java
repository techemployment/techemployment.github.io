/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rock.paper.scissors;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Bill Gates
 */
public class RockPaperScissors {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      System.out.println("Lets play rock paper scissors!");
      String playAgain = "y";  
       
     do{  
         
        System.out.println("How many rounds would you like to play?");
        Scanner rounds = new Scanner(System.in);
         int n = rounds.nextInt();
        
            if(1 <= n && n <= 10 ){
                playGame(n);
            }else{
                if(n<0)System.out.println("you can't play negative");
                if(n>10)System.out.println("you asked for too many rounds");
                break;

            }
            
            System.out.println("Would you like to play again?(y/n)");
            Scanner input = new Scanner (System.in);
            playAgain = input.nextLine();
     }while("y".equals(playAgain));    
         
        
     
    }
    public static void playGame(int n) {
        
        int tie = 0;
        int win = 0;
        int compWin = 0;
        
        Scanner selection = new Scanner(System.in);
        
            do{
            
            Random pick = new Random();
            int pcPick = pick.nextInt(3) + 1;
            System.out.println("for rock press 1, for paper press 2, for scissors press 3");
            int rock = 1;
            int paper = 2;
            int scissors = 3;
            int userInput = selection.nextInt();


            if(userInput == pcPick){
                System.out.println("It's a tie");
                tie++;
            }
            if(userInput == rock && pcPick == scissors){
                System.out.println("User wins this round");
                win++;
            }
            if(userInput == paper && pcPick == rock){
                System.out.println("User wins this round");
                win++;
            }
            if(userInput == scissors && pcPick == paper){
                System.out.println("User wins this round");
                win++;  
            }
            if(pcPick == rock && userInput == scissors){
                System.out.println("Computer wins this round");
                compWin++;
            }
            if(pcPick == paper && userInput == rock){
                System.out.println("Computer wins this round");
                compWin++;
            }
            if(pcPick == scissors && userInput == paper){
                System.out.println("Computer wins this round");
                compWin++;
            }
            if( userInput<0 || userInput > 3){
                System.out.println("must be between 1, 2, or 3");
            }else
            n--;
            }while(n>0 );
            System.out.println("User wins: " + win);
            System.out.println("Computer wins: " + compWin);
            System.out.println("Ties: " + tie);
           
        
    }
}

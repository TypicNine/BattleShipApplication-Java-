// Dylan Ray
// CSIS 222
// This program is a single player version of the classic game Battleship! It prompts the user for their name and what difficulty they would like to play, giving 3 options.
// The grid is then displayed (3x3, 9x9, 12x12) and prompts the user which cell they would like to attack, changing the board to either a 'O' for a miss, and 'X' for a hit
// The player has a limited number of turns to complete the game (30, 50, 75) and if they reach 0 missels they lose, if they sink all the ships before that, they win

// Battleship.java calls different methods from the other two files to complete a game of Battleship

package battleShip;

import java.util.Scanner;	// imports the scanner tool

public class Battleship {	static // Main class Battleship
	
	String playerName;	// Declares a string player name
	
	public static void main(String[] args)
	  {
		
		
		Scanner userInput = new Scanner(System.in);
		System.out.print("Welcome To BattleShip! Please Enter The Player Name: ");
		playerName = userInput.nextLine();
		System.out.println("Ok " + playerName + "! Let's Play Some BattleShip!");
		System.out.println();
		System.out.println("Here Are The Rules:");
		System.out.println("The Ships Will Be Randomly Placed On The Game Board and You Will Have A Certain Number Of Missiles To Sink All The Ships.");
		System.out.println("You Will Be Prompted To Select A Row And A Column To Attack. The Game Will Notify The Player If It Is A Hit Or A Miss.");
		System.out.println("If It Is A Hit, An X Will Appear On The Game Board, A Miss Will Show A O.");
		System.out.println("If All Ships Are Sunk Before All Missiles Are Used, The Player Wins. If All Missiles Are Used, It Is Game Over!");
		System.out.println();
		
		Gameboard.difficulty();	// prompts the user to chose a difficulty setting
		Gameboard.gameboard();	// creates the gameboard based on the user defined setting
		Actiongame.playGame();	// loops a prompt for the user to choose the cell they wish to attack 
		
		Gameboard.userInput.close(); // closes the userinput for all files
		Actiongame.userInput.close();
		userInput.close();
	  }
}

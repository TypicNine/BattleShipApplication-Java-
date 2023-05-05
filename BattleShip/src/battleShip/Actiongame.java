// Dylan Ray
// CSIS 222
// This file plays the game, and receives the user input for attacks on cells in the game

package battleShip;

import java.util.Scanner;

public class Actiongame {
	
	static Scanner userInput = new Scanner(System.in);
	
	public static boolean shipLocations[][] = Gameboard.getShipLocation(); // imports the values of Gameboards ship locations and creates a 2d array ship locations
	
	public static String userRow;						// creates a variable to hold the user input for row
	public static String userCol;						// creates a variable to hold the user input for column
	public static int row;								// creates a variable for row
	public static int col;								// creates a variable for column
	public static boolean isHit;						// creates a boolean variable isHit for hit logic
	public static int turnLimit = 0;					// Initializes a variable turnLimit to set the amount of turns per difficulty
	public static int turnCheck = 0;					// Initializes a variable turnCheck to check for the first turn
	public static double shotsFired = 0;				// Initializes a variable shots fired to hold how many shots have been fired
	public static double hits = 0;						// Initializes a variable hits to count the hits
	public static double accuracy = 0.00;				// Initializes a variable accuracy to hold the users shot accuracy
	public static boolean bozo = false;					// Initializes a variable bozo to check if a shot has already been taken on the user selected cell

	public static void playGame() {						// a method that receives user input and plays the game
		
		int difficulty = Gameboard.getDifficulty();		// gets the difficulty from Gameboard and assigns it to variable difficulty
		
		if (difficulty == 1) {							// Beginner difficulty sets turns to 30
			turnLimit = 30;
			turnCheck = 30;			
		}
		if (difficulty == 2) {							// Standard difficulty sets turns to 50
			turnLimit = 50;
			turnCheck = 50;
		}
		if (difficulty == 3) {							// Advanced difficulty sets turns to 75
			turnLimit = 75;
			turnCheck = 75;
			}
		
		
		for (int i = 0; i < turnLimit; i++) {			// for loop to iterate the game till a win or the total amount of turns 
			
			userAttack();								// calls method user attack
			
			// if a cell has already been fired on bozo is set to true
			if (Gameboard.board[row][col] == "O" || Gameboard.board[row][col] == "X" ) {
				bozo = true;
			}
			
			isHit();									// calls method isHit to check for a hit
			isMiss();									// calls method isMiss to check for a miss
			Gameboard.missilesLeft--;					// decrements missilesLeft 
			shotsFired++;								// increments shotsFired
			accuracy = (hits * 100) / shotsFired;		// calculates the user accuracy 
			
			if (Gameboard.carrierCount == 0) {			// if a ship has 0 cells left the total ships counter is decremented
				Gameboard.totalShips--;
			}
			if (Gameboard.battleShipCount == 0) {
				Gameboard.totalShips--;
			}
			if (Gameboard.destroyerCount == 0) {
				Gameboard.totalShips--;
			}
			if (Gameboard.submarineCount == 0) {
				Gameboard.totalShips--;
			}
			if (Gameboard.patrolCount == 0) {
				Gameboard.totalShips--;
			}
			
			Gameboard.displayGameboard();											// displays the updated gameboard
			
			if (bozo == true) {														// if a cell is shot more than once print the following message		
				 System.out.println("You Bozo, You Already Shot There!");
			}

			if (isHit == true) {													// if a isHit is true and the cell has not been hit before print what ship is hit and decrement ship counter 
				System.out.println();
				
																					// if a ship is hit print the following message
				if (Gameboard.placeShip[row][col] == "C" && bozo == false) {
					System.out.println("You Hit Their AirCraft Carrier!");
				}
				if (Gameboard.placeShip[row][col] == "B" && bozo == false) {
					System.out.println("You Hit Their BattleShip!");
				}
				if (Gameboard.placeShip[row][col] == "S" && bozo == false) {
					System.out.println("You Hit Their Submarine!");
				}
				if (Gameboard.placeShip[row][col] == "D" && bozo == false) {
					System.out.println("You Hit Their Destroyer!");
				}
				if (Gameboard.placeShip[row][col] == "P" && bozo == false) {
					System.out.println("You Hit Their Patrol Boat!");
				}
				
																					// if you sunk a ship print the following message and decrement the ship counter
				if (Gameboard.carrierCount == 0 && bozo == false) {
					System.out.println("You Have Sunk The USS Roosevelt AirCraft Carrier!");
					Gameboard.carrierCount--;
				}
				if (Gameboard.battleShipCount == 0 && bozo == false) {
					System.out.println("You Have Sunk The USS Virginia Class BattleShip!");
					Gameboard.battleShipCount--;
				}
				if (Gameboard.destroyerCount == 0 && bozo == false) {
					System.out.println("You Have Sunk The USS Lawrence Destroyer!");
					Gameboard.destroyerCount--;
				}
				if (Gameboard.submarineCount == 0 && bozo == false) {
					System.out.println("You Have Sunk The USS Ohio Class Submarine!");
					Gameboard.submarineCount--;
				}
				if (Gameboard.patrolCount == 0 && bozo == false) {
					System.out.println("You Have Sunk The USS Patrol Boat!");
					Gameboard.patrolCount--;
				}
			}
			
			if (isHit == false) {												// if a shot is missed print the following message
				System.out.println("That's A Miss!");
			}
			if (Gameboard.missilesLeft == 0) {									// if the amount of missiles or turns is 0 print the following message
				System.out.println("YOU LOSE! YOU RAN OUT OF SHOTS!");
			}
			if (Gameboard.totalShips == 0) {									// if all ships are sunk before missiles or turns are 0 print the following message
				System.out.println(Battleship.playerName.toUpperCase() + " YOU WIN! ALL SHIPS SUNK!");
				System.exit(0);													// exit the game
			}
			bozo = false;														// assign bozo false at the end of each turn
			isHit = false;														// assign isHit false at the end of each turn
		}
	}
	
	public static void userAttack() {										// A method that prompts the user for a cell to attack
		
		System.out.println();
		System.out.print("Please Enter The Row You Wish To Attack: ");
		userRow = userInput.nextLine().toUpperCase();						// saves upper case of letter selection for the row in userRow
		System.out.println();
		System.out.print("Please Enter The Column You Wish To Attack: ");
		userCol = userInput.nextLine();										// saves the column selection in userCol
		System.out.println();
		
		switch (userRow) {			// switch case that assigns the userInput for row a number value
			
			case "A": 
				row = 0;
				break;
			case "B": 
				row = 1;
				break;
			case "C": 
				row = 2;
				break;
			case "D": 
				row = 3;
				break;
			case "E": 
				row = 4;
				break;
			case "F": 
				row = 5;
				break;
			case "G": 
				row = 6;
				break;
			case "H": 
				row = 7;
				break;
			case "I": 
				row = 8;
				break;
			case "J": 
				row = 9;
				break;
			case "K": 
				row = 10;
				break;
			case "L": 
				row = 11;
				break;
		}
				
		switch (userCol) {			// switch case that assigns the userInput for column a number value
				
			case "1": 
				col = 0;
				break;
			case "2": 
				col = 1;
				break;
			case "3": 
				col = 2;
				break;
			case "4": 
				col = 3;
				break;
			case "5": 
				col = 4;
				break;
			case "6": 
				col = 5;
				break;
			case "7": 
				col = 6;
				break;
			case "8": 
				col = 7;
				break;
			case "9": 
				col = 8;
				break;
			case "10": 
				col = 9;
				break;
			case "11": 
				col = 10;
				break;
			case "12": 
				col = 11;
				break;
		}	
	}
	
	public static boolean isHit() {									// method that checks user selected cell for a ship
		
		if (shipLocations[row][col] == true && bozo == false) {		// if user selected cell hasn't been hit and has a ship decrement the ship counter
			if (Gameboard.placeShip[row][col] == "C") {
				Gameboard.carrierCount--;
			}
			if (Gameboard.placeShip[row][col] == "B") {
				Gameboard.battleShipCount--;
			}
			if (Gameboard.placeShip[row][col] == "D") {
				Gameboard.destroyerCount--;
			}
			if (Gameboard.placeShip[row][col] == "S") {
				Gameboard.submarineCount--;
			}
			if (Gameboard.placeShip[row][col] == "P") {
				Gameboard.patrolCount--;
			}
			isHit = true;			// change isHit to true			
			hits++;					// increment hits
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean isMiss() {								// method that checks user selected cell for a missed shot
		if (shipLocations[row][col] == false && shotsFired != 0) {	// if the user selected cell is empty return true
			return true;
		}
		else {
			return false;
		}
	}	
}

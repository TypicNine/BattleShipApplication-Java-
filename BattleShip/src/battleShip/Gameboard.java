// Dylan Ray
// CSIS 222
// This file creates the gameboard, populates the ships in random locations, in a random direction (vertical or horizontal)
// The ships are also defined in separate methods, and their total number of tiles is defined

package battleShip;

import java.util.Scanner;
import java.util.Random;

public class Gameboard { 

	public static String board[][]; // creates the visual 2d array the user sees
	public static String placeShip[][]; // creates the 2d array for the test display that shows ship location
	public static boolean shipLocation[][]; // creates a boolean 2d array that hold a true value where ships are located
	public static final String row[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};		// string array for displaying the row labels 
	public static final String column[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};	// string array for displaying the column labels 
	
	public static int missilesLeft;			// int variable that holds how many missiles in the game are left
	public static int tempRow;				// stores the value of the row in a changing variable for the ship placement check
	public static int tempCol;				// stores the value of the column in a changing variable for the ship placement check
	public static int difficulty = 0;		// creates the variable that stores the users choice for the difficulty
	public static int carrierCount = 5;		// creates and initializes the variable that holds the count for the carrier
	public static int battleShipCount = 4;	// creates and initializes the variable that holds the count for the battleship
	public static int patrolCount = 2;		// creates and initializes the variable that holds the count for the patrol boat
	public static int destroyerCount = 3;	// creates and initializes the variable that holds the count for the destroyer
	public static int submarineCount = 3;	// creates and initializes the variable that holds the count for the submarine
	public static int totalShips = 5;		// creates and initializes the variable that holds the count for the total amount of ships
	
	Random rand = new Random();							// creates an object rand for the random placement of ships
	static Scanner userInput = new Scanner(System.in);	// defines object userinput for the scanner
	
	public static boolean[][] getShipLocation() {		// getter to share the ship location 2d array with Actiongame.java
		boolean[][] x = shipLocation.clone();			// clones shiplocation[][]
		return x;
	}
	
	public static int getDifficulty() {					// getter to share the difficulty int with Actiongame.java
		
		int x = difficulty;								// assigns int x with the value from difficulty
		return x;
	}
	
	public static int difficulty() {																	// method that prompts the user for the chosen difficulty of the game
		
		System.out.print("Please Select a Difficulty: 1 (Beginner 6x6), 2 (Standard 9x9), 3 (Advanced 12x12): ");
		difficulty = userInput.nextInt();																// sets difficulty to the user input

		if (difficulty == 1) {			// Output for Beginner difficulty
			System.out.println("Beginner: Good choice for some easy practice!");
		}
		if (difficulty == 2) {			// Output for Standard difficulty
			System.out.println("Standard: An average challenge, good luck out there!");
		}
		if (difficulty == 3) {			// Output for Advanced difficulty
			System.out.println("Advanced: So looking for a challenge, then you got one. Let's go!");
		}
		
		return difficulty;
}
	
	public static void gameboard() {						// method that creates and populates the gameboard 
		
		if (difficulty == 1) {								// logic for the Beginner difficulty of the game
		
			shipLocation = new boolean[6][6];				// creates boolean 2d array for the game board
			board = new String[6][6];						// creates 2d array game board to display to the user
			placeShip = new String[6][6];					// creates the 2d array for the test display for the ship locations
			missilesLeft = 30;								// assigns missiles left to 30 turns
		
			for (int i = 0; i < board.length; i++) {		// for loop that populates board and placeShips with either a '~' or a '+'
				for (int j = 0; j < board.length; j++) {
					board[i][j] = "~";
					placeShip[i][j] = "+";
				}
			}
		}
		
		if (difficulty == 2) {								// logic for the Standard difficulty of the game
															// for documentation see beginner
			shipLocation = new boolean[9][9];
			board = new String[9][9];
			placeShip = new String[9][9];
			missilesLeft = 50;
			
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					board[i][j] = "~";
					placeShip[i][j] = "+";
				}
			}
		}
		
		if (difficulty == 3) {								// logic for the Advanced difficulty of the game
															// for documentation see beginner
			shipLocation = new boolean[12][12];
			board = new String[12][12];
			placeShip = new String[12][12];
			missilesLeft = 75;
			
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					board[i][j] = "~";
					placeShip[i][j] = "+";
				}
			}
		}
		
		System.out.println();
		System.out.println();
		
		battleShip();					// runs the battleship method for ship population
		patrol();						// runs the patrol boat method for ship population
		carrier();						// runs the carrier method for ship population
		destroyer();					// runs the destroyer method for ship population
		submarine();					// runs the submarine method for ship population
		displayGameboard();				// displays the game board to the user
	}
	
	public static void displayGameboard() {			// method that displays and updates the game board each turn
		
		String row[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};			// array that hold row labels
		String column[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};		// array that holds column labels
	
		if (difficulty == 1) {													// the display logic for the Beginner difficulty
			
			if (Actiongame.isHit == true) {										// if the user hits a ship, it triggers isHit in Actiongame and changes board to an 'X'
				board[Actiongame.row][Actiongame.col] = "X";
			}
			
			if (Actiongame.isMiss() == true) {									// if the user misses a ship, it triggers isMiss in Actiongame and changes board to an 'O'
				board[Actiongame.row][Actiongame.col] = "O";
			}
	
			for (int i = 0; i < board.length; i++) {							// for loop that displays the column labels
				System.out.print("    " + column[i]);
			}
			System.out.print("\n");
			
			for (int i = 0; i < board.length; i++) {							// for loop that displays the board depending on the difficulty set size including row labels
				for (int j = 0; j < board.length; j++) {
					if (j == 0)
						System.out.print(row[i] + " ");
					System.out.print("| " + board[i][j] + " |");
				}
				System.out.println();
			}
			
			System.out.println();
			
			// Tests
			
			System.out.println();
			System.out.println("            Ships");					
			
			for (int i = 0; i < board.length; i++) {							// for loop that displays the column labels
				System.out.print("    " + column[i]);
				
			}
			System.out.print("\n");
			
			for (int i = 0; i < board.length; i++) {							// for loop that displays placeShips depending on the difficulty set size including row labels
				for (int j = 0; j < board.length; j++) {
					if (j == 0)
						System.out.print(row[i] + " ");
					System.out.print("| " + placeShip[i][j] + " |");
				}
				System.out.println();
			}
			
			System.out.println();
			System.out.printf("Missels Left: %d", missilesLeft);			// displays the amount of missiles left
			System.out.printf("   Ships Left: %d", totalShips);				// displays the amount of ships left
			System.out.printf("   Accuaracy: %3.2f", Actiongame.accuracy);	// displays the accuracy of the shots taken
			System.out.print("%\n");	
			System.out.println();
		}	
		
		if (difficulty == 2) {												// the display logic for the Standard difficulty
																			// for documentation see Beginner difficulty
			if (Actiongame.isHit == true) {
				board[Actiongame.row][Actiongame.col] = "X";
			}
			
			if (Actiongame.isMiss() == true) {
				board[Actiongame.row][Actiongame.col] = "O";
			}
			
			for (int i = 0; i < board.length; i++) {
				System.out.print("    " + column[i]);
			}
			System.out.print("\n");

			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					if (j == 0)
						System.out.print(row[i] + " ");
					System.out.print("| " + board[i][j] + " |");
				}
				System.out.println();
			}
			
			//Tests
			
			System.out.println();
			System.out.println("                    Ships");
			
			for (int i = 0; i < board.length; i++) {
				System.out.print("    " + column[i]);
				
			}
			System.out.print("\n");
			
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					if (j == 0)
						System.out.print(row[i] + " ");
					System.out.print("| " + placeShip[i][j] + " |");
				}
				System.out.println();
			}
			
			System.out.println();
			System.out.printf("Missels Left: %d", missilesLeft);
			System.out.printf("   Ships Left: %d", totalShips);
			System.out.printf("   Accuaracy: %3.2f", Actiongame.accuracy);
			System.out.print("%\n");	
			System.out.println();
		}
		
		if (difficulty == 3) {												// the display logic for the Standard difficulty
																			// for documentation see the Beginner difficulty
			if (Actiongame.isHit == true) {
				board[Actiongame.row][Actiongame.col] = "X";
			}
			
			if (Actiongame.isMiss() == true) {
				board[Actiongame.row][Actiongame.col] = "O";
			}
			
			for (int i = 0; i < board.length; i++) {
				
				int x = Integer.parseInt(column[i]);
				
				if (x >= 10) {
					System.out.print("   "+ column[i]);
				}
				else
					System.out.print("    " + column[i]);
			}
			
			System.out.print("\n");
			
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					if (j == 0)
						System.out.print(row[i] + " ");
					System.out.print("| " + board[i][j] + " |");
				}
				System.out.println();
			}
			
			//Tests
			
			System.out.println();
			System.out.println("                           Ships");
			
			for (int i = 0; i < board.length; i++) {
				
				int x = Integer.parseInt(column[i]);
				
				if (x >= 10) {
					System.out.print("   "+ column[i]);
				}
				else
					System.out.print("    " + column[i]);
			}
			System.out.print("\n");
			
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					if (j == 0)
						System.out.print(row[i] + " ");
					System.out.print("| " + placeShip[i][j] + " |");
				}
				System.out.println();
			}
			
			System.out.println();
			System.out.printf("Missels Left: %d", missilesLeft);
			System.out.printf("   Ships Left: %d", totalShips);
			System.out.printf("   Accuaracy: %3.2f", Actiongame.accuracy);
			System.out.print("%\n");	
			System.out.println();
		}
	}
	

	public static void carrier() {								// method for the placement of the carrier ship
		
		int x = new Random().nextInt(2);						// assigns variable x to a random number 0 or 1
	
		for (int i = 0; i < 1; i++) { 							// for loop that checks random number 1 being vertical placement 0 being horizontal placement
	
			if (x == 1) {										// if the random number is 1 the ship will be placed vertically
		
				int n = shipLocation.length - 4;				// ensures the ship can't go past the edge of the board
				int m = shipLocation[0].length;

				int randomRow = new Random().nextInt(n);		// gets a random row number
				int randomCol = new Random().nextInt(m);		// gets a random column number
		
				tempRow = randomRow;							// assigns the random row to the tempRow variable
				tempCol = randomCol;							// assigns the random column to the tempCol variable
		
				// if the randomly selected row or any of the 4 remaining cells of the ship already have a ship there, finds new placement
				if (shipLocation[randomRow][randomCol] == true || shipLocation[randomRow + 1][randomCol] == true || shipLocation[randomRow + 2][randomCol]  == true 
				|| shipLocation[randomRow + 3][randomCol] == true || shipLocation[randomRow + 4][randomCol] == true) {

				randomRow = new Random().nextInt(n);			// selects new random row
				randomCol = new Random().nextInt(m);			// selects new random column
			
				i--;											// decrements i to continue the loop
			}
		
			else {												// if no ships are in the path of placement, it places the carrier
				placeShip[tempRow][tempCol] = "C";				// assigns placeShip to C
				shipLocation[tempRow][tempCol] = true;			// assigns the shipLocation to true
				placeShip[tempRow + 1][tempCol] = "C";			
				shipLocation[tempRow + 1][tempCol] = true;
				placeShip[tempRow + 2][tempCol] = "C";			
				shipLocation[tempRow + 2][tempCol] = true;
				placeShip[tempRow + 3][tempCol] = "C";			
				shipLocation[tempRow + 3][tempCol] = true;
				placeShip[tempRow + 4][tempCol] = "C";			
				shipLocation[tempRow + 4][tempCol] = true;
		
				i++;											// increments the loop to end 
			}
		}
		else {													// Horizontal logic for placement
																// see Vertical logic for documentation
			int n = shipLocation.length;
			int m = shipLocation[0].length - 4;					

			int randomRow = new Random().nextInt(n);			
			int randomCol = new Random().nextInt(m);
		
			tempRow = randomRow;
			tempCol = randomCol;
		
			if (shipLocation[randomRow][randomCol] == true || shipLocation[randomRow][randomCol + 1] == true || shipLocation[randomRow][randomCol + 2]  == true 
					|| shipLocation[randomRow][randomCol + 3] == true || shipLocation[randomRow][randomCol + 4] == true) {

				randomRow = new Random().nextInt(n);
				randomCol = new Random().nextInt(m);
			
				i--;
			}
		
			else {
				placeShip[tempRow][tempCol] = "C";
				shipLocation[tempRow][tempCol] = true;
				placeShip[tempRow][tempCol + 1] = "C";
				shipLocation[tempRow][tempCol + 1] = true;
				placeShip[tempRow][tempCol + 2] = "C";
				shipLocation[tempRow][tempCol + 2] = true;
				placeShip[tempRow][tempCol + 3] = "C";	
				shipLocation[tempRow][tempCol + 3] = true;
				placeShip[tempRow][tempCol + 4] = "C";	
				shipLocation[tempRow][tempCol + 4] = true;
		
				i++;
			}
		}
	}
}
	
	public static void battleShip() {							// method for the placement of the battleship
		
		int x = new Random().nextInt(2);						// assigns variable x to a random number 0 or 1
		
		for (int i = 0; i < 1; i++) {							// for loop that checks random number 1 being vertical placement 0 being horizontal placement
		
			if (x == 1) {										// if the random number is 1 the ship will be placed vertically
				
				int n = shipLocation.length - 3;				// ensures the ship can't go past the edge of the board
				int m = shipLocation[0].length;

				int randomRow = new Random().nextInt(n);		// gets a random row number
				int randomCol = new Random().nextInt(m);		// gets a random column number
			
				tempRow = randomRow;							// assigns the random row to the tempRow variable
				tempCol = randomCol;							// assigns the random column to the tempCol variable
			
				// if the randomly selected row or any of the 4 remaining cells of the ship already have a ship there, finds new placement
				if (shipLocation[randomRow][randomCol] == true || shipLocation[randomRow + 1][randomCol] == true || shipLocation[randomRow + 2][randomCol]  == true 
						|| shipLocation[randomRow + 3][randomCol] == true) {

					randomRow = new Random().nextInt(n);		// selects new random row
					randomCol = new Random().nextInt(m);		// selects new random column
					
					i--;										// decrements i to continue the loop
				}
			
				else {											// if no ships are in the path of placement, it places the battleship
					placeShip[tempRow][tempCol] = "B";			// assigns placeShip to B
					shipLocation[tempRow][tempCol] = true;		// assigns the shipLocation to true
					placeShip[tempRow + 1][tempCol] = "B";
					shipLocation[tempRow + 1][tempCol] = true;
					placeShip[tempRow + 2][tempCol] = "B";
					shipLocation[tempRow + 2][tempCol] = true;
					placeShip[tempRow + 3][tempCol] = "B";
					shipLocation[tempRow + 3][tempCol] = true;
					
					i++;										// increments the loop to end 
				}
			}
			else {												// Horizontal logic for placement
																// see Vertical logic for documentation
				int n = shipLocation.length;
				int m = shipLocation[0].length - 3;
				
				int randomRow = new Random().nextInt(n);
				int randomCol = new Random().nextInt(m);
				
				tempRow = randomRow;
				tempCol = randomCol;
				
				if (shipLocation[randomRow][randomCol] == true || shipLocation[randomRow][randomCol + 1] == true || shipLocation[randomRow][randomCol + 2]  == true 
					|| shipLocation[randomRow][randomCol + 3] == true) {

					randomRow = new Random().nextInt(n);
					randomCol = new Random().nextInt(m);
				
					i--;
				}
			
				else {
					placeShip[tempRow][tempCol] = "B";
					shipLocation[tempRow][tempCol] = true;
					placeShip[tempRow][tempCol + 1] = "B";
					shipLocation[tempRow][tempCol + 1] = true;
					placeShip[tempRow][tempCol + 2] = "B";
					shipLocation[tempRow][tempCol + 2] = true;
					placeShip[tempRow][tempCol + 3] = "B";	
					shipLocation[tempRow][tempCol + 3] = true;
					
					i++;
				}
			}
		}
	}
		
	public static void submarine() {							// method for the placement of the submarine
		
		int x = new Random().nextInt(2);						// assigns variable x to a random number 0 or 1
	
		for (int i = 0; i < 1; i++) {							// for loop that checks random number 1 being vertical placement 0 being horizontal placement
		
			if (x == 1) {										// if the random number is 1 the ship will be placed vertically
				
				int n = shipLocation.length - 2;				// ensures the ship can't go past the edge of the board
				int m = shipLocation[0].length;

				int randomRow = new Random().nextInt(n);		// gets a random row number
				int randomCol = new Random().nextInt(m);		// gets a random column number
			
				tempRow = randomRow;							// assigns the random row to the tempRow variable
				tempCol = randomCol;							// assigns the random column to the tempCol variable
			
				// if the randomly selected row or any of the 4 remaining cells of the ship already have a ship there, finds new placement
				if (shipLocation[randomRow][randomCol] == true || shipLocation[randomRow + 1][randomCol] == true || shipLocation[randomRow + 2][randomCol]  == true ) {

					randomRow = new Random().nextInt(n);		// selects new random row
					randomCol = new Random().nextInt(m);		// selects new random column
					
					i--;										// decrements i to continue the loop
				}
			
				else {											// if no ships are in the path of placement, it places the submarine
					placeShip[tempRow][tempCol] = "S";			// assigns placeShip to S
					shipLocation[tempRow][tempCol] = true;		// assigns the shipLocation to true
					placeShip[tempRow + 1][tempCol] = "S";
					shipLocation[tempRow + 1][tempCol] = true;
					placeShip[tempRow + 2][tempCol] = "S";
					shipLocation[tempRow + 2][tempCol] = true;
			
					i++;										// increments the loop to end 
				}
			}
			else {												// Horizontal logic for placement
																// see Vertical logic for documentation
				int n = shipLocation.length;
				int m = shipLocation[0].length - 2;
				
				int randomRow = new Random().nextInt(n);
				int randomCol = new Random().nextInt(m);
				
				tempRow = randomRow;
				tempCol = randomCol;
				
				if (shipLocation[randomRow][randomCol] == true || shipLocation[randomRow][randomCol + 1] == true || shipLocation[randomRow][randomCol + 2]  == true ) {

					randomRow = new Random().nextInt(n);
					randomCol = new Random().nextInt(m);
				
					i--;
				}
			
				else {
					placeShip[tempRow][tempCol] = "S";
					shipLocation[tempRow][tempCol] = true;		
					placeShip[tempRow][tempCol + 1] = "S";
					shipLocation[tempRow][tempCol + 1] = true;
					placeShip[tempRow][tempCol + 2] = "S";
					shipLocation[tempRow][tempCol + 2] = true;
					
					i++;										
				}
			}
		}
	}
	
	public static void destroyer() {							// method for the placement of the destroyer
		
		int x = new Random().nextInt(2);						// assigns variable x to a random number 0 or 1
		
		for (int i = 0; i < 1; i++) {							// for loop that checks random number 1 being vertical placement 0 being horizontal placement

			if (x == 1) {										// if the random number is 1 the ship will be placed vertically
				
				int n = shipLocation.length - 2;				// ensures the ship can't go past the edge of the board
				int m = shipLocation[0].length;

				int randomRow = new Random().nextInt(n);		// gets a random row number
				int randomCol = new Random().nextInt(m);		// gets a random column number
			
				tempRow = randomRow;							// assigns the random row to the tempRow variable
				tempCol = randomCol;							// assigns the random column to the tempCol variable
			
				// if the randomly selected row or any of the 4 remaining cells of the ship already have a ship there, finds new placement
				if (shipLocation[randomRow][randomCol] == true || shipLocation[randomRow + 1][randomCol] == true || shipLocation[randomRow + 2][randomCol]  == true ) {

					randomRow = new Random().nextInt(n);		// selects new random row
					randomCol = new Random().nextInt(m);		// selects new random column
					
					i--;										// decrements i to continue the loop
				}
			
				else {											// if no ships are in the path of placement, it places the destroyer
					placeShip[tempRow][tempCol] = "D";			// assigns placeShip to D
					shipLocation[tempRow][tempCol] = true;		// assigns the shipLocation to true
					placeShip[tempRow + 1][tempCol] = "D";
					shipLocation[tempRow + 1][tempCol] = true;
					placeShip[tempRow + 2][tempCol] = "D";
					shipLocation[tempRow + 2][tempCol] = true;
			
					i++;										// increments the loop to end 
				}
			}
			else {												// Horizontal logic for placement
																// see Vertical logic for documentation
				int n = shipLocation.length;
				int m = shipLocation[0].length - 2;
				
				int randomRow = new Random().nextInt(n);
				int randomCol = new Random().nextInt(m);
				
				tempRow = randomRow;
				tempCol = randomCol;
				
				if (shipLocation[randomRow][randomCol] == true || shipLocation[randomRow][randomCol + 1] == true || shipLocation[randomRow][randomCol + 2]  == true ) {

					randomRow = new Random().nextInt(n);
					randomCol = new Random().nextInt(m);
				
					i--;
				}
			
				else {
					placeShip[tempRow][tempCol] = "D";
					shipLocation[tempRow][tempCol] = true;
					placeShip[tempRow][tempCol + 1] = "D";
					shipLocation[tempRow][tempCol + 1] = true;
					placeShip[tempRow][tempCol + 2] = "D";
					shipLocation[tempRow][tempCol + 2] = true;
	
					i++;
				}
			}
		}
	}
	
	public static void patrol() {								// method for the placement of the patrol boat
		
		int x = new Random().nextInt(2);						// assigns variable x to a random number 0 or 1
		
		for (int i = 0; i < 1; i++) {							// for loop that checks random number 1 being vertical placement 0 being horizontal placement
		
			if (x == 1) {										// if the random number is 1 the ship will be placed vertically
			
				int n = shipLocation.length - 1;				// ensures the ship can't go past the edge of the board
				int m = shipLocation[0].length;

				int randomRow = new Random().nextInt(n);		// gets a random row number
				int randomCol = new Random().nextInt(m);		// gets a random column number
			
				tempRow = randomRow;							// assigns the random row to the tempRow variable
				tempCol = randomCol;
			
				// if the randomly selected row or any of the 4 remaining cells of the ship already have a ship there, finds new placement
				if (shipLocation[randomRow][randomCol] == true || shipLocation[randomRow + 1][randomCol] == true) {

					randomRow = new Random().nextInt(n);		// selects new random row
					randomCol = new Random().nextInt(m);		// selects new random column
					
					i--;										// decrements i to continue the loop
				}
			
				else {											// if no ships are in the path of placement, it places the patrol boat
					placeShip[tempRow][tempCol] = "P";			// assigns placeShip to P
					shipLocation[tempRow][tempCol] = true;		// assigns the shipLocation to true
					placeShip[tempRow + 1][tempCol] = "P";
					shipLocation[tempRow + 1][tempCol] = true;
				
					i++;										// increments the loop to end 
				}
			}
			else {												// Horizontal logic for placement
																// see Vertical logic for documentation
				int n = shipLocation.length;
				int m = shipLocation[0].length - 1;

				int randomRow = new Random().nextInt(n);
				int randomCol = new Random().nextInt(m);
			
				tempRow = randomRow;
				tempCol = randomCol;
			
				if (shipLocation[randomRow][randomCol] == true || shipLocation[randomRow][randomCol + 1] == true) {

					randomRow = new Random().nextInt(n);
					randomCol = new Random().nextInt(m);
				
					tempRow = randomRow;
					tempCol = randomCol;
				
					i--;
				}
			
				else {	
					placeShip[tempRow][tempCol] = "P";
					shipLocation[tempRow][tempCol] = true;
					placeShip[tempRow][tempCol + 1] = "P";
					shipLocation[tempRow][tempCol + 1] = true;
			
					i++;
				}
			}
		}	
	}
}
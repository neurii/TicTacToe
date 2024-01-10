//Name: Hanna Park
//Project: Tictactoe- smart AI
//Date: Oct 21, 2022

package ticTacToe;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SmartAI {
	public static void main(String args[]) throws Exception {
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in), 1);
		GameBoard game = new GameBoard();// makes a blank gameboard
		String player;
		int turn = 0;
		int aiTurn = 0;
		int humanTurn = 0;
		int index;
		boolean errorFlag;
		boolean gameOver = false;
		int[] winCheck = { 0, 0, 0, 0, 0, 0, 0, 0 };

		System.out.print("Please Enter Player's Name: ");
		player = keyboard.readLine();
		System.out.println(player + " you are X's");
		System.out.println("AI is O's");
		System.out.println("Press ENTER to continue");
		keyboard.readLine();
		System.out.println();
		System.out.println();
		game.drawBoard();
		System.out.println();
		System.out.println();

		//initialize arrays that store ai and human moves
		int[] ai = { -1, -1, -1, -1, -1 };
		int[] human = { -2, -2, -2, -2, -2 };

		while (gameOver == false) {
			errorFlag = false;
			if (turn % 2 == 0) {// if it is player 1's turn
				do {
					index = game.AImove(ai, human, turn);// Assumes an integer is entered
					errorFlag = game.play("X", index);
					if (errorFlag == true) {
						ai[aiTurn] = index;
					}
					if (errorFlag == false)
						System.out.println("");
				} while (errorFlag == false);// makes sure player enters a square not yet used
				System.out.println();
				System.out.println();
				game.drawBoard();
				System.out.println();
				System.out.println();
				if (game.checkWin("X", winCheck)) {
					System.out.println("AI WIN!!");
					gameOver = true;
				}
				aiTurn++;

			} else {// player 2s turn
				do {
					System.out.print(player + " enter your choice (1-9): ");
					index = Integer.parseInt(keyboard.readLine());// Assumes a valid number is entered
					errorFlag = game.play("O", index);
					if (errorFlag == true) {
						human[humanTurn] = index;
					}
					if (errorFlag == false)
						System.out.println("That square is already taken or invalid.  Try again");
				} while (errorFlag == false);// makes sure player enters a square not yet used
				System.out.println();
				System.out.println();
				game.drawBoard();
				System.out.println();
				System.out.println();
				if (game.checkWin("O", winCheck)) {
					System.out.println("You WIN!!");
					gameOver = true;
				}
				humanTurn++;
			}
			turn++;
			if (turn == 9 && gameOver == false) {
				gameOver = true;
				System.out.println("DRAW");
			}
		} // end while
	}// end main
}// end class

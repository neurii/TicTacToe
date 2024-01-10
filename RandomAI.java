//Name: Hanna Park
//Project: Tictactoe- random AI
//Date: Oct 21, 2022
package ticTacToe;

import java.io.*;

public class RandomAI {
	public static void main(String args[]) throws Exception {
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in), 1);
		GameBoard game = new GameBoard();// makes a blank gameboard
		String player1;
		int rand;
		int turn = 0;
		int index;
		boolean errorFlag;
		boolean gameOver = false;
		int[] winCheck = { 0, 0, 0, 0, 0, 0, 0, 0 };

		System.out.print("Please Enter Player 1's Name: ");
		player1 = keyboard.readLine();
		System.out.println(player1 + " you are X's");
		System.out.println("Computer is O's ");
		System.out.println("Press ENTER to continue");
		keyboard.readLine();
		System.out.println();
		System.out.println();
		game.drawBoard();
		System.out.println();
		System.out.println();
		while (gameOver == false) {
			errorFlag = false;
			if (turn % 2 == 0) {// if it is player 1's turn
				do {
					System.out.print(player1 + " enter your choice (1-9): ");
					index = Integer.parseInt(keyboard.readLine());// Assumes a valid number is entered
					errorFlag = game.play("X", index);
					if (errorFlag == false)
						System.out.println("That square is already taken or invalid.  Try again");
				} while (errorFlag == false);// makes sure player enters a square not yet used
				System.out.println();
				System.out.println();
				game.drawBoard();
				System.out.println();
				System.out.println();
				if (game.checkWin("X", winCheck)) {
					System.out.println(player1 + ", YOU WIN!!");
					gameOver = true;
				}
			} else {// player 2s turn
				do {
				Thread.sleep(500);
					rand = (int) (Math.random() * 9) + 1;// Assumes an integer is entered
					errorFlag = game.play("O", rand);
					if (errorFlag == false)
						System.out.println("That square is already taken or invalid.  Try again");
				} while (errorFlag == false);// makes sure player enters a square not yet used
				System.out.println();
				System.out.println();
				game.drawBoard();
				System.out.println();
				System.out.println();
				if (game.checkWin("O", winCheck)) {
					System.out.println("YOU LOSE!!");
					gameOver = true;
				}
			}
			turn++;
			if (turn == 9 && gameOver == false) {
				gameOver = true;
				System.out.println("DRAW");
			}
		} // end while
	}// end main
}// end class

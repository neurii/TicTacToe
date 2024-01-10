//Name: Hanna Park
//Project: Tictactoe- learning AI
//Date: Oct 21, 2022
package ticTacToe;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LearningAI {
	public static void main(String args[]) throws Exception {
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in), 1);
		Scanner in = new Scanner(System.in);
		int games = 1;
		ArrayList<ArrayList<Integer>> record = new ArrayList<ArrayList<Integer>>();
		// check which winning combination is used to win
		//will be changed to -1 if the winning combination have been used
		int[] winCheck = { 0, 0, 0, 0, 0, 0, 0, 0 };

		//AI does not know any combinations at first. But, it learns through games.
		int win1[] = { 0, 0, 0 };
		int win2[] = { 0, 0, 0 };
		int win3[] = { 0, 0, 0 };
		int win4[] = { 0, 0, 0 };
		int win5[] = { 0, 0, 0 };
		int win6[] = { 0, 0, 0 };
		int win7[] = { 0, 0, 0 };
		int win8[] = { 0, 0, 0 };

		int block1[] = { 0, 0, 0 };
		int block2[] = { 0, 0, 0 };
		int block3[] = { 0, 0, 0 };
		int block4[] = { 0, 0, 0 };
		int block5[] = { 0, 0, 0 };
		int block6[] = { 0, 0, 0 };
		int block7[] = { 0, 0, 0 };
		int block8[] = { 0, 0, 0 };

		String player;
		System.out.print("Please Enter Player's Name: ");
		player = keyboard.readLine();
		System.out.println(player + " you are O's");
		System.out.println("AI is X's");
		System.out.println("Press ENTER to continue");
		keyboard.readLine();
		System.out.println();
		System.out.println();
		while (true) {
			GameBoard game = new GameBoard();// makes a blank gameboard
			int turn = 0;
			int aiTurn = 0;
			int humanTurn = 0;
			int index;
			boolean errorFlag;
			boolean gameOver = false;
			int[] ai = { -1, -1, -1, -1, -1 };
			int[] human = { -2, -2, -2, -2, -2 };
			ArrayList<Integer> aimove = new ArrayList<Integer>();
			ArrayList<Integer> playermove = new ArrayList<Integer>();
			ArrayList<Integer> move = new ArrayList<Integer>();
			
			game.drawBoard();
			System.out.println();
			System.out.println();
			
			while (gameOver == false) {
				//System.out.print(Arrays.toString(winCheck));
				//AI learns a combination when the combination has used by human or AI itself by chance
				if (winCheck[0] == -1) {
					win1[0] = 1;
					win1[1] = 2;
					win1[2] = 3;
					block1[0] = 1;
					block1[1] = 2;
					block1[2] = 3;
				}
				if (winCheck[1] == -1) {
					win2[0] = 4;
					win2[1] = 5;
					win2[2] = 6;
					block2[0] = 4;
					block2[1] = 5;
					block2[2] = 6;
				}
				if (winCheck[2] == -1) {
					win3[0] = 7;
					win3[1] = 8;
					win3[2] = 9;
					block3[0] = 7;
					block3[1] = 8;
					block3[2] = 9;
				}
				if (winCheck[3] == -1) {
					win4[0] = 1;
					win4[1] = 4;
					win4[2] = 7;
					block4[0] = 1;
					block4[1] = 4;
					block4[2] = 7;
				}
				if (winCheck[4] == -1) {
					win5[0] = 2;
					win5[1] = 5;
					win5[2] = 8;
					block5[0] = 2;
					block5[1] = 5;
					block5[2] = 8;
				}
				if (winCheck[5] == -1) {
					win6[0] = 3;
					win6[1] = 6;
					win6[2] = 9;
					block6[0] = 3;
					block6[1] = 6;
					block6[2] = 9;
				}
				if (winCheck[6] == -1) {
					win7[0] = 1;
					win7[1] = 5;
					win7[2] = 9;
					block7[0] = 1;
					block7[1] = 5;
					block7[2] = 9;
				}
				if (winCheck[7] == -1) {
					win8[0] = 3;
					win8[1] = 5;
					win8[2] = 7;
					block8[0] = 3;
					block8[1] = 5;
					block8[2] = 7;
				}

				int win[][] = { win1, win2, win3, win4, win5, win6, win7, win8 };
				int block[][] = { block1, block2, block3, block4, block5, block6, block7, block8 };
				
				errorFlag = false;
				if (turn % 2 == 0) {// if it is player 1's turn
					do {
						index = game.learningAI(win, block, ai, human);// Assumes an integer is entered
						errorFlag = game.play("X", index);
						if (errorFlag == true) {
							ai[aiTurn] = index;
							aimove.add(index);
							move.add(index);
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
						record.add(move);
						System.out.println(record.toString());
						System.out.println("Ai Win!");
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
							playermove.add(index);
							move.add(index);
						}
						if (errorFlag == false)
							System.out.println("That square is already taken or invalid. Try again");
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
			}
			games++;
			System.out.println("num of games: " + games);
			// end while
		}
	}// end main
}// end class

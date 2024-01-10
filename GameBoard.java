package ticTacToe;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class for Tic Tac Toe gameboard
 * 
 * @Hanna Park
 */
public class GameBoard {
	/**
	 * An array of 9 GameTiles representing the TicTacToe gameboard
	 */
	GameTile[] board;

	/**
	 * Constructs an empty gameboard of 9 GameTiles and fills it with unowned tiles
	 */
	public GameBoard() {
		board = new GameTile[9];
		for (int i = 0; i < 9; i++)
			board[i] = new GameTile();
	}

	/**
	 * This will allow a player to claim a GameTile on the TicTacToe board
	 * 
	 * @return A boolean true if the player successfully played on a tile, false if
	 *         that tile is already owned or the index is out of bounds
	 * @param player A String indicating which player is to own the tile ("X" or
	 *               "O")
	 * @param tile   An integer representing the tile the player wishes to claim
	 */
	public boolean play(String player, int tile) {
		// Enter code for this method here
		if (tile > 10)
			return false;
		if (!board[tile - 1].owned()) {
			board[tile - 1].setOwner(player);
			return true;
		} else
			return false;
	}

	/**
	 * This will check to see if there are three tiles in a row belonging to a
	 * player
	 * 
	 * @return A boolean true if the player has three tiles in a row, false
	 *         otherwise
	 * @param player A String indicating which player to check for a win ("X" or
	 *               "O")
	 */
	public boolean checkWin(String player, int[] winCheck) {
// AI learns winning combinations only when AI or human wins by the winning combination
		for (int i = 0; i < 3; i++) {
			// vertical check
			if (board[i].owner.equals(player) && board[i + 3].owner.equals(player)
					&& board[i + 6].owner.equals(player)) {
				if (i == 0) {
					winCheck[3] = -1;
				}
				if (i == 1) {
					winCheck[4] = -1;
				}
				if (i == 2) {
					winCheck[5] = -1;
				}
				return true;
			}

			// horizontal check
			else if (board[i * 3].owner.equals(player) && board[(i * 3) + 1].owner.equals(player)
					&& board[(i * 3) + 2].owner.equals(player)) {
				if (i == 0) {
					winCheck[0] = -1;
				}
				if (i == 1) {
					winCheck[1] = -1;
				}
				if (i == 2) {
					winCheck[2] = -1;
				}
				return true;
			}
		}

		// diagonal check(1,5,9)
		if (board[0].owner.equals(player) && board[4].owner.equals(player) && board[8].owner.equals(player)) {
			winCheck[6] = -1;
			return true;
		}

		// diagonal check(3,5,9)
		if (board[2].owner.equals(player) && board[4].owner.equals(player) && board[6].owner.equals(player)) {
			winCheck[7] = -1;
			return true;
		}
		return false;
	}


	public int learningAI(int[][] win, int[][] block, int[] ai, int[] human) {
		// compare ai moves done with winning combinations
		for (int j = 0; j < 5; j++) {
			for (int n = 0; n < win.length; n++) {
				for (int i = 0; i < 3; i++) {
					if (ai[j] == win[n][i]) {
						win[n][i] = 20;
						Arrays.sort(win[n]);
					}
					if (win[n][1] == 20 && !board[win[n][0] - 1].owned()) {
						return win[n][0];
					}
				}
			}
		}
		for (int j = 0; j < 5; j++) {
			for (int n = 0; n < block.length; n++) {
				for (int i = 0; i < 3; i++) {
					if (human[j] == block[n][i]) {
						block[n][i] = 30;
						Arrays.sort(block[n]);
					}
					if (block[n][1] == 30 && !board[block[n][0] - 1].owned()) {
						return block[n][0];
					}
				}
			}
		}
		// makes random move if there is no move AI can make to win or block human
		return (int) (Math.random() * 9) + 1;
	}

	public int AImove(int[] ai, int[] human, int turn) {
//winning combinations
		int win1[] = { 1, 2, 3 };
		int win2[] = { 4, 5, 6 };
		int win3[] = { 7, 8, 9 };
		int win4[] = { 1, 4, 7 };
		int win5[] = { 2, 5, 8 };
		int win6[] = { 3, 6, 9 };
		int win7[] = { 1, 5, 9 };
		int win8[] = { 3, 5, 7 };

		int block1[] = { 1, 2, 3 };
		int block2[] = { 4, 5, 6 };
		int block3[] = { 7, 8, 9 };
		int block4[] = { 1, 4, 7 };
		int block5[] = { 2, 5, 8 };
		int block6[] = { 3, 6, 9 };
		int block7[] = { 1, 5, 9 };
		int block8[] = { 3, 5, 7 };

		int win[][] = { win1, win2, win3, win4, win5, win6, win7, win8 };
		int block[][] = { block1, block2, block3, block4, block5, block6, block7, block8 };

		int corners[] = { 1, 3, 7, 9 };
		int sides[] = { 2, 4, 6, 8 };
		int ran = (int) (Math.random() * 4);

		int middlecheck = 0;

		// compare ai moves done with winning combinations
		for (int j = 0; j < 5; j++) {
			for (int n = 0; n < win.length; n++) {
				for (int i = 0; i < 3; i++) {
					if (ai[j] == win[n][i]) {
						// change to 20 to mark that tile is owned by AI
						win[n][i] = 20;
						Arrays.sort(win[n]);
					}
					// if two of winning combination are owned by AI and the other one is not owned
					// by anyone, make a move on that tile
					if (win[n][1] == 20 && !board[win[n][0] - 1].owned()) {
						return win[n][0];
					}
				}
			}
		}
		// compare human moves done with winning combinations to block
		for (int j = 0; j < 5; j++) {
			for (int n = 0; n < block.length; n++) {
				for (int i = 0; i < 3; i++) {
					if (human[j] == block[n][i]) {
						// change to 30 to mark that tile is owned by human
						block[n][i] = 30;
						Arrays.sort(block[n]);
					}
					// if two of winning combination are owned by human and the other one is not owned
					// by anyone, block the tile
					if (block[n][1] == 30 && !board[block[n][0] - 1].owned()) {
						return block[n][0];
					}
					// by blocking the middle tile, decrease the probability for human player to win
					if (turn == 4) {
						if (human[j] == 2 || human[j] == 4 || human[j] == 6 || human[j] == 8) {
							middlecheck++;
							// System.out.println(middlecheck);
						}
						if (middlecheck == 2 && !board[4].owned()) {
							return 5;
						}
					}
				}
			}
		}
		// make a move at corners
		if (!board[0].owned() || !board[2].owned() || !board[6].owned() || !board[8].owned()) {
			return corners[ran];
			// middle
		} else if (!board[4].owned()) {
			return 5;
		}
		//lastly sides
		return sides[ran];
	}

	/**
	 * This will draw the current gameboard on the screen
	 */
	public void drawBoard() {
		// Enter code for this method here
		System.out.println("-------------");
		System.out.println("| " + board[0].owner + " | " + board[1].owner + " | " + board[2].owner + " |");
		System.out.println("-------------");
		System.out.println("| " + board[3].owner + " | " + board[4].owner + " | " + board[5].owner + " |");
		System.out.println("-------------");
		System.out.println("| " + board[6].owner + " | " + board[7].owner + " | " + board[8].owner + " |");
		System.out.println("-------------");
	}

}// end class

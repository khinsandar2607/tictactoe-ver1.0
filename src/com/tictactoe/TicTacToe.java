package com.tictactoe;

import java.util.Scanner;

public class TicTacToe {

	private static final int SIZE = 3;
	private static final char PLAYER_X = 'X';
	private static final char PLAYER_O = 'O';
	private char[][] board;
	private char currentPlayer;
	Scanner scanner = new Scanner(System.in);

	public TicTacToe() {

		board = new char[SIZE][SIZE];

		initializeBoard();
		currentPlayer = 'X';
	}

	// Initialize the board from digits 0-8
	public void initializeBoard() {
		int boxNumbers = 0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				board[i][j] = (char) ('0' + boxNumbers++);
			}
		}
	}

	public void printBoard() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.printf("%s ", " " + board[i][j]);
				if (j + 1 < SIZE) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (i + i < SIZE) {
				System.out.println("----------");
			}
		}
	}

	/*
	 * Method to check who won the game
	 */
	public boolean checkWinner(char player) {
		// check the horizontal
		for (int i = 0; i < SIZE; i++) {
			if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
				return true;
			}
		}
		// check the vertical
		for (int j = 0; j < SIZE; j++) {
			if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
				return true;
			}
		}
		// check the diagonals
		// left diagonals
		if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
			return true;
		}
		if (board[2][0] == player && board[1][1] == player && board[0][2] == player) {
			return true;
		}

		return false;
	}

	/*
	 * Method to check if game over or not
	 */
	public boolean isGameOver() {
		return checkWinner(PLAYER_X) || checkWinner(PLAYER_O) || isBoardFull();
	}

	/*
	 * check the board is full or not
	 */
	private boolean isBoardFull() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (Character.isDigit(board[i][j])) {
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * to make move to player
	 */
	public int toMakeMove() {
		// making a move to player to enter a box number
		System.out.println("Player " + currentPlayer + "-turn. Choose the box number : ");
		int boxNumber = scanner.nextInt();

		// validate the box number
		if (boxNumber < 0 || boxNumber > 8) {
			System.out.println("Invalid number. try AGAIN!");
			return toMakeMove();
		}

		// check the box number is empty
		int i = boxNumber / SIZE;
		int j = boxNumber % SIZE;
		if (board[i][j] == PLAYER_X || board[i][j] == PLAYER_O) {
			System.out.println("Box is already occoupied. try AGAIN!");
			return toMakeMove();
		}

		// return box number
		return boxNumber;
	}

	/*
	 * Method to play the game
	 */
	public void toPlay() {
		while (!isGameOver()) {

			printBoard();
			System.out.println();

			// ask the current player to make move
			int boxNumber = toMakeMove();

			// player the maker for the current player here
			int i = boxNumber / SIZE;
			int j = boxNumber % SIZE;
			board[i][j] = currentPlayer;

			// switch the player -- ternary operator
			currentPlayer = currentPlayer == PLAYER_X ? PLAYER_O : PLAYER_X;
//			if (currentPlayer == PLAYER_X) {
//				currentPlayer = PLAYER_O;
//			} else {
//				currentPlayer = PLAYER_X;
//			}

		}
		toPrintWinnerResult();
		// show the final board result
		printBoard();
		// switch the box to empty or reset board
		initializeBoard();
		// reset the player to PLAYER_X
		currentPlayer = PLAYER_X;
	}

	// print the winner or final result
	private void toPrintWinnerResult() {
		if (checkWinner(PLAYER_X)) {
			System.out.println("Player_X WON");
			System.out.println("------------------------------------------");
		} else if (checkWinner(PLAYER_O)) {
			System.out.println("Player_O WON");
			System.out.println("------------------------------------------");
		} else {
			System.out.println("Game drawn!");
			System.out.println("------------------------------------------");
		}
	}

}

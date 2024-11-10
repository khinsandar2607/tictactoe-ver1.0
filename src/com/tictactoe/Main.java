package com.tictactoe;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		TicTacToe ticTacToe2 = new TicTacToe();
		Scanner scanner = new Scanner(System.in);
		String answer;
		
		do {
			ticTacToe2.toPlay();

			System.out.println("Do you want to play Again? Yes : Y, No : N");
			answer = scanner.nextLine();

		} while (answer.equalsIgnoreCase("Y"));
		
		scanner.close();

	}

}

package com.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BowlingGame {

	public static void main(final String[] args) throws IOException {
		final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		final BowlingCore bowlingCore = new BowlingCore();
		Boolean gameFinished = false;
		while (!gameFinished) {
			System.out.print("INPUT: ");
			final String line = in.readLine();
			try {
				final int input = Integer.parseInt(line);
				if (input >= 0 && input <= 10) {
					gameFinished = bowlingCore.evaluateInput(input);
					bowlingCore.printResult();
				} else {
					System.out.println("Not in the range, 1-10!");
				}

			} catch (final NumberFormatException exception) {
				System.out.println("NumberFormatException");
			}
		}

	}
}

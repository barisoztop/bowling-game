package com.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BowlingGame {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BowlingCore bowlingCore = new BowlingCore(); 
		while (true) {
			String line = in.readLine();
			try {
				bowlingCore.evaluateInput(Integer.parseInt(line));
			} catch (NumberFormatException exception) {
				System.out.println("NumberFormatException");
			}
		}
	}
}

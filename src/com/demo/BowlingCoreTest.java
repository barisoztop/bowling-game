package com.demo;

import static org.junit.Assert.*;

import org.junit.Test;

public class BowlingCoreTest {

	@Test
	public void testAllStrike() {
		BowlingCore bowlingCore = new BowlingCore(); 
		Boolean gameFinished = false;
		for (int i = 1; i < 13; i++) {
			gameFinished = bowlingCore.evaluateInput(10);
		}
		assertEquals(true, gameFinished);
		
		Frame lastFrame = bowlingCore.frameMap.get(10);
		assertEquals(3, lastFrame.knockDowns.size());
		for ( int knockDown : lastFrame.knockDowns) {
			assertEquals(10, knockDown);
		}
		assertEquals(300, lastFrame.totalPoint);
	}
}

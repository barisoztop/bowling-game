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
		assertEquals(30, lastFrame.knockDowns.get(0) + lastFrame.totalPointFromNextBalls);
		for ( int knockDown : lastFrame.knockDowns) {
			assertEquals(10, knockDown);
		}
		assertEquals(300, lastFrame.totalPoint);
		
		Frame fifthFrame = bowlingCore.frameMap.get(5);
		assertEquals(1, fifthFrame.knockDowns.size());
		for ( int knockDown : fifthFrame.knockDowns) {
			assertEquals(10, knockDown);
		}
		assertEquals(150, fifthFrame.totalPoint);
	}
	
	@Test
	public void testATypicalGame() {
		BowlingCore bowlingCore = new BowlingCore(); 
		Boolean gameFinished = false;
		int[] inputs = new int[]{1,4,4,5,6,4,5,5,10,0,1,7,3,6,4,10,2,8,6};
		for (int j = 0; j < inputs.length; j++) {
			gameFinished = bowlingCore.evaluateInput(inputs[j]);
		}
		assertEquals(true, gameFinished);
		
		Frame lastFrame = bowlingCore.frameMap.get(10);
		assertEquals(133, lastFrame.totalPoint);
		
		Frame fifthFrame = bowlingCore.frameMap.get(5);
		assertEquals(60, fifthFrame.totalPoint);
	}
}

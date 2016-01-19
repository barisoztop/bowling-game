package com.demo;

import java.util.HashMap;
import java.util.Map;

public class BowlingCore {

	private static final int STRIKE = 10;
	int currentFrameNo = 1;
	int currentFrameBall = 1;
	Map<Integer, Frame> frameMap;

	public BowlingCore() {
		frameMap = new HashMap<Integer, Frame>();
	}

	public boolean evaluateInput(int input) {
		// 1
		if (frameMap.get(currentFrameNo) != null) {
			Frame frame = frameMap.get(currentFrameNo);
			frame.knockDowns.add(input);
			if (frame.knockDowns.size() == 2) {
				frame.completed = true;
				for (Integer numberOfKnowDowns : frame.knockDowns) {
					frame.totalPoint += numberOfKnowDowns;
				}
				if (currentFrameNo > 1) {
					frame.totalPoint += frameMap.get(currentFrameNo - 1).totalPoint;
				}
				currentFrameNo++;
			}
		} else {
			Frame frame = new Frame(currentFrameNo);
			// frame.firstBall = input;
			frame.knockDowns.add(input);
			frameMap.put(currentFrameNo, frame);
			
			
			// if (input == STRIKE) {
			// frame.frameCompleted = true;
			// frame.totalPoint = 10;
			// frameMap.put(currentFrameNo, frame);
			// currentFrameNo++;
			// }
		}

		if (currentFrameNo == 5) {
			return true;
		}

		return false;

	}

	public void printResult() {

		for (Frame frame : frameMap.values()) {
			if (frame.completed) {
				for (Integer knowDown : frame.knockDowns) {
					System.out.print(knowDown + ", ");
				}
				
				System.out.print("total point: " + frame.totalPoint);
				System.out.println();
			}
		}

	}

}

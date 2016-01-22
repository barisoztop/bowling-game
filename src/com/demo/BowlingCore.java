package com.demo;

import java.util.HashMap;
import java.util.Map;

public class BowlingCore {

	private static final int STRIKE = 10; // Move to enum
	int currentFrameNo = 1;
	int currentFrameBall = 1;
	Map<Integer, Frame> frameMap;

	public BowlingCore() {
		frameMap = new HashMap<Integer, Frame>();
	}

	public boolean evaluateInput(int input) {
		// 1
		if (frameMap.get(currentFrameNo) != null) {
			Frame currentFrame = frameMap.get(currentFrameNo);
			currentFrame.knockDowns.add(input);
			if (currentFrame.knockDowns.size() == 2) {
				// Check previous frame's completion status 
				
				int previousFrameNo = currentFrameNo - 1;
				if (previousFrameNo > 0 && !frameMap.get(previousFrameNo).completed) {
					Frame previousFrame = frameMap.get(previousFrameNo);
					previousFrame.completed = true;
					previousFrame.totalPoint += (previousFrameNo - 1 > 0 ? frameMap.get(previousFrameNo - 1).totalPoint : 0);
					previousFrame.totalPoint += STRIKE;
					for (Integer numberOfKnowDowns : currentFrame.knockDowns) {
						previousFrame.totalPoint += numberOfKnowDowns;
					}
				}

				currentFrame.completed = true;
				currentFrame.totalPoint += calculateTotalPoint(currentFrameNo);

				currentFrameNo++;
			}
		} else {
			Frame frame = new Frame(currentFrameNo);
			// frame.firstBall = input;
			frame.knockDowns.add(input);
			frameMap.put(currentFrameNo, frame);
			
			 if (input == STRIKE) {
				 // TODO Check previous frame too
				 frameMap.put(currentFrameNo, frame);
				 currentFrameNo++;
			 }
		}

		if (currentFrameNo == 5) {
			return true;
		}

		return false;

	}
	
	public int calculateTotalPoint(int currentFrameNo) {
		if (currentFrameNo < 1) {
			return 0;
		} else if (frameMap.get(currentFrameNo).completed) {
			Frame currentFrame = frameMap.get(currentFrameNo);
			return currentFrame.totalPoint;
		}
		
		Frame currentFrame = frameMap.get(currentFrameNo);
		int pointsFromKnockDowns = 0;
		for (Integer numberOfKnockDowns : currentFrame.knockDowns) {
			pointsFromKnockDowns += numberOfKnockDowns;
		}
		
		return (pointsFromKnockDowns + calculateTotalPoint(currentFrameNo - 1));
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

package com.demo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BowlingCore {

	private static final int STRIKE = 10; // Move to enum
	private static final int LAST_FRAME = 10; // Move to enum
	int currentFrameNo = 1;
	int currentFrameBall = 1;
	Map<Integer, Frame> frameMap;
	List<Frame> incompletedFrames;


	public BowlingCore() {
		frameMap = new HashMap<Integer, Frame>();
		incompletedFrames = new LinkedList<Frame>();
	}

	public boolean evaluateInput(int input) {
		checkIncompletedFrames(input);

		boolean existingFrame = frameMap.get(currentFrameNo) != null;
		if (existingFrame) {
			Frame frame = frameMap.get(currentFrameNo);

			if (frame.knockDowns.get(0) + input == 10) {
				frame.knockDowns.add(input);
				frame.numberOfBallsRequiredToComplete = 1;
				incompletedFrames.add(frame);
				if (currentFrameNo != LAST_FRAME) {
					currentFrameNo++;
				}
			} else {
				if (currentFrameNo != LAST_FRAME) {
					frame.knockDowns.add(input);
					frame.totalPoint += calculateTotalPoint(currentFrameNo);
					frame.completed = true; // TODO move it to the above method
					currentFrameNo++;
				}
			}
		} else {
			Frame frame = new Frame(currentFrameNo);
			frame.knockDowns.add(input);
			frameMap.put(currentFrameNo, frame);
			
			 if (input == STRIKE) {
				 // TODO Check previous frame too
				 frame.numberOfBallsRequiredToComplete = 2;
				 incompletedFrames.add(frame);
				 if (currentFrameNo != LAST_FRAME) {
					 currentFrameNo++;
				 }
			 }
		}

		if (currentFrameNo == LAST_FRAME && incompletedFrames.size() == 0) { // 11
			return true;
		}

		return false;

	}
	
	private void checkIncompletedFrames(int currentKnockDowns) {
		for (Iterator<Frame> iterator = incompletedFrames.iterator(); iterator.hasNext();) {
			Frame frame = (Frame) iterator.next();
			frame.numberOfBallsRequiredToComplete--;
			frame.totalPointFromNextBalls += currentKnockDowns;
			if (frame.numberOfBallsRequiredToComplete == 0) {
				frame.totalPoint += calculateTotalPoint(frame.frameNo) + frame.totalPointFromNextBalls;
				frame.completed = true;
				iterator.remove();
			}
		}
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
			System.out.println("FRAME NO: "  + frame.frameNo + ", TOTAL POINT: " + frame.totalPoint);
			for (int i=0; i < frame.knockDowns.size(); i++) {
				System.out.println("--BALL " + (i + 1) + ": " + frame.knockDowns.get(i));
			}
		}

	}

}

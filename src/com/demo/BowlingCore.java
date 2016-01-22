package com.demo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BowlingCore {

	private static final int STRIKE = 10; // Move to enum
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
			frame.knockDowns.add(input);
			if (frame.knockDowns.size() == 2) {
				// Check previous frame's completion status 
				if (incompletedFrames.size() > 0) {
//					incompletedFrames.add(frame);
				} else {
					frame.completed = true;
					frame.totalPoint += calculateTotalPoint(currentFrameNo);
				}

				currentFrameNo++;
			}
		} else {
			Frame frame = new Frame(currentFrameNo);
			frame.knockDowns.add(input);
			frameMap.put(currentFrameNo, frame);
			
			 if (input == STRIKE) {
				 // TODO Check previous frame too
				 frame.numberOfBallsRequiredToComplete = 2;
				 incompletedFrames.add(frame);
				 currentFrameNo++;
			 }
		}

		if (currentFrameNo == 5) {
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

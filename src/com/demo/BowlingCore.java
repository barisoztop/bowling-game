package com.demo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BowlingCore {

	private static final int STRIKE = 10;
	private static final int LAST_FRAME = 10;
	private int currentFrameNo = 1;
	private final Map<Integer, Frame> frameMap;
	private final List<Frame> incompletedFrames;

	public BowlingCore() {
		frameMap = new HashMap<Integer, Frame>();
		incompletedFrames = new LinkedList<Frame>();
	}

	public boolean evaluateInput(final int input) {
		checkIncompletedFrames(input);

		if (getFrameMap().get(currentFrameNo) != null) {
			final Frame frame = getFrameMap().get(currentFrameNo);

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
					frame.completed = true;
					currentFrameNo++;
				}
			}
		} else {
			final Frame frame = new Frame(currentFrameNo);
			frame.knockDowns.add(input);
			getFrameMap().put(currentFrameNo, frame);

			if (input == STRIKE) {
				frame.numberOfBallsRequiredToComplete = 2;
				incompletedFrames.add(frame);
				if (currentFrameNo != LAST_FRAME) {
					currentFrameNo++;
				}
			}
		}

		if (currentFrameNo == LAST_FRAME && incompletedFrames.size() == 0) {
			return true;
		}

		return false;

	}

	private void checkIncompletedFrames(final int currentKnockDowns) {
		for (final Iterator<Frame> iterator = incompletedFrames.iterator(); iterator.hasNext();) {
			final Frame frame = iterator.next();
			frame.numberOfBallsRequiredToComplete--;
			frame.totalPointFromNextBalls += currentKnockDowns;
			if (frame.numberOfBallsRequiredToComplete == 0) {
				frame.totalPoint += calculateTotalPoint(frame.frameNo) + frame.totalPointFromNextBalls;
				frame.completed = true;
				iterator.remove();
			}
		}
	}

	public int calculateTotalPoint(final int currentFrameNo) {
		if (currentFrameNo < 1) {
			return 0;
		} else if (getFrameMap().get(currentFrameNo).completed) {
			final Frame currentFrame = getFrameMap().get(currentFrameNo);
			return currentFrame.totalPoint;
		}

		final Frame currentFrame = getFrameMap().get(currentFrameNo);
		int pointsFromKnockDowns = 0;
		for (final Integer numberOfKnockDowns : currentFrame.knockDowns) {
			pointsFromKnockDowns += numberOfKnockDowns;
		}

		return (pointsFromKnockDowns + calculateTotalPoint(currentFrameNo - 1));
	}

	public void printResult() {

		for (final Frame frame : getFrameMap().values()) {
			System.out.println("FRAME NO: " + frame.frameNo);

			for (int i = 0; i < frame.knockDowns.size(); i++) {
				System.out.println("--BALL " + (i + 1) + ": " + frame.knockDowns.get(i));
			}
			if (frame.completed) {
				System.out.println("TOTAL POINT: " + frame.totalPoint);
			}
		}

	}

	public Map<Integer, Frame> getFrameMap() {
		return frameMap;
	}

}

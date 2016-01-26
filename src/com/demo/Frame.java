package com.demo;

import java.util.LinkedList;
import java.util.List;

public class Frame {
	final int frameNo;
	int totalPoint;
	final List<Integer> knockDowns;
	boolean completed;
	int numberOfBallsRequiredToComplete;
	int totalPointFromNextBalls;

	public Frame(final int frameNo) {
		this.frameNo = frameNo;
		knockDowns = new LinkedList<Integer>();
	}
}

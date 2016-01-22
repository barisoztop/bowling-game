package com.demo;

import java.util.LinkedList;
import java.util.List;

// Use adapter
public class Frame {
	int frameNo;
	int totalPoint;
	List<Integer> knockDowns;
	boolean completed;
	int numberOfBallsRequiredToComplete;
	int totalPointFromNextBalls;

	public Frame(int frameNo) {
		this.frameNo = frameNo;
		knockDowns = new LinkedList<Integer>();
	}

}

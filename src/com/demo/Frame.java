package com.demo;

import java.util.List;

public class Frame {
    int frameNo;
    int totalPoint;
    List<Integer> knockDowns;
    boolean frameCompleted;
    int firstBall;
    int secondBall;
    
    public Frame(int frameNo) {
	this.frameNo = frameNo;
    }

}

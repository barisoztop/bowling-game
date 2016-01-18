package com.demo;

import java.util.HashMap;
import java.util.Map;

public class BowlingCore {
    
    	int currentFrameNo = 1;
    	int currentFrameBall = 1;
    	Map<Integer, Frame> frameMap;
    	
    	public BowlingCore() {
    	    frameMap = new HashMap<Integer, Frame>();
    	}

	public void evaluateInput(int input) {
		// 1
	    if (frameMap.get(currentFrameNo) != null) {
		// TODO
	    } else {
		Frame frame = new Frame(currentFrameNo);
		
		
		
		frameMap.put(currentFrameNo, frame);

	    }
	    	
		
	}

}

package com.gg.brailledecoder;

public class ReturnBraille {	
	// recognized sign
	private String sign; 
	// the name of the sign for the tts engine
	private String voiceOutput;
	
	// contructor
	public ReturnBraille(String sign, String voiceOutput) {
		this.sign = sign;
		this.voiceOutput = voiceOutput;
	}
	// contructor for alphabetical signs
	public ReturnBraille(String sign) {
		this.sign = sign;
		this.voiceOutput = sign;
	}
	
	// getter
	public String getSign() {
		return sign;
	}
	
	public String voiceOutput() {
		return voiceOutput;
	}
}

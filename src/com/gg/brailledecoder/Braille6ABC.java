package com.gg.brailledecoder;

/**
 * @author Gottfried Gaisbauer
 */

public class Braille6ABC implements Braille {
	// value of the boolean[]
	private int value;
	
	
	/*
	 * 
	 * This class has almost exactly the same function like Braille6.
	 * The difference is, that Braille6ABC only supports letters of the alphabet.
	 * 
	 */
	
	@Override
	public ReturnBraille getRecognizedSign(boolean[] p) {
		return new ReturnBraille(braille(p));
	}
	
	@Override
	public char getCharFromBraille(boolean[] p) {
		return getStringFromBraille(p).charAt(0);
	}
	
	@Override
	public String getStringFromBraille(boolean[] p) {
		return braille(p);
	}
	
	@Override
	public int getValue() {
		return value;
	}
	
	@Override
	public String getPressedButtons(boolean[] p) {
		StringBuilder ret = new StringBuilder("P");
		
		for(int i = 0; i < p.length; i++) 
			if(p[i]) 
				ret.append(Integer.toString((i+1)));
		
		return ret.toString();
	}
	
	private String braille(boolean[] p) {
		value = getValue(p);
		
		switch(value) {
		case 0: return p();
		case 14: return p345();
		case 20: return p24();
		case 21: return p246();
		case 22: return p245();
		case 23: return p2456();
		case 28: return p234();
		case 29: return p2346();
		case 30: return p2345();
		case 32: return p1();
		case 34: return p15();
		case 36: return p14();
		case 38: return p145();
		case 40: return p13();
		case 41: return p136();
		case 42: return p135();
		case 43: return p1356();
		case 44: return p134();
		case 45: return p1346();
		case 46: return p1345();
		case 47: return p13456();
		case 48: return p12();
		case 50: return p125();
		case 51: return p1256();
		case 52: return p124();
		case 54: return p1245();
		case 56: return p123();
		case 57: return p1236();
		case 58: return p1235();
		case 60: return p1234();
		case 62: return p12345();
		default: return "invalid";
		}
	}
	
	private String p() {
		return " ";
	}
	
	private String p345() {
		return "ä";
	}
	
	private String p24() {
		return "i";
	}

	private String p246() {
		return "ö";
	}

	private String p245() {
		return "j";
	}

	private String p2456() {
		return "w";
	}

	private String p234() {
		return "s";
	}

	private String p2346() {
		return "ß";
	}

	private String p2345() {
		return "t";
	}

	private String p1() {
		return "a";
	}

	private String p15() {
		return "e";
	}

	private String p14() {
		return "c";
	}

	private String p145() {
		return "d";
	}
	
	private String p13() {
		return "k";
	}

	private String p136() {
		return "u";
	}

	private String p135() {
		return "o";
	}

	private String p1356() {
		return "z";
	}

	private String p134() {
		return "m";
	}

	private String p1346() {
		return "x";
	}

	private String p1345() {
		return "n";
	}

	private String p13456() {
		return "y";
	}

	private String p12() {
		return "b";
	}

	private String p125() {
		return "h";
	}

	private String p1256() {
		return "ü";
	}

	private String p124() {
		return "f";
	}

	private String p1245() {
		return "g";
	}

	private String p123() {
		return "l";
	}

	private String p1236() {
		return "v";
	}

	private String p1235() {
		return "r";
	}
	
	private String p1234() {
		return "p";
	}
	
	private String p12345() {
		return "q";
	}

	private int getValue(boolean[] p) {
		int ret = 0;
		
		for(int i = 0; i < 6; i++) {
			ret = ret << 1;
			if(p[i]) ret++;
		}
		
		return ret;
	}
}

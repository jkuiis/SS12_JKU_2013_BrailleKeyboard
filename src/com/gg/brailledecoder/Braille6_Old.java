package com.gg.brailledecoder;

/**
 * @author Gottfried Gaisbauer
 */

public class Braille6_Old implements Braille {
	// value of the boolean[]
	private int value;
	
	// counter to check if brack is pending
	private int brackPending=0;
	
	// switch to check if parenthesis is pending
	private boolean parPending=false;
	
	// switch if the next signs are numbers
	// set to false with any other sign
	private boolean nextIsNumber=false;
	
	// switch to set next character as capital 
	private boolean nextIsCapital=false;
	
	// switch to set next characters as capitals
	// set to false with the ' sign
	private boolean nextAreCapitals=false;
	
	
	/*
	 * Numbering of the braille points:
	 *  |----|----|
	 *  | P1 | P4 |
	 *  |----|----|
	 *  | P2 | P5 |
	 *  |----|----|
	 *  | P3 | P6 |
	 *  |----|----|
	 * 
	 * Our Keyboard:
	 *
	 *  left hand:  (von Ringfinger bis Zeigefinger)
	 *  P3 P2 P1 
	 * 
	 *  right hand: (von Zeigefinger bis Ringfinger)
	 *  P4 P5 P6
	 * 
	 * Internal Array:
	 *  p[0] = P1
	 *  p[1] = P2
	 *  p[2] = P3
	 *  p[3] = P4
	 *  p[4] = P5
	 *  p[5] = P6
	 * 
	 */
	
	@Override
	public char getCharFromBraille(boolean[] p) {
		return getStringFromBraille(p).charAt(0);
	}
	
	@Override
	public String getStringFromBraille(boolean[] p) {
		String ret = braille(p);
		
		if(nextIsCapital) {
			char[] b = ret.toCharArray();
			b[0] = Character.toUpperCase(b[0]);
			ret = new String(b);
			nextIsCapital = false;
		} else if (nextAreCapitals) {
			char[] b = ret.toCharArray();
			for(int i = 0; i < b.length; i++)
				b[i] = Character.toUpperCase(b[i]);
			ret = new String(b);
		}
		
		return ret;
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
		case 1: return p6();
		case 2: return p5();
		case 3: return p56();
		case 4: return p4();
		case 5: return p46();
		case 6: return p45();
		case 7: return p456();
		case 8: return p3();
		case 9: return p36();
		case 10: return p35();
		case 11: return p356();
		case 12: return p34();
		case 13: return p346();
		case 14: return p345();
		case 15: return p3456();
		case 16: return p2();
		case 17: return p26();
		case 18: return p25();
		case 19: return p256();
		case 20: return p24();
		case 21: return p246();
		case 22: return p245();
		case 23: return p2456();
		case 24: return p23();
		case 25: return p236();
		case 26: return p235();
		case 27: return p2356();
		case 28: return p234();
		case 29: return p2346();
		case 30: return p2345();
		case 31: return p23456();
		case 32: return p1();
		case 33: return p16();
		case 34: return p15();
		case 35: return p156();
		case 36: return p14();
		case 37: return p146();
		case 38: return p145();
		case 39: return p1456();
		case 40: return p13();
		case 41: return p136();
		case 42: return p135();
		case 43: return p1356();
		case 44: return p134();
		case 45: return p1346();
		case 46: return p1345();
		case 47: return p13456();
		case 48: return p12();
		case 49: return p126();
		case 50: return p125();
		case 51: return p1256();
		case 52: return p124();
		case 53: return p1246();
		case 54: return p1245();
		case 55: return p12456();
		case 56: return p123();
		case 57: return p1236();
		case 58: return p1235();
		case 59: return p12356();
		case 60: return p1234();
		case 61: return p12346();
		case 62: return p12345();
		case 63: return p123456();
		}
		System.out.println("unknown sign");
		return "";
	}
	
	private String p() {
		nextIsNumber = false;
		return " ";
	}

	private String p6() {
		if(nextIsNumber) {
			nextIsNumber = false;
			return "";
		}
		if(nextAreCapitals) {
			nextAreCapitals = false;
			return "";
		}
		return "'";
	}

	private String p5() {
		nextIsNumber = false;
		return "~";
	}

	private String p56() {
		nextIsNumber = false;
		return "<";
	}

	private String p4() {
		if(nextIsNumber) return "0.";
		return "\"";
	}

	private String p46() {
		nextIsNumber = false;
		nextIsCapital = true;
		return "$";
	}

	private String p45() {
		nextIsNumber = false;
		nextAreCapitals = true;
		return ">";
	}

	private String p456() {
		nextIsNumber = false;
		return "_";
	}

	private String p3() {
		nextIsNumber = false;
		return ".";
	}

	private String p36() {
		nextIsNumber = false;
		return "-";
	}

	private String p35() {
		if(nextIsNumber) return "9.";
		return "*";
	}

	private String p356() {
		nextIsNumber = false;
		return "«";
	}

	private String p34() {
		nextIsNumber = false;
		return "äu";
	}

	private String p346() {
		nextIsNumber = false;
		return "ie";
	}

	private String p345() {
		if(nextIsNumber) {
			nextIsNumber = false;
			return "";
		}
		return "ä";
	}

	private String p3456() {
		nextIsNumber = true;
		return "#";
	}

	private String p2() {
		if(nextIsNumber) return "1.";
		return ",";
	}

	private String p26() {
		if(nextIsNumber) return "5.";
		return "?";
	}

	private String p25() {
		if(nextIsNumber) return "3.";
		return ":";
	}

	private String p256() {
		if(nextIsNumber) return "4.";
		return "/";
	}

	private String p24() {
		if(nextIsNumber) return "9";
		return "i";
	}

	private String p246() {
		nextIsNumber = false;
		return "ö";
	}

	private String p245() {
		nextIsNumber = false;
		return "j";
	}

	private String p2456() {
		nextIsNumber = false;
		return "w";
	}

	private String p23() {
		if(nextIsNumber) return "2.";
		return ";";
	}

	private String p236() {
		if(nextIsNumber) return "8.";
		return "»";
	}

	private String p235() {
		if(nextIsNumber) return "6.";
		if(nextIsCapital) return "+";
		return "!";
	}

	private String p2356() {
		if(nextIsNumber) return "7.";
		
		parPending = !parPending;
		if(!parPending) return ")";
		return "(";
	}

	private String p234() {
		nextIsNumber = false;
		return "s";
	}

	private String p2346() {
		nextIsNumber = false;
		return "ß";
	}

	private String p2345() {
		nextIsNumber = false;
		return "t";
	}

	private String p23456() {
		nextIsNumber = false;
		if(brackPending-- != 0) return "]";
		return "st";
	}

	private String p1() {
		if(nextIsNumber) return "1";
		return "a";
	}

	private String p16() {
		nextIsNumber = false;
		return "au";
	}

	private String p15() {
		if(nextIsNumber) return "5";
		return "e";
	}

	private String p156() {
		nextIsNumber = false;
		return "sch";
	}

	private String p14() {
		if(nextIsNumber) return "3";
		return "c";
	}

	private String p146() {
		nextIsNumber = false;
		return "ei";
	}

	private String p145() {
		if(nextIsNumber) return "4";
		return "d";
	}

	private String p1456() {
		nextIsNumber = false;
		return "ch";
	}

	private String p13() {
		nextIsNumber = false;
		return "k";
	}

	private String p136() {
		nextIsNumber = false;
		return "u";
	}

	private String p135() {
		nextIsNumber = false;
		return "o";
	}

	private String p1356() {
		nextIsNumber = false;
		return "z";
	}

	private String p134() {
		nextIsNumber = false;
		return "m";
	}

	private String p1346() {
		nextIsNumber = false;
		return "x";
	}

	private String p1345() {
		nextIsNumber = false;
		return "n";
	}

	private String p13456() {
		nextIsNumber = false;
		return "y";
	}

	private String p12() {
		if(nextIsNumber) return "2";
		return "b";
	}

	private String p126() {
		nextIsNumber = false;
		return "eu";
	}

	private String p125() {
		if(nextIsNumber) return "8";
		return "h";
	}

	private String p1256() {
		nextIsNumber = false;
		return "ü";
	}

	private String p124() {
		if(nextIsNumber) return "6";
		return "f";
	}

	private String p1246() {
		nextIsNumber = false;
		return "`";
	}

	private String p1245() {
		if(nextIsNumber) return "7";
		return "g";
	}

	private String p12456() {
		nextIsNumber = false;
		return "^";
	}

	private String p123() {
		nextIsNumber = false;
		return "l";
	}

	private String p1236() {
		nextIsNumber = false;
		return "v";
	}

	private String p1235() {
		nextIsNumber = false;
		return "r";
	}

	private String p12356() {
		nextIsNumber = false;
		brackPending++;
		return "[";
	}

	private String p1234() {
		nextIsNumber = false;
		return "p";
	}

	private String p12346() {
		nextIsNumber = false;
		return "&";
	}

	private String p12345() {
		nextIsNumber = false;
		return "q";
	}

	private String p123456() {
		nextIsNumber = false;
		return "%";
	}

	private int getValue(boolean[] p) {
		int ret = 0;
		
		for(int i = 0; i < 6; i++) {
			ret = ret << 1;
			if(p[i]) ret++;
		}
		
		return ret;
	}

	@Override
	public ReturnBraille getRecognizedSign(boolean[] p) {
		return null;
	}
}

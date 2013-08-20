package com.gg.brailledecoder;

/**
 * @author Gottfried Gaisbauer
 */

public class Braille6 implements Braille {
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
	public ReturnBraille getRecognizedSign(boolean[] p) {
		return braille(p);
	}
	
	@Override
	public char getCharFromBraille(boolean[] p) {
		return getStringFromBraille(p).charAt(0);
	}
	
	@Override
	public String getStringFromBraille(boolean[] p) {
		String ret = braille(p).getSign();
		
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
	
	private ReturnBraille braille(boolean[] p) {
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
		return new ReturnBraille("invalid", "Invalid Character");
	}
	
	private ReturnBraille p() {
		nextIsNumber = false;
		return new ReturnBraille(" ", "Leerzeichen");
	}

	private ReturnBraille p6() {
		if(nextIsNumber) {
			nextIsNumber = false;
			return new ReturnBraille("", "");
		}
		if(nextAreCapitals) {
			nextAreCapitals = false;
			return new ReturnBraille("", "");
		}
		return new ReturnBraille("'", "apostrophe");
	}

	private ReturnBraille p5() {
		nextIsNumber = false;
		return new ReturnBraille("~", "tilde");
	}

	private ReturnBraille p56() {
		nextIsNumber = false;
		return new ReturnBraille("<", "less than");
	}

	private ReturnBraille p4() {
		if(nextIsNumber) return new ReturnBraille("0.", "zeroth");
		return new ReturnBraille("\"", "backslash");
	}

	private ReturnBraille p46() {
		nextIsNumber = false;
		nextIsCapital = true;
		return new ReturnBraille("$", "dollar");
	}

	private ReturnBraille p45() {
		nextIsNumber = false;
		nextAreCapitals = true;
		return new ReturnBraille(">", "greater than");
	}

	private ReturnBraille p456() {
		nextIsNumber = false;
		return new ReturnBraille("_", "underscore");
	}

	private ReturnBraille p3() {
		nextIsNumber = false;
		return new ReturnBraille(".", "dot");
	}

	private ReturnBraille p36() {
		nextIsNumber = false;
		return new ReturnBraille("-", "hyphen");
	}

	private ReturnBraille p35() {
		if(nextIsNumber) return new ReturnBraille("9.", "ninth");
		return new ReturnBraille("*", "asterisk");
	}

	private ReturnBraille p356() {
		nextIsNumber = false;
		return new ReturnBraille("«", "double less than");
	}

	private ReturnBraille p34() {
		nextIsNumber = false;
		return new ReturnBraille("äu", "Umlaut a u");
	}

	private ReturnBraille p346() {
		nextIsNumber = false;
		return new ReturnBraille("ie", "long i");
	}

	private ReturnBraille p345() {
		if(nextIsNumber) {
			nextIsNumber = false;
			return new ReturnBraille("", "");
		}
		return new ReturnBraille("ä", "Umlaut a");
	}

	private ReturnBraille p3456() {
		nextIsNumber = true;
		return new ReturnBraille("#", "hash sign");
	}

	private ReturnBraille p2() {
		if(nextIsNumber) return new ReturnBraille("1.", "first");
		return new ReturnBraille(",", "comma");
	}

	private ReturnBraille p26() {
		if(nextIsNumber) return new ReturnBraille("5.", "fifth");
		return new ReturnBraille("?", "question mark");
	}

	private ReturnBraille p25() {
		if(nextIsNumber) return new ReturnBraille("3.", "third");
		return new ReturnBraille(":", "colon");
	}

	private ReturnBraille p256() {
		if(nextIsNumber) return new ReturnBraille("4.", "forth");
		return new ReturnBraille("/", "Schrägstrich");
	}

	private ReturnBraille p24() {
		if(nextIsNumber) return new ReturnBraille("9", "nine");
		return new ReturnBraille("i");
	}

	private ReturnBraille p246() {
		nextIsNumber = false;
		return new ReturnBraille("ö", "Umlaut o");
	}

	private ReturnBraille p245() {
		nextIsNumber = false;
		return new ReturnBraille("j");
	}

	private ReturnBraille p2456() {
		nextIsNumber = false;
		return new ReturnBraille("w");
	}

	private ReturnBraille p23() {
		if(nextIsNumber) return new ReturnBraille("2.", "secondly");
		return new ReturnBraille(";", "semi colon");
	}

	private ReturnBraille p236() {
		if(nextIsNumber) return new ReturnBraille("8.", "eigthly");
		return new ReturnBraille("»", "double greater than");
	}

	private ReturnBraille p235() {
		if(nextIsNumber) return new ReturnBraille("6.", "sixth");
		if(nextIsCapital) return new ReturnBraille("+", "plus");
		return new ReturnBraille("!", "exclamation mark");
	}

	private ReturnBraille p2356() {
		if(nextIsNumber) return new ReturnBraille("7.", "seventh");
		
		parPending = !parPending;
		if(!parPending) return new ReturnBraille(")", "right paranthesis");
		return new ReturnBraille("(", "left paranthesis");
	}

	private ReturnBraille p234() {
		nextIsNumber = false;
		return new ReturnBraille("s");
	}

	private ReturnBraille p2346() {
		nextIsNumber = false;
		return new ReturnBraille("ß", "Scharfes S");
	}

	private ReturnBraille p2345() {
		nextIsNumber = false;
		return new ReturnBraille("t");
	}

	private ReturnBraille p23456() {
		nextIsNumber = false;
		if(brackPending-- != 0) return new ReturnBraille("]", "right square bracket");
		return new ReturnBraille("st");
	}

	private ReturnBraille p1() {
		if(nextIsNumber) return new ReturnBraille("1", "one");
		return new ReturnBraille("a");
	}

	private ReturnBraille p16() {
		nextIsNumber = false;
		return new ReturnBraille("au");
	}

	private ReturnBraille p15() {
		if(nextIsNumber) return new ReturnBraille("5", "five");
		return new ReturnBraille("e");
	}

	private ReturnBraille p156() {
		nextIsNumber = false;
		return new ReturnBraille("sch");
	}

	private ReturnBraille p14() {
		if(nextIsNumber) return new ReturnBraille("3", "three");
		return new ReturnBraille("c");
	}

	private ReturnBraille p146() {
		nextIsNumber = false;
		return new ReturnBraille("ei");
	}

	private ReturnBraille p145() {
		if(nextIsNumber) return new ReturnBraille("4", "four");
		return new ReturnBraille("d");
	}

	private ReturnBraille p1456() {
		nextIsNumber = false;
		return new ReturnBraille("ch");
	}

	private ReturnBraille p13() {
		nextIsNumber = false;
		return new ReturnBraille("k");
	}

	private ReturnBraille p136() {
		nextIsNumber = false;
		return new ReturnBraille("u");
	}

	private ReturnBraille p135() {
		nextIsNumber = false;
		return new ReturnBraille("o");
	}

	private ReturnBraille p1356() {
		nextIsNumber = false;
		return new ReturnBraille("z");
	}

	private ReturnBraille p134() {
		nextIsNumber = false;
		return new ReturnBraille("m");
	}

	private ReturnBraille p1346() {
		nextIsNumber = false;
		return new ReturnBraille("x");
	}

	private ReturnBraille p1345() {
		nextIsNumber = false;
		return new ReturnBraille("n");
	}

	private ReturnBraille p13456() {
		nextIsNumber = false;
		return new ReturnBraille("y");
	}

	private ReturnBraille p12() {
		if(nextIsNumber) return new ReturnBraille("2", "two");
		return new ReturnBraille("b");
	}

	private ReturnBraille p126() {
		nextIsNumber = false;
		return new ReturnBraille("eu");
	}

	private ReturnBraille p125() {
		if(nextIsNumber) return new ReturnBraille("8", "eight");
		return new ReturnBraille("h");
	}

	private ReturnBraille p1256() {
		nextIsNumber = false;
		return new ReturnBraille("ü");
	}

	private ReturnBraille p124() {
		if(nextIsNumber) return new ReturnBraille("6", "six");
		return new ReturnBraille("f");
	}

	private ReturnBraille p1246() {
		nextIsNumber = false;
		return new ReturnBraille("`", "grave");
	}

	private ReturnBraille p1245() {
		if(nextIsNumber) return new ReturnBraille("7", "seven");
		return new ReturnBraille("g");
	}

	private ReturnBraille p12456() {
		nextIsNumber = false;
		return new ReturnBraille("^", "caret");
	}

	private ReturnBraille p123() {
		nextIsNumber = false;
		return new ReturnBraille("l");
	}

	private ReturnBraille p1236() {
		nextIsNumber = false;
		return new ReturnBraille("v");
	}

	private ReturnBraille p1235() {
		nextIsNumber = false;
		return new ReturnBraille("r");
	}

	private ReturnBraille p12356() {
		nextIsNumber = false;
		brackPending++;
		return new ReturnBraille("[", "left square bracket");
	}

	private ReturnBraille p1234() {
		nextIsNumber = false;
		return new ReturnBraille("p");
	}

	private ReturnBraille p12346() {
		nextIsNumber = false;
		return new ReturnBraille("&", "ampersand");
	}

	private ReturnBraille p12345() {
		nextIsNumber = false;
		return new ReturnBraille("q");
	}

	private ReturnBraille p123456() {
		nextIsNumber = false;
		return new ReturnBraille("%", "percentage sign");
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

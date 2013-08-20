package com.gg.brailledecoder;

/**
 * @author Gottfried Gaisbauer
 */

import java.util.BitSet;

public class BrailleToRoman {	
	private Braille b;
	private StringBuilder sb;
	private boolean[] buttons;
	private BrailleType type;
	
	/*
	 * In Fact BitSet is kind of a overkill.
	 * A simpler boolean array also is fine
	 */
	
	public BrailleToRoman(BrailleType type) {
		this.type = type;
		sb = new StringBuilder();
		
		switch(type) {
		case Braille6: 
			b = new Braille6();
			buttons = new boolean[6];
			break;
		case Braille6ABC:
			b = new Braille6ABC();
			buttons = new boolean[6];
			break;
		case Braille8:
			b = new Braille8();
			buttons = new boolean[8];
			break;
		}
	}
	
	public ReturnBraille getRecognizedSign(BitSet bs) {
		convertBitSet(bs);
		
		switch(type) {
		case Braille6: 		return getRecognizedSign6();
		case Braille6ABC: 	return getRecognizedSign6();
		case Braille8:		return getRecognizedSign8();
		default: 			return null; // cant happen
		}
	}
	
	private ReturnBraille getRecognizedSign6() {
		// get char from braille
		ReturnBraille rb = b.getRecognizedSign(buttons);
							
		// append to stored string
		sb.append(rb.getSign());
				
		return rb;
	}
	
	private ReturnBraille getRecognizedSign8() {
		// get char from braille
		ReturnBraille rb = b.getRecognizedSign(buttons);
							
		// handle special chars like '\t',...
		Braille8SpecialChars b8sc = new Braille8SpecialChars(this);
						
		// handle them -> BrailleToRoman is storing the text
		b8sc.handleSpecialChars(rb.getSign().charAt(0));
							
		// set return string
		return rb;
	}

	@Deprecated
	public String getRomanFromBraille(BitSet bs) {
		convertBitSet(bs);
		
		switch(type) {
		case Braille6:		return getRomanFromBraille6();
		case Braille6ABC:	return getRomanFromBraille6();
		case Braille8:		return getRomanFromBraille8();
		default:			return "cant happen";
		}
		
	}
	
	@Deprecated
	private String getRomanFromBraille6() {
		// get char from braille
		String s = b.getStringFromBraille(buttons);
					
		// append to stored string
		sb.append(s);
		
		return s;
	}
	
	@Deprecated
	private String getRomanFromBraille8() {
		// get char from braille
		char c = b.getCharFromBraille(buttons);
					
		// handle special chars like '\t',...
		Braille8SpecialChars b8sc = new Braille8SpecialChars(this);
					
		// handle them -> BrailleToRoman is storing the text
		b8sc.handleSpecialChars(c);
					
		// set return string
		return Character.toString(c);
	}
	
	@Deprecated
	public char getCharFromBraille(BitSet bs) {
		convertBitSet(bs);
		char c = b.getCharFromBraille(buttons);
		
		if(type == BrailleType.Braille8) {
			Braille8SpecialChars b8sc = new Braille8SpecialChars(this);
			b8sc.handleSpecialChars(c);
		} else sb.append(c);
		
		return c;
	}
	
	public void deleteLastChar() {
		sb.deleteCharAt(sb.length()-1);
	}
	
	public void resetString() {
		sb = new StringBuilder();
	}
	
	public void append(char c) {
		sb.append(c);
	}
	
	public void append(String s) {
		sb.append(s);
	}
	
	public String getText() {
		return sb.toString();
	}
	
	public int getValue() {
		return b.getValue();
	}
	
	private void convertBitSet(BitSet bs) {
		if(type == BrailleType.Braille8) {
			buttons[0] = bs.get(3);
			buttons[1] = bs.get(2);
			buttons[2] = bs.get(1);
			buttons[3] = bs.get(4);  
			
			buttons[4] = bs.get(5); 
			buttons[5] = bs.get(6);
			buttons[6] = bs.get(0);
			buttons[7] = bs.get(7); 
		} else {
			buttons[0] = bs.get(2); 
			buttons[1] = bs.get(1); 
			buttons[2] = bs.get(0); 
			
			buttons[3] = bs.get(3); 
			buttons[4] = bs.get(4); 
			buttons[5] = bs.get(5); 
		}
	}

	public String getPressedButtons() {
		return b.getPressedButtons(buttons);
	}
	
	// returns possibilities with given number of assigned pins
	public char[] getPossibilities(int numberOfAssignedPins) {
		if(type == BrailleType.Braille8) return null;
		
		switch(numberOfAssignedPins) {
		case 1: return oneAssigned();
		case 2: return twoAssigned();
		case 3: return threeAssigned();
		case 4: return fourAssigned();
		case 5: return fiveAssigned();
		default:  return new char[0]; 
		}
	}
	
	// returns possibilites with given numbers of assigned pins left and assigned pins right
	public char[] getPossibilities(int leftAssignedPins, int rightAssignedPins) {
		switch(leftAssignedPins) {
		case 1: return oneAssignedLeft(rightAssignedPins);
		case 2: return twoAssignedLeft(rightAssignedPins);
		case 3: return threeAssignedLeft(rightAssignedPins);
		default:  return new char[0];
		}
	}
	
	public BitSet getAssignedPins(char ch) {
		if(type == BrailleType.Braille8) return null;
		switch(ch) {
		case 'a': return a(new BitSet(6));
		case 'b': return b(new BitSet(6));
		case 'c': return c(new BitSet(6));
		case 'd': return d(new BitSet(6));
		case 'e': return e(new BitSet(6));
		case 'f': return f(new BitSet(6));
		case 'g': return g(new BitSet(6));
		case 'h': return h(new BitSet(6));
		case 'i': return i(new BitSet(6));
		case 'j': return j(new BitSet(6));
		case 'k': return k(new BitSet(6));
		case 'l': return l(new BitSet(6));
		case 'm': return m(new BitSet(6));
		case 'n': return n(new BitSet(6));
		case 'o': return o(new BitSet(6));
		case 'p': return p(new BitSet(6));
		case 'q': return q(new BitSet(6));
		case 'r': return r(new BitSet(6));
		case 's': return s(new BitSet(6));
		case 't': return t(new BitSet(6));
		case 'u': return u(new BitSet(6));
		case 'v': return v(new BitSet(6));
		case 'w': return w(new BitSet(6));
		case 'x': return x(new BitSet(6));
		case 'y': return y(new BitSet(6));
		case 'z': return z(new BitSet(6));
		case 'ä': return ä(new BitSet(6));
		case 'ö': return ö(new BitSet(6));
		case 'ü': return ü(new BitSet(6));
		case 'ß': return ß(new BitSet(6));
		default:  return null;
		}
	}

	private BitSet a(BitSet bs) {
		bs.set(2);
		return bs;
	}

	private BitSet b(BitSet bs) {
		bs.set(1);
		bs.set(2);
		return bs;
	}

	private BitSet c(BitSet bs) {
		bs.set(2);
		bs.set(3);
		return bs;
	}

	private BitSet d(BitSet bs) {
		bs.set(2);
		bs.set(3);
		bs.set(4);
		return bs;
	}

	private BitSet e(BitSet bs) {
		bs.set(2);
		bs.set(4);
		return bs;
	}

	private BitSet f(BitSet bs) {
		bs.set(1);
		bs.set(2);
		bs.set(3);
		return bs;
	}

	private BitSet g(BitSet bs) {
		bs.set(1);
		bs.set(2);
		bs.set(3);
		bs.set(4);
		return bs;
	}

	private BitSet h(BitSet bs) {
		bs.set(1);
		bs.set(2);
		bs.set(4);
		return bs;
	}

	private BitSet i(BitSet bs) {
		bs.set(1);
		bs.set(3);
		return bs;
	}

	private BitSet j(BitSet bs) {
		bs.set(1);
		bs.set(3);
		bs.set(4);
		return bs;
	}

	private BitSet k(BitSet bs) {
		bs.set(0);
		bs.set(2);
		return bs;
	}

	private BitSet l(BitSet bs) {
		bs.set(0);
		bs.set(1);
		bs.set(2);
		return bs;
	}

	private BitSet m(BitSet bs) {
		bs.set(0);
		bs.set(2);
		bs.set(3);
		return bs;
	}

	private BitSet n(BitSet bs) {
		bs.set(0);
		bs.set(2);
		bs.set(3);
		bs.set(4);
		return bs;
	}

	private BitSet o(BitSet bs) {
		bs.set(0);
		bs.set(2);
		bs.set(4);
		return bs;
	}

	private BitSet p(BitSet bs) {
		bs.set(0);
		bs.set(1);
		bs.set(2);
		bs.set(3);
		return bs;
	}

	private BitSet q(BitSet bs) {
		bs.set(0);
		bs.set(1);
		bs.set(2);
		bs.set(3);
		bs.set(4);
		return bs;
	}

	private BitSet r(BitSet bs) {
		bs.set(0);
		bs.set(1);
		bs.set(2);
		bs.set(4);
		return bs;
	}

	private BitSet s(BitSet bs) {
		bs.set(0);
		bs.set(1);
		bs.set(3);
		return bs;
	}

	private BitSet t(BitSet bs) {
		bs.set(0);
		bs.set(1);
		bs.set(3);
		bs.set(4);
		return bs;
	}

	private BitSet u(BitSet bs) {
		bs.set(0);
		bs.set(2);
		bs.set(5);
		return bs;
	}

	private BitSet v(BitSet bs) {
		bs.set(0);
		bs.set(1);
		bs.set(2);
		bs.set(5);
		return bs;
	}

	private BitSet w(BitSet bs) {
		bs.set(1);
		bs.set(3);
		bs.set(4);
		bs.set(5);
		return bs;
	}

	private BitSet x(BitSet bs) {
		bs.set(0);
		bs.set(2);
		bs.set(3);
		bs.set(5);
		return bs;
	}

	private BitSet y(BitSet bs) {
		bs.set(0);
		bs.set(2);
		bs.set(3);
		bs.set(4);
		bs.set(5);
		return bs;
	}

	private BitSet z(BitSet bs) {
		bs.set(0);
		bs.set(2);
		bs.set(4);
		bs.set(5);
		return bs;
	}

	private BitSet ä(BitSet bs) {
		bs.set(0);
		bs.set(3);
		bs.set(4);
		return bs;
	}

	private BitSet ö(BitSet bs) {
		bs.set(1);
		bs.set(3);
		bs.set(5);
		return bs;
	}

	private BitSet ü(BitSet bs) {
		bs.set(1);
		bs.set(2);
		bs.set(4);
		bs.set(5);
		return bs;
	}

	private BitSet ß(BitSet bs) {
		bs.set(0);
		bs.set(1);
		bs.set(3);
		bs.set(5);
		return bs;
	}

	private char[] oneAssignedLeft(int rightAssignedPins) {
		switch(rightAssignedPins) {
		case 0: return oneLeftNoRight();
		case 1: return oneLeftOneRight();
		case 2: return oneLeftTwoRight();
		case 3: return oneLeftThreeRight();
		default:  return new char[0];
		}
	}
	
	private char[] twoAssignedLeft(int rightAssignedPins) {
		switch(rightAssignedPins) {
		case 0: return twoLeftNoRight();
		case 1: return twoLeftOneRight();
		case 2: return twoLeftTwoRight();
		default:  return new char[0];
		}
	}
	
	private char[] threeAssignedLeft(int rightAssignedPins) {
		switch(rightAssignedPins) {
		case 0: return threeLeftNoRight();
		case 1: return threeLeftOneRight();
		case 2: return threeLeftTwoRight();
		default:  return new char[0];
		}
	}

	private char[] oneLeftNoRight() {
		char[] ret = new char[1];
		ret[0] = 'a';
		return ret;
	}

	private char[] oneLeftOneRight() {
		char[] ret = new char[3];
		ret[0] = 'c';
		ret[1] = 'e';
		ret[2] = 'i';
		return ret;
	}

	private char[] oneLeftTwoRight() {
		char[] ret = new char[4];
		ret[0] = 'd';
		ret[1] = 'j';
		ret[2] = 'ä';
		ret[3] = 'ö';
		return ret;
	}

	private char[] oneLeftThreeRight() {
		char[] ret = new char[1];
		ret[0] = 'w';
		return ret;
	}	

	private char[] twoLeftNoRight() {
		char[] ret = new char[2];
		ret[0] = 'b';
		ret[1] = 'k';
		return ret;
	}

	private char[] twoLeftOneRight() {
		char[] ret = new char[6];
		ret[0] = 'f';
		ret[1] = 'h';
		ret[2] = 'm';
		ret[3] = 'o';
		ret[4] = 's';
		ret[5] = 'u';
		return ret;
	}

	private char[] twoLeftTwoRight() {
		char[] ret = new char[8];
		ret[0] = 'g';
		ret[1] = 'n';
		ret[2] = 't';
		ret[3] = 'x';
		ret[4] = 'z';
		ret[5] = 'ü';
		ret[6] = 'ß';
		return ret;
	}

	private char[] threeLeftNoRight() {
		char[] ret = new char[1];
		ret[0] = 'l';
		return ret;
	}

	private char[] threeLeftOneRight() {
		char[] ret = new char[3];
		ret[0] = 'p';
		ret[1] = 'r';
		ret[2] = 'v';
		return ret;
	}

	private char[] threeLeftTwoRight() {
		char[] ret = new char[1];
		ret[0] = 'q';
		return ret;
	}

	private char[] oneAssigned() {
		char[] ret = new char[1];
		ret[0] = 'a';
		return ret;
	}
	
	private char[] twoAssigned() {
		char[] ret = new char[5];
		ret[0] = 'b';
		ret[1] = 'c';
		ret[2] = 'e';
		ret[3] = 'i';
		ret[4] = 'k';
		return ret;
	}
	
	private char[] threeAssigned() {
		char[] ret = new char[11];
		ret[0] = 'd';
		ret[1] = 'f';
		ret[2] = 'h';
		ret[3] = 'j';
		ret[4] = 'l';
		ret[5] = 'm';
		ret[6] = 'o';
		ret[7] = 's';
		ret[8] = 'u';
		ret[9] = 'ä';
		ret[10] = 'ö';
		return ret;
	}
	
	private char[] fourAssigned() {
		char[] ret = new char[11];
		ret[0] = 'g';
		ret[1] = 'n';
		ret[2] = 'p';
		ret[3] = 'r';
		ret[4] = 't';
		ret[5] = 'v';
		ret[6] = 'w';
		ret[7] = 'x';
		ret[8] = 'z';
		ret[9] = 'ü';
		ret[10] = 'ß';
		return ret;
	}
	
	private char[] fiveAssigned() {
		char[] ret = new char[2];
		ret[0] = 'q';
		ret[1] = 'y';
		return ret;
	}
	
	/*
	 * This is a short Braille8 Description (more in Braille8.java)
	 * 
	 * BitSet is expected with a pin allocation like:
	 * bs.get(0) = P7
	 * bs.get(1) = P3
	 * bs.get(2) = P2
	 * bs.get(3) = P1
	 * bs.get(4) = P4
	 * bs.get(5) = P5
	 * bs.get(6) = P6
	 * bs.get(7) = P8
	 * 
	 * like http://www.fakoo.de/braille/computerbraille-writer.html
	 * 
	 * for easier internal handling, the array for internal usage is ordered ascending
	 *  p[0] = P1 = bs.get(3);
	 *  p[1] = P2 = bs.get(2);
	 *  p[2] = P3 = bs.get(1);
	 *  p[3] = P4 = bs.get(4);
	 *  p[4] = P5 = bs.get(5);
	 *  p[5] = P6 = bs.get(6);
	 *  p[6] = P7 = bs.get(0);
	 *  p[7] = P8 = bs.get(7);
	 * 
	 */
	
	/*
	 * This is a short Braille6 Description (more in Braille6.java)
	 * 
	 * BitSet is expected with a pin allocation like:
	 *  bs.get(0) = P3
	 *  bs.get(1) = P2
	 *  bs.get(2) = P1
	 *  bs.get(3) = P4
	 *  bs.get(4) = P5
	 *  bs.get(5) = P6
	 * 
	 * like http://www.fakoo.de/braille/braille-writer.html
	 * 
	 * for easier internal handling, the array for internal usage is ordered ascending
	 *  p[0] = P1 = bs.get(2);
	 *  p[1] = P2 = bs.get(1);
	 *  p[2] = P3 = bs.get(0);
	 *  p[3] = P4 = bs.get(3);
	 *  p[4] = P5 = bs.get(4);
	 *  p[5] = P6 = bs.get(5);
	 * 
	 */
}

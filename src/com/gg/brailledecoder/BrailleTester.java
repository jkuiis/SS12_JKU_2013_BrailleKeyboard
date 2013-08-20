package com.gg.brailledecoder;

/**
 * @author Gottfried Gaisbauer
 */

import java.util.BitSet;
import java.util.Random;

public class BrailleTester {
	static int randomLoopCnt = 100;
	static BrailleToRoman btr;
	
	public static void main(String[] args) {		
		// test braille6
		testBraille6Random();
		testBraille6Rising();
		
		// test braille8
		testBraille8Random();
		testBraille8Rising();
		
		// test possibilites
		testPossibilities();
		testLeftRightPossibilities();
		
		// test character to pinassignment to character
		testConversion();
	}
	
	private static void testConversion() {
		btr = new BrailleToRoman(BrailleType.Braille6ABC);
		
		char start = 'a';
		char ch;
		BitSet pins;
		
		do {
			pins = btr.getAssignedPins(start);
			ch = btr.getCharFromBraille(pins);
			
			if(ch != start) 
				System.out.println("oh my dear. didn't you work properly? " + start + " != " + ch);
			
			start++;
		} while(start != 'z');
		
		start = 'ä';
		pins = btr.getAssignedPins(start);
		ch = btr.getCharFromBraille(pins);
		if(ch != start) 
			System.out.println("oh my dear. didn't you work properly? " + start + " != " + ch);
		
		start = 'ö';
		pins = btr.getAssignedPins(start);
		ch = btr.getCharFromBraille(pins);
		if(ch != start) 
			System.out.println("oh my dear. didn't you work properly? " + start + " != " + ch);
		
		start = 'ü';
		pins = btr.getAssignedPins(start);
		ch = btr.getCharFromBraille(pins);
		if(ch != start) 
			System.out.println("oh my dear. didn't you work properly? " + start + " != " + ch);
		
		start = 'ß';
		pins = btr.getAssignedPins(start);
		ch = btr.getCharFromBraille(pins);
		if(ch != start) 
			System.out.println("oh my dear. didn't you work properly? " + start + " != " + ch);
	}

	private static void testPossibilities() {
		btr = new BrailleToRoman(BrailleType.Braille6ABC);
		
		for(int i = 0; i <= 6; i++)  // from 0 to included 6
			System.out.println("[" + i + "]: " + String.valueOf(btr.getPossibilities(i)));
	}
	
	private static void testLeftRightPossibilities() {
		btr = new BrailleToRoman(BrailleType.Braille6ABC);
		
		for(int i = 0; i <= 3; i++) // both loops from 0 to included 3
			for(int j = 0; j <= 3; j++)
				System.out.println("[" + i + "/" + j + "]: " + String.valueOf(btr.getPossibilities(i, j)));
	}

	private static void testBraille8Rising() {
		BitSet bs;
		String s;
		
		// create brailletoroman. 
		btr = new BrailleToRoman(BrailleType.Braille8);
		
		System.out.println("\nBraille8 rising tester");
		System.out.println("value == buttons == Sign");
				
		for(int i = 0; i < 256; i++) {
			// generate rising bitset
			bs = getBitSet(i, 8);
			
			// convert to braille to text
			s = btr.getRomanFromBraille(bs);
			
			// output some stuff
			System.out.println(i + ": " + btr.getValue() + " == " + btr.getPressedButtons() + " == " + s + ";");
		}
		// output whole text
		System.out.println(btr.getText());
	}

	private static void testBraille8Random() {
		Random r = new Random(System.currentTimeMillis());
		BitSet bs;
		String s;
		
		// create brailletoroman.
		btr = new BrailleToRoman(BrailleType.Braille8);
		
		System.out.println("\nBraille8 random tester");
		System.out.println("value == buttons == Sign");
		
		for(int i = 0; i < randomLoopCnt; i++) {
			// create new random bitset
			bs = new BitSet(8);
			for(int j = 0; j < 8; j++) 
				if(r.nextInt(2) == 1) 
					bs.set(j);
			
			// convert braille to text
			s = btr.getRomanFromBraille(bs);
			
			// output if not too much
			if(randomLoopCnt <= 100) 
				System.out.println(btr.getValue() + " == " + s);
		}
		
		// output if not too much (it gets very very slow)
		if(randomLoopCnt <= 10000) 
			System.out.println(btr.getText());
	}

	private static void testBraille6Rising() {
		BitSet bs;
		String s;
		
		// create brailletoroman.
		btr = new BrailleToRoman(BrailleType.Braille6);
				
		System.out.println("\nBraille6 rising tester");
		System.out.println("value == buttons == Sign");
		
		for(int i = 0; i < 64; i++) {
			// generate rising bitset
			bs = getBitSet(i, 6);
			
			// convert braille to text
			s = btr.getRomanFromBraille(bs);
			
			// output some stuff
			System.out.println(btr.getValue() + " == " + btr.getPressedButtons() + " == " + s);
		}
		
		System.out.println(btr.getText());
	}
	
	// generate bitset with len and value 
	private static BitSet getBitSet(int num, int len) {
		BitSet bs = new BitSet(len);
		int m = (int) Math.pow(2, len-1);
		
		for(int i = 0; i < len; i++) {
			if(num >= m) {
				bs.set(i);
				num -= m;
			}
			m/=2;
		}
		
		return bs;
	}

	private static void testBraille6Random() {
		Random r = new Random(System.currentTimeMillis());
		BitSet bs;
		String s;
		
		// create brailletoroman. 
		btr = new BrailleToRoman(BrailleType.Braille6);
				
		if(randomLoopCnt <= 100) {
			System.out.println("\nBraille6 random tester");
			System.out.println("value == buttons == Sign");
		}
		
		for(int i = 0; i < randomLoopCnt; i++) {
			// generate new random bitset
			bs = new BitSet();
			for(int j = 0; j < 6; j++) 
				if(r.nextInt(2) == 1) 
					bs.set(j);
			
			// convert braille to text
			s = btr.getRomanFromBraille(bs);
			
			// output if not too much
			if(randomLoopCnt <= 100) 
				System.out.println(btr.getValue() + " == " + btr.getPressedButtons() + " == " + s);
		}
		
		// output if not too much
		if(randomLoopCnt <= 10000) 
			System.out.println(btr.getText());
	}
}

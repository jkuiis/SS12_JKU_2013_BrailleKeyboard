package com.gg.brailledecoder;

/**
 * @author Gottfried Gaisbauer
 */

public interface Braille {
	/**
	 * @param p
	 * Braille pin allocation where
	 * p[0] = P1
	 * p[1] = P2
	 * p[2] = P3
	 * p[3] = P4
	 * p[4] = P5
	 * p[5] = P6
	 * p[6] = P7 // if braille8
	 * p[7] = P8 // if braille8
	 * 
	 * @return
	 * The class ReturnBraille contains the recognized sign and the name of the sign for the tts engine
	 */
	public ReturnBraille getRecognizedSign(boolean[] p);
	
	/**
	 * @param p
	 * Braille pin allocation where
	 * p[0] = P1
	 * p[1] = P2
	 * p[2] = P3
	 * p[3] = P4
	 * p[4] = P5
	 * p[5] = P6
	 * p[6] = P7 // if braille8
	 * p[7] = P8 // if braille8
	 * 
	 * @return
	 * The corresponding String to the Braille sign
	 */
	@Deprecated
	public String getStringFromBraille(boolean[] p);
	
	/**
	 * @param p
	 * Braille pin allocation where
	 * p[0] = P1
	 * p[1] = P2
	 * p[2] = P3
	 * p[3] = P4
	 * p[4] = P5
	 * p[5] = P6
	 * p[6] = P7 // if braille8
	 * p[7] = P8 // if braille8
	 * 
	 * @return
	 * The corresponding char to the Braille sign. 
	 * If braille6 is used, the method returns the first char of the corresponding string.
	 */
	@Deprecated
	public char getCharFromBraille(boolean[] p);
	
	/**
	 * @param p
	 * Braille pin allocation where
	 * p[0] = P1
	 * p[1] = P2
	 * p[2] = P3
	 * p[3] = P4
	 * p[4] = P5
	 * p[5] = P6
	 * p[6] = P7 // if braille8
	 * p[7] = P8 // if braille8
	 * 
	 * @return
	 * A string of pressed Buttons like 'P123' stands for 
	 * P1, P2 and P3 is pressed
	 * 
	 */
	public String getPressedButtons(boolean[] p);
	
	/**
	 * @return
	 * the calculated value from the pressed buttons
	 */
	public int getValue();
}

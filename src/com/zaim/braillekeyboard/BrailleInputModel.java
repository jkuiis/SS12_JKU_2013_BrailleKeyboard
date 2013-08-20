package com.zaim.braillekeyboard;

import java.util.Observable;

import android.util.Log;

public class BrailleInputModel extends Observable {
	private String input = "";
	
	public void addChar(char c) {
		input += String.valueOf(c);
		
		setChanged();
		notifyObservers(input);
	}
	
	public void addString(String s) {
		input += s;
		
		setChanged();
		notifyObservers(input);
	}
	
	public String getLastChar() {
		if (input.length() == 0) {
			return "";
		}
		else {
			return input.substring(input.length() - 1, input.length());
		}
	}
	
	public String getLastWord() {
		int index = input.lastIndexOf(" ");
		if (index == -1 ) {
			index = input.lastIndexOf("\n");
			if (index == -1) {
				return "";
			}
		}
		return input.substring(index + 1);
	}
	
	public String getLastSentence() {
		int index = input.lastIndexOf(".");
		if (index == -1) {
			index = input.lastIndexOf("\n");
			if (index == -1) {
				return "";
			}
		}
		return input.substring(index + 2);
	}
	
	public void deleteLastWord() {
		int index = input.lastIndexOf(" ");
		if (index == -1) {
			index = input.lastIndexOf("\n");
			if (index == -1) {
				if (input.length() > 0) {
					input = "";
				}
			}
			else {
				input = input.substring(0, index);
			}
		}
		else {
			input = input.substring(0, index);
		}
		
		setChanged();
		notifyObservers(input);
	}
	
	public void deleteLastChar() {
		if (input.length() != 0) {
			input = input.substring(0, input.length() - 1);
		}
		
		setChanged();
		notifyObservers(input);
	}
	
	public void clear() {
		input = "";
		
		setChanged();
		notifyObservers(input);
	}

	public String getInputText() {
		return input;
	}
}

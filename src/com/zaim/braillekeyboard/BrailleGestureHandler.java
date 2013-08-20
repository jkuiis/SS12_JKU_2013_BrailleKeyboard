package com.zaim.braillekeyboard;

import android.os.Handler;
import android.os.Message;
import com.zaim.braillekeyboard.BrailleGestureListener;

public class BrailleGestureHandler extends Handler {
	private BrailleActivity brailleActivity;
	
	public BrailleGestureHandler(BrailleActivity brailleActivity) {
		this.brailleActivity = brailleActivity;
	}
	
	@Override
	public void handleMessage(Message msg) {
		BrailleInputModel input = brailleActivity.getInput();
		
		switch (msg.what) {
		case BrailleGestureListener.NOTHING_TO_DELETE:
			brailleActivity.speak("Nothing to delete!");
			break;
		case BrailleGestureListener.WORD_DELETED:
			input.deleteLastWord();
			brailleActivity.speak("Word deleted!");
			break;
		case BrailleGestureListener.CHAR_DELETED:
			input.deleteLastChar();
			brailleActivity.speak("Letter deleted!");
			break;
		case BrailleGestureListener.NOTHING_TO_READ:
			brailleActivity.speak("Nothing to read!");
			break;
		case BrailleGestureListener.READ_WORD:
			brailleActivity.speak(input.getLastWord());
			break;
		case BrailleGestureListener.READ_SENTENCE:
			brailleActivity.speak(input.getLastSentence());
			break;
		case BrailleGestureListener.NEW_LINE:
			input.addChar('\n');
			brailleActivity.speak("New line!");
			break;
		case BrailleGestureListener.SPACE:
			input.addString(" ");
			brailleActivity.speak("Empty space!");
			break;
		}
			
	}

}

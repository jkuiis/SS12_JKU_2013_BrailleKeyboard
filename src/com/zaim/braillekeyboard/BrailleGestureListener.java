package com.zaim.braillekeyboard;

import android.speech.tts.TextToSpeech;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public class BrailleGestureListener extends SimpleOnGestureListener {
	private static final String TAG = "BrailleGestureListener";
	
	public static final int NOTHING_TO_DELETE = 0;
	public static final int CHAR_DELETED = 1;
	public static final int WORD_DELETED = 2;
	public static final int NOTHING_TO_READ = 3;
	public static final int READ_WORD = 4;
	public static final int READ_SENTENCE = 5;
	public static final int NEW_LINE = 6;
	public static final int SPACE = 7;
	
	private static final int SWIPE_THRESHOLD = 100;
	private static final int SWIPE_VELOCITY_THRESHOLD = 100;

	private int numOfLeftSwipes = 0;
	private int numOfRightSwipes = 0;
	
	private BrailleInputModel input;
	private BrailleTouchModel model;
	private BrailleGestureHandler handler;
	
	private TextToSpeech tts;

	private BrailleActivity brailleActivity;
	
	public BrailleGestureListener(BrailleActivity brailleActivity) {
		this.brailleActivity = brailleActivity;
		
		input = brailleActivity.getInput();
		model = brailleActivity.getModel();
		handler = brailleActivity.getHandler();
		
		tts = brailleActivity.getTts();
	}
	
	@Override
	public boolean onDown(MotionEvent e) {
		return true;
	}

	private class DoubleLeftSwipeObserver implements Runnable {
		private int interval = 100; //ms
		private int timeout = 500; //ms
		private long timePassed = 0;

		@Override
		public void run() {
			while (true) {
				if (numOfLeftSwipes == 2) {
					numOfLeftSwipes = 0;
					String inputText = input.getInputText();
					if (inputText.equals("")) {
						handler.sendEmptyMessage(NOTHING_TO_DELETE);
					}
					else {
						handler.sendEmptyMessage(WORD_DELETED);
					}
					return;
				}
				else if (timePassed >= timeout) {
					numOfLeftSwipes = 0;
					String inputText = input.getInputText();
					if (inputText.equals("")) {
						handler.sendEmptyMessage(NOTHING_TO_DELETE);
					}
					else {
						handler.sendEmptyMessage(CHAR_DELETED);
					}

					return;
				}
				else {
					try {
						timePassed += interval;
						Thread.sleep(interval);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();;
					}
				}
			}
		}

	}
	
	private class DoubleRightSwipeObserver implements Runnable {
		private int interval = 100; //ms
		private int timeout = 500; //ms
		private long timePassed = 0;

		@Override
		public void run() {
			while (true) {
				if (numOfRightSwipes == 2) {
					numOfRightSwipes = 0;
					String lastSentence = input.getLastSentence();
					if (lastSentence.equals("")) {
						handler.sendEmptyMessage(NOTHING_TO_READ);
					}
					else {
						handler.sendEmptyMessage(READ_SENTENCE);
					}

					return;
				}
				else if (timePassed >= timeout) {
					numOfRightSwipes = 0;
					String lastWord = input.getLastWord();
					if (lastWord.equals("")) {
						handler.sendEmptyMessage(NOTHING_TO_READ);
					}
					else {
						handler.sendEmptyMessage(READ_WORD);
					}

					return;
				}
				else {
					try {
						timePassed += interval;
						Thread.sleep(interval);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();;
					}
				}
			}
		}

	}
	

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		boolean result = false;
		try {
			float diffY = e2.getY() - e1.getY();
			float diffX = e2.getX() - e1.getX();
			if (Math.abs(diffX) > Math.abs(diffY)) {
				if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
					if (diffX > 0) {
						onSwipeRight();
						result = true;
					} else {
						onSwipeLeft();
						result = true;
					}
				}
			} else {
				if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
					if (diffY > 0) {
						onSwipeDown();
						result = true;
					} else {
						onSwipeUp();
						result = true;
					}
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		model.beepSemaphore = true;
		if (!result) {
			brailleActivity.beepError();
		}
		return result;
	}

	public void onSwipeRight() {
		if (numOfRightSwipes == 0) {
			new Thread(new DoubleRightSwipeObserver()).start();
		}
		numOfRightSwipes++;
	}

	public void onSwipeLeft() {
		if (numOfLeftSwipes == 0) {
			new Thread(new DoubleLeftSwipeObserver()).start();
		}
		numOfLeftSwipes++;
	}

	public void onSwipeUp() {
		handler.sendEmptyMessage(SPACE);
	}

	public void onSwipeDown() {
		handler.sendEmptyMessage(NEW_LINE);
	}
}

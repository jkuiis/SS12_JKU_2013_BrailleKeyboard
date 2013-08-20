package com.zaim.braillekeyboard;

import java.util.Locale;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;

import com.gg.brailledecoder.ReturnBraille;

public class BrailleActivity extends Activity implements OnTouchListener {
	private static final String TAG = "BrailleActivity";

	private TextToSpeech tts;
	private boolean ttsLoaded;
	
	private SoundPool soundPool;
	private boolean soundPoolReady;
	private int errorBeepSoundID;
	
	private int numOfErrorsBeforeCalibration;
	
	private BrailleTouchModel model;
	private BrailleInputModel input;
	private BrailleView view;
	private BrailleTextView textView;
	private BrailleRecognition recognition;
	private GestureDetector gestureDetector;
	private BrailleGestureHandler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		//Remove title bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_braille);

		tts = new TextToSpeech(this, new OnInitListener() {
			@Override
			public void onInit(int status) {
				if (status == TextToSpeech.SUCCESS) {
					ttsLoaded = true;
					tts.setLanguage(Locale.getDefault());
				}
			}
		});

		model = new BrailleTouchModel();

		input = new BrailleInputModel();
		
		handler = new BrailleGestureHandler(this);

		view = (BrailleView) findViewById(R.id.brailleTouchView);
		view.setBrailleTouchModel(model);
		model.addObserver(view);
		view.setBrailleInputModel(input);
		view.setOnTouchListener(this);
		
		textView = (BrailleTextView) findViewById(R.id.brailleTextView);
		input.addObserver(textView);
	
		recognition = new BrailleRecognition(model);
		
		gestureDetector = new GestureDetector(this, new BrailleGestureListener(this));

		soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
				if (status == 0) { // 0 means success.
					soundPoolReady = true;
				}
			}
		});
		errorBeepSoundID = soundPool.load(this, R.raw.beep, 1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.braille, menu);
		return true;
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		gestureDetector.onTouchEvent(event);

		
		int action = event.getActionMasked();
		if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN) {
			model.addTouchPoint(event);
		}
		else if (action == MotionEvent.ACTION_POINTER_UP) {
			model.fingerLifted(event);
		}
		else if (action == MotionEvent.ACTION_UP) {
			if (!model.isCalibrated()) {
				if (model.numOfTouchPoints() != 6) {
					if (numOfErrorsBeforeCalibration == 5) {
						speak("Touch the screen with 6 fingers to calibrate!");
						numOfErrorsBeforeCalibration = 0;
					}
					else {
						beepError();
					}
				}
				else {
					model.calibrate();
					tts.speak("Kalibriert", TextToSpeech.QUEUE_FLUSH, null);
					Log.v(TAG, "Calibrated!");
				}
			}
			else {
				if (model.numOfTouchPoints() > 7) {
					beepError();
					Log.v(TAG, "Too many fingers!");
				}
				else if (model.numOfTouchPoints() == 6) {
					model.calibrate();
					tts.speak("Kalibriert", TextToSpeech.QUEUE_FLUSH, null);
				}
				else {
					model.fingerLifted(event);
					ReturnBraille result = recognition.recognize();
					if (!result.getSign().equals("invalid")) {
						input.addString(result.getSign());
						tts.speak(result.voiceOutput(), TextToSpeech.QUEUE_FLUSH, null);
					}
					else {
						if (model.beepSemaphore) {
							beepError();
						}
					}
				}
			}

			model.clear();
		}

		return true;
	}

	public void speak(String text) {
		if (ttsLoaded) {
			tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
		}
	}
	
	public void beepError() {
		if (soundPoolReady) {
			soundPool.play(errorBeepSoundID, 1, 1, 1, 0, 1f);
		}
	}
	
	
	public TextToSpeech getTts() {
		return tts;
	}

	public SoundPool getSoundPool() {
		return soundPool;
	}

	public BrailleTouchModel getModel() {
		return model;
	}

	public BrailleInputModel getInput() {
		return input;
	}

	public BrailleGestureHandler getHandler() {
		return handler;
	}
	

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		tts.shutdown();
		soundPool.release();
	}
	
}

package sk.tuke.ursus.activities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

import sk.tuke.ursus.MyApplication;
import sk.tuke.ursus.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

/**
 * Hlavn� aktivita
 * @author Vlastimil Bre�ka
 *
 */
public class MainMenu extends Activity implements OnTouchListener {

	/**
	 * Kon�tanta dial�gu
	 */
	private static final int APP_DATA_NOT_SETUP = 0;
	
	/**
	 * Premenn� aplik�cie, dr�� glob�lne premenn�
	 */
	private MyApplication app;
	
	/**
	 * Premenn� vibratoru
	 */
	private Vibrator vibrator;
	
	/**
	 * Tla�idlo �tartu
	 */
	private ImageButton startButton;
	
	/**
	 * Tla�idlo nastaven�
	 */
	private ImageButton settingsButton;
	
	/**
	 * Tla�idlo O mne
	 */
	private ImageButton aboutButton;

	/**
	 * Met�da onCreate
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.main_menu);
		
		app = (MyApplication) getApplication();
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
				
		initViews();
		addListeners();

		loadAppData();
	}

	/**
	 * Prid� listenery
	 */
	private void addListeners() {
		startButton.setOnTouchListener(this);
		settingsButton.setOnTouchListener(this);
		aboutButton.setOnTouchListener(this);
	}

	/**
	 * Inicializuje views
	 */
	private void initViews() {
		startButton = (ImageButton) findViewById(R.id.startButton);
		settingsButton = (ImageButton) findViewById(R.id.settingsButton);
		aboutButton = (ImageButton) findViewById(R.id.aboutButton);
	}

	/**
	 * Na��ta objek aplik�cie disku a nastav� glob�lne premenn�
	 */
	private void loadAppData() {

		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = openFileInput(MyApplication.FILENAME);
			ois = new ObjectInputStream(fis);

			MyApplication loadedApp = (MyApplication) ois.readObject();

			app.setEmailAddresses(loadedApp.getEmailAddresses());
			app.setXmlURL(loadedApp.getXmlURL());
			app.setPhpURL(loadedApp.getPhpURL());
			app.setReadyToStart(loadedApp.isReadyToStart());

			fis.close();
			ois.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Met�da onTouch
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			
			vibrator.vibrate(30);
			v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shrink));
			
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			
			v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.enlarge));
			
			switch (v.getId()) {
				case R.id.startButton:
					
					if (app.isReadyToStart()) {
						startActivity(new Intent(getApplicationContext(), RoomSelection.class));
					} else {
						showDialog(APP_DATA_NOT_SETUP);
					}
					break;
				case R.id.settingsButton:
					startActivity(new Intent(getApplicationContext(), Settings.class));
					break;
				case R.id.aboutButton:
					startActivity(new Intent(getApplicationContext(), About.class));
					break;
			}
		}
		return false;
	}
	
	/**
	 * Met�da onCreateDialog
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		if(id == APP_DATA_NOT_SETUP) {
			dialog = new AlertDialog.Builder(this)
			.setMessage("There are no email recipients, or URLs set up. Please, set this up in Settings first.")
			.setTitle("Warning")
			.setNeutralButton("Dismiss", null)
			.create();
		}
		return dialog;

	}
	
	/**
	 * Met�da onResume
	 */
	@Override
	protected void onResume() {
		super.onResume();
	}

	/**
	 * Met�da onPause
	 */
	@Override
	protected void onPause() {
		super.onPause();
	}

}

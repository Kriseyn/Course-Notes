package edu.ucuccs.mycoursenote;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Notes_Recording extends Activity {

	private MediaRecorder myRecorder;
	private MediaPlayer myPlayer;
	private String outputFile = null;
	private Button startBtn;
	private Button stopBtn;
	private Button playBtn;
	private Button stopPlayBtn;
	private TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notes__recording);

		text = (TextView) findViewById(R.id.text1);
		// store it to sd card
		outputFile = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/Course_Notes.3gpp";

		myRecorder = new MediaRecorder();
		myRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		myRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		myRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
		myRecorder.setOutputFile(outputFile);

		startBtn = (Button) findViewById(R.id.start);
		startBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				start(v);
			}
		});

		stopBtn = (Button) findViewById(R.id.stop);
		stopBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stop(v);
			}
		});

		playBtn = (Button) findViewById(R.id.play);
		playBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				play(v);
			}
		});

		stopPlayBtn = (Button) findViewById(R.id.stopPlay);
		stopPlayBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopPlay(v);
			}
		});
	}

	public void start(View view) {
		try {
			myRecorder.prepare();
			myRecorder.start();
		} catch (IllegalStateException e) {
			// start:it is called before prepare()
			// prepare: it is called after start() or before setOutputFormat()
			e.printStackTrace();
		} catch (IOException e) {
			// prepare() fails
			e.printStackTrace();
		}

		text.setText("Status: Recording");
		startBtn.setEnabled(false);
		stopBtn.setEnabled(true);

		Toast.makeText(getApplicationContext(), "Start Recording",
				Toast.LENGTH_SHORT).show();
	}

	public void stop(View view) {
		try {
			myRecorder.stop();
			myRecorder.release();
			myRecorder = null;

			stopBtn.setEnabled(false);
			playBtn.setEnabled(true);
			text.setText("Status: Stop recording");

			Toast.makeText(getApplicationContext(), "Stop Recording",
					Toast.LENGTH_SHORT).show();
		} catch (IllegalStateException e) {
			// it is called before start()
			e.printStackTrace();
		} catch (RuntimeException e) {
			// no valid audio/video data has been received
			e.printStackTrace();
		}
	}

	public void play(View view) {
		try {
			myPlayer = new MediaPlayer();
			myPlayer.setDataSource(outputFile);
			myPlayer.prepare();
			myPlayer.start();

			playBtn.setEnabled(false);
			stopPlayBtn.setEnabled(true);
			text.setText("Status: Playing");

			Toast.makeText(getApplicationContext(),
					"Playing the record", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stopPlay(View view) {
		try {
			if (myPlayer != null) {
				myPlayer.stop();
				myPlayer.release();
				myPlayer = null;
				playBtn.setEnabled(true);
				stopPlayBtn.setEnabled(false);
				text.setText("Status: Stop playing");

				Toast.makeText(getApplicationContext(),
						"Stop playing", Toast.LENGTH_SHORT)
						.show();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package edu.ucuccs.mycoursenote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreateNotes extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_notes);
	}

	public void btnRecording(View v) {
		Intent intent = new Intent(CreateNotes.this, Notes_Recording.class);
		startActivity(intent);
	}

	public void btnScribbling(View v) {
		Intent intent = new Intent(CreateNotes.this, Notes_Scribbling.class);
		startActivity(intent);
	}

	public void btnText(View v) {
		Intent intent = new Intent(CreateNotes.this, Notes_Text.class);
		startActivity(intent);
	}
	
}

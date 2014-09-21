package edu.ucuccs.mycoursenote;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Notes_Scribbling extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notes__scribbling);
		BrushView view=new BrushView(this);
		setContentView(view);
		addContentView(view.btnEraseAll, view.params);
	}
	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notes__scribbling, menu);
		return true;
	}
	
	public void btnAlarm(View v) {
		Intent intent = new Intent(Notes_Scribbling.this, Notes_Alarm.class);
		startActivity(intent);
	}

}

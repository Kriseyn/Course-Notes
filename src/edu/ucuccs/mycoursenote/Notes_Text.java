package edu.ucuccs.mycoursenote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Notes_Text extends Activity {

	DbAdapter dbcon = new DbAdapter(this);
	Button btnSave;
	EditText editCompose, editSubject;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notes__text);

		btnSave = (Button) findViewById(R.id.btnSaveText);
		editCompose = (EditText) findViewById(R.id.editMsg);
		editSubject = (EditText) findViewById(R.id.editSubject);
		
	}

	public void btnSaveText(View v) {
		dbcon.open();

		long connectionsave = dbcon.method_compose(editSubject.getText()
				.toString(), editCompose.getText().toString());

		if (connectionsave > 0) {
			Toast.makeText(getApplicationContext(), "New Notes Saved",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(getApplicationContext(), "Notes NOT Saved",
					Toast.LENGTH_LONG).show();
		}

		dbcon.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notes__text, menu);
		return true;
	}

	public void btnAlarm(View v) {
		Intent intent = new Intent(Notes_Text.this, Notes_Alarm.class);
		startActivity(intent);
	}

}

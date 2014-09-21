package edu.ucuccs.mycoursenote;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import edu.ucuccs.mycoursenote.adapter.TitleNavigationAdapter;
import edu.ucuccs.mycoursenote.model.SpinnerNavItem;


		public class MainActivity extends Activity implements
		ActionBar.OnNavigationListener {
			
			DbAdapter dbcon = new DbAdapter(this);
			
			
			
			
			

	// action bar
	private ActionBar actionBar;

	// Title navigation Spinner data
	private ArrayList<SpinnerNavItem> navSpinner;

	// Navigation adapter
	private TitleNavigationAdapter adapter;

	// Refresh menu item
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		

		actionBar = getActionBar();

		// Hide the action bar title
		actionBar.setDisplayShowTitleEnabled(false);

		// Enabling Spinner dropdown navigation
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		// Spinner title navigation data
		navSpinner = new ArrayList<SpinnerNavItem>();
		navSpinner.add(new SpinnerNavItem("Subject", R.drawable.mm));
		navSpinner.add(new SpinnerNavItem("Title", R.drawable.mm));

		// title drop down adapter
		adapter = new TitleNavigationAdapter(getApplicationContext(),
				navSpinner);

		// assigning the spinner navigation
		actionBar.setListNavigationCallbacks(adapter, this);

		// Changing the action bar icon
		// actionBar.setIcon(R.drawable.ico_actionbar);
	}


	
	/**
	 * On selecting action bar icons
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		
		case R.id.intent:
			// location found
			Activity_intent();
			return true;
		
		case R.id.intent1:
			// location found
			Activity_intent();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Launching new activity
	 * */
	private void Activity_intent() {
		Intent i = new Intent(MainActivity.this,CreateNotes.class);
		startActivity(i);
	}

	/*
	 * Actionbar navigation item select listener
	 */
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// Action to be taken after selecting a spinner item
		return false;
	}

	
	
	public void btnSubmit(View v) {
		Intent intent = new Intent(MainActivity.this, CreateNotes.class);
		startActivity(intent);
		
		
	}
	
	
	
	
	
	private void populatelist() {

		Cursor showAll = dbcon.method_showAllRecords();
		adapter.clear();
		adapter.notifyDataSetChanged();
		if (showAll.moveToFirst()) {
			do {
				adapter.add("Subject:" + showAll.getString(1).toString()
						);
			} while (showAll.moveToNext());
		}
	}

	
	
	
}
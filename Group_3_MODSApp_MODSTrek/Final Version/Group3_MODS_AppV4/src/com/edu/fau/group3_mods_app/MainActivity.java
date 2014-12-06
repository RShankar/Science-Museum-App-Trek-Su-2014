package com.edu.fau.group3_mods_app;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	private Button aboutButton;
	private Button floorButton;
	private Button eventsProgramsButton;
	private Button nFCButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//sets button to the view's button
		floorButton = (Button)findViewById(R.id.floor_map_btn);		
		//adds click listener
		floorButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View currentView) {
				//creates new intent
				Intent floorMap1PageIntent = getPackageManager().getLaunchIntentForPackage("processing.test.floormaps");
				
				//starts activity
				startActivityForResult(floorMap1PageIntent,0);
			}
		});
		
		//sets button to the view's button
		eventsProgramsButton = (Button)findViewById(R.id.events_and_programs);		
				//adds click listener
		eventsProgramsButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View currentView) {
						//creates new intent
						Intent eventsPageIntent = new Intent(currentView.getContext(), EventsPrograms.class);
						
						//starts activity
						startActivityForResult(eventsPageIntent,1);
						overridePendingTransition(R.animator.animation2, R.animator.animation1);
						onPause();
						
					}
				});
				
		//sets button to the view's button
		aboutButton = (Button)findViewById(R.id.options_btn);		
		//adds click listener
		aboutButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View currentView) {
				//creates new intent
				Intent aboutPageIntent = new Intent(currentView.getContext(), AboutPage.class);
				
				//starts activity
				startActivityForResult(aboutPageIntent,2);
				overridePendingTransition(R.animator.animation2, R.animator.animation1);
				onPause();
				
			}
		});
		
	//sets button to the view's button
	nFCButton = (Button)findViewById(R.id.NFC_btn);	
	//adds click listener
	nFCButton.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View currentView) {
			//creates new intent
			Intent nFCPageIntent = getPackageManager().getLaunchIntentForPackage("processing.test.enablenfc");
			//starts activity
			startActivityForResult(nFCPageIntent,3);
			overridePendingTransition(R.animator.animation2, R.animator.animation1);
			onPause();
			
		}
	});
}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}

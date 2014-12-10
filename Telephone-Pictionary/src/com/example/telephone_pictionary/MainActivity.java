package com.example.telephone_pictionary;

import android.content.Intent;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View; 


public class MainActivity extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
	//	 Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) 
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void gameDirections(View view) 
	{
	
		Intent intent = new Intent(this, Explanation.class);
	    startActivity(intent);
	}
	
	//leads to new game page
	// When Host Game button is pressed, but Pass And Play is
	// going to lead here for now
	public void newGame(View view)
	{
		//this is crashing. i will have to check this out later. 
		Intent intent2 = new Intent(this, Connect.class);
		startActivity(intent2);
	} 
	
	//This is what happens when Join Game button is pushed.
	public void joinGame(View view)
	{
		//Intent intent = new Intent(this, JoinGame.class)
		
	}	
	//This is what happens when Pass and Play button is pushed.
	public void passAndPlay(View view)
	{
		Intent intent = new Intent(this, PassPlay.class);
		startActivity(intent);
	}
	
	public void drawCanvasTemp(View view){
		Intent intent3 = new Intent(this, CanvasDrawer.class);
		startActivity(intent3);
	}
	
	



}

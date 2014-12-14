package com.example.telephone_pictionary;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View; 
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.Toast;


public class MainActivity extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void gameDirections(View view) 
	{
	
		Intent intent = new Intent(this, Explanation.class);
	    startActivity(intent);
	}
	
	
	//remove these? 
	
	public void hostGame(View view)
	{
		Intent intent = new Intent(this, HostGame.class);
		startActivity(intent);
	}
	
	// This is what happens when Join Game button is pushed.
	public void joinGame(View view)
	{
		Intent intent = new Intent(this, JoinGame.class);
		startActivity(intent);	
	}	
	
	// This is what happens when Pass and Play button is pushed.
	public void passAndPlay(View view)
	{
		AlertDialog.Builder playerPicker = new AlertDialog.Builder(this);
		
		LayoutInflater inflater = this.getLayoutInflater();
		View DialogView = inflater.inflate(R.layout.number_picker, null);
		
		playerPicker.setView(DialogView); 
		
		final NumberPicker numPlayers = (NumberPicker)DialogView.findViewById(R.id.numpick); 
		numPlayers.setMinValue(4);
		numPlayers.setMaxValue(12); 
		numPlayers.setWrapSelectorWheel(false);
        numPlayers.setValue(4);
        numPlayers.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		
		playerPicker.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dInterface, int id) {
				int numOfPlayers = numPlayers.getValue();
				dInterface.dismiss(); // This might need to be after starting canvas writer
				
				// give the model the number of players
				Model.clearModel();
				Model model = Model.getInstance();
				model.setNumUsers(numOfPlayers);
				
				// Start Canvas Writer
				Intent intent = new Intent();
				intent.setClassName("com.example.telephone_pictionary", "com.example.telephone_pictionary.CanvasWriter"); 
				startActivity(intent);	
				
				Context context = getApplicationContext();
				CharSequence loadingMessage = "Enter your first word or phrase";
				int duration = Toast.LENGTH_SHORT;
				Toast.makeText(context, loadingMessage, duration).show();
			}
		});
		
		playerPicker.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dInterface, int id) {
				dInterface.cancel();
			}
		});
		playerPicker.create().show();
	}
}

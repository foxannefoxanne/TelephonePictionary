package com.example.telephone_pictionary;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View; 
import android.widget.EditText;
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
	
	public void newGame(View view)
	{
		// this is crashing. i will have to check this out later. 
		Intent intent2 = new Intent(this, Connect.class);
		startActivity(intent2);
	} 
	
	// This is what happens when Join Game button is pushed.
	public void joinGame(View view)
	{
		// Intent intent = new Intent(this, JoinGame.class)
		
	}	
	// This is what happens when Pass and Play button is pushed.
	public void passAndPlay(View view)
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Pass and Play");
		alertDialogBuilder.setMessage(R.string.numPlayers);
		
		final EditText input  = new EditText(this);
		alertDialogBuilder.setView(input);
		
		alertDialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dInterface, int id) {

				int numOfPlayers = Integer.parseInt(input.getText().toString());
				dInterface.dismiss(); // This might need to be after starting canvas writer
				
				// give the model the number of players
				Model.clearModel();
				Model model = Model.getInstance();
				model.setNumUsers(numOfPlayers);
				
				// Start Canvas Writer
				Intent intent = new Intent();
				intent.setClassName("com.example.telephone_pictionary", "com.example.telephone_pictionary.CanvasWriter"); 
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(intent);	
				
				Context context = getApplicationContext();
				CharSequence loadingMessage = "Enter your first word or phrase";
				int duration = Toast.LENGTH_SHORT;
				Toast.makeText(context, loadingMessage, duration).show();
			}
		});
		alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dInterface, int id) {
				dInterface.cancel();
			}
		});
		alertDialogBuilder.create().show();
	}
}

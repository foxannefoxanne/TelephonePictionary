package com.example.telephone_pictionary;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CanvasWriter extends Activity {
	private WritingTools writeTool; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canvas_writer);
		
		writeTool = (WritingTools)findViewById(R.id.writing);
		writeTool.setDrawingCacheEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.canvas_writer, menu);
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

	public void writeDialog(View view)
	{
		
		TextView textView = new TextView(this); 
		AlertDialog.Builder  textInput = new AlertDialog.Builder(this);
		textInput.setTitle("Guess:");
		LayoutInflater inflater = this.getLayoutInflater(); 
		
		// final View textRetriever = inflater.inflate(R.layout.text_input, null);
		final EditText guess  = new EditText(this);
		textInput.setView(guess);
		// final EditText guess  = (EditText)textRetriever.findViewById(R.id.textEntry);

		textInput.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dinterface, int id) {
				// EditText guess  = (EditText)textRetriever.findViewById(R.id.textEntry);
				String guessString = guess.getText().toString();
				writeTool.createText(guessString); 
				dinterface.dismiss(); 
			}
		});
		textInput.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dinterface, int id) {
				dinterface.cancel();

			}
		});
		textInput.create().show();				
	}
	
	public void submitImage(View view) {
		
		writeTool.setDrawingCacheEnabled(true);
   	    Bitmap bitmap = writeTool.getDrawingCache();
   	    Model model = Model.getInstance();
   	  
   	    Card cardStorage = new Card(bitmap,Card.Type.TEXT); 

	    if (model.saveCard(cardStorage)) {
	    	// end game
	    	Intent intent = new Intent();
	    	intent.setClassName("com.example.telephone_pictionary", "com.example.telephone_pictionary.EndGame");
	    	startActivity(intent);
	    }
	    else {
	    	// go to card viewer
   	    	Intent intent = new Intent();
			intent.setClassName("com.example.telephone_pictionary", "com.example.telephone_pictionary.CardViewer"); 
			startActivity(intent);	
	    }

	}
}

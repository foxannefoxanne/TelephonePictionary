package com.example.telephone_pictionary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageButton;
import android.widget.LinearLayout;


import java.util.UUID;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.widget.Toast;



public class CanvasDrawer extends Activity{

	private DrawingTools drawTool; 
	private ImageButton currPaint, drawButton, saveButton, clearButton; 
	private float sBrush, mBrush, lBrush;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canvas_drawer);
		drawTool = (DrawingTools)findViewById(R.id.drawing);
		sBrush = getResources().getInteger(R.integer.small);
		mBrush = getResources().getInteger(R.integer.med);
		lBrush = getResources().getInteger(R.integer.large);
		
		drawTool.setDrawingCacheEnabled(true);
	//	drawButton = (ImageButton)findViewById(R.id.drawing); 
	//	drawButton.setOnClickListener(this); 
	
	//	saveButton = (ImageButton)findViewById(R.id.save_button);
	//	saveButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.canvas_drawer, menu);
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
	
	//eventually will switch to previous card view
	public void toCardView(View view) {
		
	}
	
	//to instructions page. 
	public void toInstructions(View view) {
		Intent intent = new Intent(this, Explanation.class);
	    startActivity(intent);
	}

	
	
	public void changeColor(View view){
		final Dialog colorChooser = new Dialog(this);
		colorChooser.setTitle("Color:");
		colorChooser.setContentView(R.layout.color_picker); 
		colorChooser.show();
	
	}
	
	public void changeBrushSize(View view){
		final Dialog brushChooser = new Dialog(this); 
		brushChooser.setTitle("Brush size:");
		brushChooser.setContentView(R.layout.brush_picker);
		brushChooser.show();
	}	
	

	public void paintClicker(View view){
			if(view != currPaint){
				ImageButton newColor = (ImageButton)view;
				String color = view.getTag().toString();
				drawTool.setColor(color); 
			}
	}
	
	
	//function to save images
	public void savetoDevice(View view) {
		 AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
		 saveDialog.setTitle("Save Image:");
		 saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
			 public void onClick(DialogInterface dialog, int which){
				 String savedImage = MediaStore.Images.Media.insertImage(
						 getContentResolver(), drawTool.getDrawingCache(),
						 UUID.randomUUID().toString() + ".png", "drawing");
				 
				 if(savedImage!=null){
					 	Toast successToast = Toast.makeText(getApplicationContext(),
					 		"Image saved.", Toast.LENGTH_SHORT);
					 		successToast.show();
					 	}
					 	else{
					 		Toast failedToast = Toast.makeText(getApplicationContext(),
					 		"Save unsuccessful.", Toast.LENGTH_SHORT);
		 					failedToast.show(); 
					 	}
				 drawTool.destroyDrawingCache();
			 }
		 });
		 saveDialog.setNegativeButton("No", new DialogInterface.OnClickListener(){
			 public void onClick(DialogInterface dialog, int which){
				 dialog.cancel();
			 }
		 });
	}

	//clear image
	public void clearImage(View view) {
		AlertDialog.Builder resetDialog = new AlertDialog.Builder(this);
		resetDialog.setTitle("Clear Image:");
		resetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				drawTool.startNew(); 
				dialog.dismiss(); 
			}
		}); 
		resetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		}); 
	}
		
	//will eventually submit image to queue
	public void submitImage(View view) {
	
	}
	
}

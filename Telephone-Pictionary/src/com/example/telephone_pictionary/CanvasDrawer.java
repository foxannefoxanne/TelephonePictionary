package com.example.telephone_pictionary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import android.provider.MediaStore;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

public class CanvasDrawer extends Activity{

	private DrawingTools drawTool; 
	private ImageButton currPaint, drawButton, saveButton, clearButton; 
	private float xsBrush, sBrush, mBrush, lBrush, xlBrush;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canvas_drawer);
		drawTool = (DrawingTools)findViewById(R.id.drawing);
		
		xsBrush = getResources().getInteger(R.integer.xsmall);
		sBrush = getResources().getInteger(R.integer.small);
		mBrush = getResources().getInteger(R.integer.med);
		lBrush = getResources().getInteger(R.integer.large);
		xlBrush = getResources().getInteger(R.integer.xlarge); 
		
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

	
	
	public void changeColor(View view) {
		final Dialog colorChooser = new Dialog(this);
		colorChooser.setTitle("Color:");
	    colorChooser.setContentView(R.layout.color_picker); 
	    
	    ImageButton color1 = (ImageButton)colorChooser.findViewById(R.id.color1);
	    ImageButton color2 = (ImageButton)colorChooser.findViewById(R.id.color2);
	    ImageButton color3 = (ImageButton)colorChooser.findViewById(R.id.color3);
	    ImageButton color4 = (ImageButton)colorChooser.findViewById(R.id.color4);
	    ImageButton color5 = (ImageButton)colorChooser.findViewById(R.id.color5);
	    ImageButton color6 = (ImageButton)colorChooser.findViewById(R.id.color6);
	    ImageButton color7 = (ImageButton)colorChooser.findViewById(R.id.color7);
	    ImageButton color8 = (ImageButton)colorChooser.findViewById(R.id.color8);
	    ImageButton color9 = (ImageButton)colorChooser.findViewById(R.id.color9);
	    ImageButton color10 = (ImageButton)colorChooser.findViewById(R.id.color10);
	    ImageButton color11 = (ImageButton)colorChooser.findViewById(R.id.color11);
	    ImageButton color12 = (ImageButton)colorChooser.findViewById(R.id.color12);
	    ImageButton color13 = (ImageButton)colorChooser.findViewById(R.id.color13);
	    ImageButton color14 = (ImageButton)colorChooser.findViewById(R.id.color14);
	    ImageButton color15 = (ImageButton)colorChooser.findViewById(R.id.color15);
	    ImageButton color16 = (ImageButton)colorChooser.findViewById(R.id.color16);
	    ImageButton color17  = (ImageButton)colorChooser.findViewById(R.id.color17);
	    ImageButton color18 = (ImageButton)colorChooser.findViewById(R.id.color18);
	    ImageButton color19 = (ImageButton)colorChooser.findViewById(R.id.color19);
	    ImageButton color20 = (ImageButton)colorChooser.findViewById(R.id.color20);
	

	    OnClickListener listener = new OnClickListener() {
		public void onClick(View v) {	
			drawTool.setErase(false); 
			String color = v.getTag().toString();
			drawTool.setColor(color);
			colorChooser.dismiss(); 
		}};
		colorChooser.show();

		color1.setOnClickListener(listener);
		color2.setOnClickListener(listener);
		color3.setOnClickListener(listener);
		color4.setOnClickListener(listener);
		color5.setOnClickListener(listener);
		color6.setOnClickListener(listener);
		color7.setOnClickListener(listener);
		color8.setOnClickListener(listener);
		color9.setOnClickListener(listener);
		color10.setOnClickListener(listener);
		color11.setOnClickListener(listener);
		color12.setOnClickListener(listener);
		color13.setOnClickListener(listener);
		color14.setOnClickListener(listener);
		color15.setOnClickListener(listener);
		color16.setOnClickListener(listener);
		color17.setOnClickListener(listener);
		color18.setOnClickListener(listener);
		color19.setOnClickListener(listener);
		color20.setOnClickListener(listener);
	}
	
	public void changeBrushSize(View view) {
	    final Dialog brushChooser = new Dialog(this); 
		brushChooser.setTitle("Brush size:");
		brushChooser.setContentView(R.layout.brush_picker);
		drawTool.setErase(false); 

		ImageButton xsButton = (ImageButton)brushChooser.findViewById(R.id.xs_brush);
		xsButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick (View v) {
				drawTool.setBrushSize(xsBrush);
				drawTool.setLastBrushSize(xsBrush); 
				brushChooser.dismiss();
			} }); 

		
		ImageButton sButton = (ImageButton)brushChooser.findViewById(R.id.s_brush);
			sButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick (View v) {
				drawTool.setBrushSize(sBrush);
				drawTool.setLastBrushSize(sBrush); 
				brushChooser.dismiss();
			} }); 
		
		ImageButton mButton = (ImageButton)brushChooser.findViewById(R.id.m_brush);
		mButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick (View v){
				drawTool.setBrushSize(mBrush);
				drawTool.setLastBrushSize(mBrush); 
				brushChooser.dismiss();
			}}); 
		
		
		ImageButton lButton = (ImageButton)brushChooser.findViewById(R.id.l_brush);
		lButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick (View v){
				drawTool.setBrushSize(lBrush);
				drawTool.setLastBrushSize(lBrush); 
				brushChooser.dismiss();
			}}); 
		
		ImageButton xlButton = (ImageButton)brushChooser.findViewById(R.id.xl_brush);
		xlButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick (View v){
				drawTool.setBrushSize(xlBrush);
				drawTool.setLastBrushSize(xlBrush); 
				brushChooser.dismiss();
			}}); 
		
		brushChooser.show();
		
	}	
	


	//NEEDS HELP. 
	//function to save images
	public void savetoDevice(View view) {
		 AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
		 saveDialog.setTitle("Save Image:");
		 saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
			 public void onClick(DialogInterface dialog, int which) {
				 drawTool.setDrawingCacheEnabled(true);
				 Bitmap bitmap = drawTool.getDrawingCache();
				 String path = Environment.getExternalStorageDirectory().getAbsolutePath();
				 File file = new File(path+"/image.png");
				 FileOutputStream ostream;
				 try {
					 file.createNewFile();
					 ostream = new FileOutputStream(file);
					 bitmap.compress(CompressFormat.PNG, 100, ostream);
					 ostream.flush();
					 ostream.close();
					 Toast.makeText(getApplicationContext(), "image saved", 5000).show();
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "error", 5000).show();
				}

				 drawTool.destroyDrawingCache();
			 }
		 });
		 saveDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface dialog, int which) {
				 dialog.cancel();
			 }
		 });
		saveDialog.show(); 

	}

	// clear image
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
		
		resetDialog.show(); 
	}
		
	public void eraseImage(View view) {
		drawTool.setErase(true);
		
	}
	//will eventually submit image to queue
	public void submitImage(View view) {
		
		drawTool.setDrawingCacheEnabled(true);
   	    Bitmap bitmap = drawTool.getDrawingCache();

   	    Model model = Model.getInstance();

   	    Card cardStorage = new Card(bitmap,Card.Type.IMAGE); 

   	    if (model.saveCard(cardStorage)) {
   	    	// end of game
	    	Intent intent = new Intent();
	    	intent.setClassName("com.example.telephone_pictionary", "com.example.telephone_pictionary.EndGame");
	    	startActivity(intent);
   	    }
   	    else {
   	    	// pass to next player
   	    	Intent intent = new Intent();
			intent.setClassName("com.example.telephone_pictionary", "com.example.telephone_pictionary.CardViewer"); 
			startActivity(intent);	
			
			Context context = getApplicationContext();
			CharSequence loadingMessage = "Pass to next player";
			int duration = Toast.LENGTH_SHORT;
			Toast.makeText(context, loadingMessage, duration).show();
   	    }
   	    
   	    

	}
	

	
}

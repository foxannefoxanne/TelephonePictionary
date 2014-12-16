package com.example.telephone_pictionary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	private float xsBrush, sBrush, mBrush, lBrush, xlBrush;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canvas_drawer);
		drawTool = (DrawingTools)findViewById(R.id.drawing);
		
		//set up brushes for later user
		xsBrush = getResources().getInteger(R.integer.xsmall);
		sBrush = getResources().getInteger(R.integer.small);
		mBrush = getResources().getInteger(R.integer.med);
		lBrush = getResources().getInteger(R.integer.large);
		xlBrush = getResources().getInteger(R.integer.xlarge); 
		
		//keep track of drawing
		drawTool.setDrawingCacheEnabled(true);


	}
	
	//return to card view
	public void toCardView(View view) {
		Intent intent = new Intent(this, CardViewer.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(intent);
	}
	
	// to instructions page. 
	public void toInstructions(View view) {
		Intent intent = new Intent(this, Explanation.class);
	    startActivity(intent);
	}

	//change colors
	public void changeColor(View view) {

		//create dialog, disable standard android title
		final Dialog colorChooser = new Dialog(this);
	    colorChooser.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    colorChooser.setContentView(R.layout.color_picker); 

	    
	    //large, gross chunk of code. initializes each button to a color
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
	

	    //on click. if color is selected, changes in draw tools and closes dialog
	    OnClickListener listener = new OnClickListener() {
		public void onClick(View v) {	
			String color = v.getTag().toString();
			drawTool.setColor(color);
			colorChooser.dismiss(); 
		}};
		colorChooser.show();

		//another super gross chunk of code to set up onclick
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
	    brushChooser.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    brushChooser.setContentView(R.layout.brush_picker);
		
	    
	    //initiate all buttons
		ImageButton xsButton = (ImageButton)brushChooser.findViewById(R.id.xs_brush);
		ImageButton sButton = (ImageButton)brushChooser.findViewById(R.id.s_brush);
		ImageButton mButton = (ImageButton)brushChooser.findViewById(R.id.m_brush);
		ImageButton lButton = (ImageButton)brushChooser.findViewById(R.id.l_brush);
		ImageButton xlButton = (ImageButton)brushChooser.findViewById(R.id.xl_brush);
	
		//on click to change all brush sizes
		 OnClickListener listener = new OnClickListener() {
				public void onClick(View v) {	
				 switch(v.getId())
				 {
				 	case R.id.xs_brush:
				 		drawTool.setBrushSize(xsBrush);
						break; 
				 	case R.id.s_brush:
				 		drawTool.setBrushSize(sBrush);
						break; 
				 	case R.id.m_brush:
				 		drawTool.setBrushSize(mBrush);
						break; 
				 	case R.id.l_brush:
				 		drawTool.setBrushSize(lBrush);
						break; 
				 	case R.id.xl_brush:
				 		drawTool.setBrushSize(xlBrush);
						break; 
				 }
				 	brushChooser.dismiss();
				}};
		  
		//set up listener with buttons
		xsButton.setOnClickListener(listener);
		sButton.setOnClickListener(listener);
		mButton.setOnClickListener(listener);
		lButton.setOnClickListener(listener);
		xlButton.setOnClickListener(listener);

		//show dialog
		brushChooser.show();
		
	}	
	
	// function to save images
	public void savetoDevice(View view) {
	
		//build alert dialog.
		 AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
		 LayoutInflater inflater = this.getLayoutInflater();
		 View DialogView = inflater.inflate(R.layout.save_dialog, null);
		 saveDialog.setView(DialogView); 

		 //user chooses to save
		 saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface dialog, int which) {
				//get drawing cache
				drawTool.setDrawingCacheEnabled(true);
				Bitmap bitmap = drawTool.getDrawingCache();
				 
				// create new time-stamped file name
				String path = Environment.getExternalStorageDirectory().getAbsolutePath();
				String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSS").format(new Date());
			    File file = new File(path + File.separator + "IMG_"+ timeStamp + ".png");
				
				 //attempt to save image
			    FileOutputStream ostream;
			    try {
			    	file.createNewFile();
			    	ostream = new FileOutputStream(file);
			    	bitmap.compress(CompressFormat.PNG, 100, ostream);
			    	ostream.flush();
			    	ostream.close();
			    	Toast.makeText(getApplicationContext(), "Image saved", 5000).show();
			    } catch (Exception e) {
			    	e.printStackTrace();
			    	Toast.makeText(getApplicationContext(), "Error", 5000).show();
			    }

				 drawTool.destroyDrawingCache();
			 }
		 });
		 
		 //no option, dialog exits.
		 saveDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface dialog, int which) {
				 dialog.cancel();
			 }
		 });
		saveDialog.show(); 
	}

	// clear image
	public void clearImage(View view) {
	
		//build alert dialog
		AlertDialog.Builder resetDialog = new AlertDialog.Builder(this);
		LayoutInflater inflater = this.getLayoutInflater();
		View DialogView = inflater.inflate(R.layout.clr_dialog, null);
		resetDialog.setView(DialogView); 
		 
		//user opts to clear, drawCanvas is empty
		resetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				drawTool.startNew(); 
				dialog.dismiss(); 
			}
		}); 
		
		//close dialog
		resetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		}); 
		
		resetDialog.show(); 
	}
	
	//sets draw color to white 
	public void eraseImage(View view) {
		drawTool.setErase();
	}

	
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
	    	
			Context context = getApplicationContext();
			CharSequence loadingMessage = "End of Game\nSwipe->";
			int duration = Toast.LENGTH_SHORT;
			Toast.makeText(context, loadingMessage, duration).show();
			
			this.setResult(RESULT_OK);
			finish();
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
			this.setResult(RESULT_OK);
			finish();
   	    }
   	    
	}
	
	public void toMain(View view) {
		finish();
	}
	
}

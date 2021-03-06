package com.example.telephone_pictionary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.content.Context;
import android.graphics.Bitmap;

public class CanvasDrawer extends Activity
{
	private DrawingTools drawTool; 
	private float xsBrush, sBrush, mBrush, lBrush, xlBrush;
	private ImageButton currentSize; 
	private boolean colorSetter, brushSetter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canvas_drawer);
		drawTool = (DrawingTools)findViewById(R.id.drawing);
		
		// set up brushes for later user
		xsBrush = getResources().getInteger(R.integer.xsmall);
		sBrush = getResources().getInteger(R.integer.small);
		mBrush = getResources().getInteger(R.integer.med);
		lBrush = getResources().getInteger(R.integer.large);
		xlBrush = getResources().getInteger(R.integer.xlarge); 
	
	
		colorSetter = false; 
		brushSetter = false; 
		
		// keep track of drawing
		drawTool.setDrawingCacheEnabled(true);
	}
	
	// return to card view
	public void toCardView(View view) 
	{
		Intent intent = new Intent(this, CardViewer.class);
		// bring back same card viewer if it is returned to
		intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(intent);
	}
	
	// to instructions page
	public void toInstructions(View view) 
	{
		Intent intent = new Intent(this, Explanation.class);
	    startActivity(intent);
	}

	// change colors
	public void changeColor(View view) 
	{
		// create dialog, disable standard android title
		final Dialog colorChooser = new Dialog(this);
	    colorChooser.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    colorChooser.setContentView(R.layout.color_picker); 
	   
	    
	    // large, gross chunk of code. initializes each button to a color
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
	  
	    if(!colorSetter){
	    	color20.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    }
	    else{
		if(color1.getId() == drawTool.getColorButton().getId())
	    		color1.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    	else if(color2.getId() == drawTool.getColorButton().getId())
	    		color2.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    	else if(color3.getId() == drawTool.getColorButton().getId())
	    		color3.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    	else if(color4.getId() == drawTool.getColorButton().getId())
	    		color4.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    	else if(color4.getId() == drawTool.getColorButton().getId())
	    		color4.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    	else if(color5.getId() == drawTool.getColorButton().getId())
	    		color5.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    	else if(color6.getId() == drawTool.getColorButton().getId())
	    		color6.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    	else if(color7.getId() == drawTool.getColorButton().getId())
	    		color7.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    	else if(color8.getId() == drawTool.getColorButton().getId())
	    		color8.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    	else if(color9.getId() == drawTool.getColorButton().getId())
	    		color9.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    	else if(color10.getId() == drawTool.getColorButton().getId())
	    		color10.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    	else if(color11.getId() == drawTool.getColorButton().getId())
	    		color11.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    	else if(color12.getId() == drawTool.getColorButton().getId())
	    		color12.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    	else if(color13.getId() == drawTool.getColorButton().getId())
	    		color13.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    	else if(color14.getId() == drawTool.getColorButton().getId())
	    		color14.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    	else if(color15.getId() == drawTool.getColorButton().getId())
	    		color15.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    	else if(color16.getId() == drawTool.getColorButton().getId())
	    		color16.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    	else if(color17.getId() == drawTool.getColorButton().getId())
	    		color17.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    	else if(color18.getId() == drawTool.getColorButton().getId())
	    		color18.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    	else if(color19.getId() == drawTool.getColorButton().getId())
	    		color19.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    	else if(color20.getId() == drawTool.getColorButton().getId())
	    		color20.setImageDrawable(getResources().getDrawable(R.drawable.paint_selected));
	    }

	    // on click. if color is selected, changes in draw tools and closes dialog
	    OnClickListener listener = new OnClickListener() 
	    {
	    	public void onClick(View v) 
	    	{	
				String color = v.getTag().toString();
				colorSetter = true; 
				drawTool.setColor(color, (ImageButton)colorChooser.findViewById(v.getId()));
				colorChooser.dismiss(); 
	    	}
	    };

		colorChooser.show();

		// another super gross chunk of code to set up onclick
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
	  		

	    if(!brushSetter){
	       	mButton.setImageDrawable(getResources().getDrawable(R.drawable.mbrush_selected));
	    }
	    else{
			if(drawTool.getBrushSize().equals("xsBrush")){
			    	xsButton.setImageDrawable(getResources().getDrawable(R.drawable.xsbrush_selected));}
		    else if(drawTool.getBrushSize().equals("sBrush")){
		    		sButton.setImageDrawable(getResources().getDrawable(R.drawable.sbrush_selected));}
		     else if(drawTool.getBrushSize().equals("mBrush")){
		    	 	mButton.setImageDrawable(getResources().getDrawable(R.drawable.mbrush_selected));
		     }
			 else if(drawTool.getBrushSize().equals("lBrush")){
			    	lButton.setImageDrawable(getResources().getDrawable(R.drawable.lbrush_selected));
			    }
			 else if(drawTool.getBrushSize().equals("xlBrush")){
				 	xlButton.setImageDrawable(getResources().getDrawable(R.drawable.xlbrush_selected));
			 }

	    }

	  
	
		//on click to change all brush sizes
		 OnClickListener listener = new OnClickListener() {
				public void onClick(View v) 
				{	
					switch(v.getId())
					{
					 	case R.id.xs_brush:
					 		drawTool.setBrushSize(xsBrush, "xsBrush");
							break; 
					 	case R.id.s_brush:
					 		drawTool.setBrushSize(sBrush, "sBrush");
							break; 
					 	case R.id.m_brush:
					 		drawTool.setBrushSize(mBrush, "mBrush");
					 		break; 
					 	case R.id.l_brush:
					 		drawTool.setBrushSize(lBrush, "lBrush");
							break; 
					 	case R.id.xl_brush:
					 		drawTool.setBrushSize(xlBrush, "xlBrush");
					 		break; 
					}
					brushSetter = true; 
				 	brushChooser.dismiss();
				}
			};
		  
		// set up listener with buttons
		xsButton.setOnClickListener(listener);
		sButton.setOnClickListener(listener);
		mButton.setOnClickListener(listener);
		lButton.setOnClickListener(listener);
		xlButton.setOnClickListener(listener);

		// show dialog
		brushChooser.show();
	}	
	

	// function to save images
	public void savetoDevice(View view) 
	{
		// build alert dialog.
		AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
		LayoutInflater inflater = this.getLayoutInflater();
		View DialogView = inflater.inflate(R.layout.save_dialog, null);
		saveDialog.setView(DialogView); 

		// user chooses to save
		saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() 
		{
			public void onClick(DialogInterface dialog, int which) 
			{
				// get drawing cache
				drawTool.setDrawingCacheEnabled(true);
				Bitmap bitmap = drawTool.getDrawingCache();
				 
				// create new time-stamped file name & save to gallery
				String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSS").format(new Date());
				String filename = "IMG_" + timeStamp;
				MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, filename, null);
				Toast.makeText(getApplicationContext(), "Image saved", 5000).show();

				drawTool.destroyDrawingCache();
			 }
		 });
		 
		 // no option, dialog exits
		 saveDialog.setNegativeButton("No", new DialogInterface.OnClickListener() 
		 {
			 public void onClick(DialogInterface dialog, int which) 
			 {
				 dialog.cancel();
			 }
		 });
		 saveDialog.show(); 
	}

	// clear image
	public void clearImage(View view) 
	{
		// build alert dialog
		AlertDialog.Builder resetDialog = new AlertDialog.Builder(this);
		LayoutInflater inflater = this.getLayoutInflater();
		View DialogView = inflater.inflate(R.layout.clr_dialog, null);
		resetDialog.setView(DialogView); 
		 
		// user opts to clear, drawCanvas is empty
		resetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() 
		{
			public void onClick(DialogInterface dialog, int which) 
			{
				drawTool.startNew(); 
				dialog.dismiss(); 
			}
		}); 
		
		// close dialog
		resetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() 
		{
			public void onClick(DialogInterface dialog, int which) 
			{
				dialog.cancel();
			}
		}); 
		
		resetDialog.show(); 
	}
	
	// sets draw color to white 
	public void eraseImage(View view) 
	{
		drawTool.setErase();
	}

	// submit message to model
	public void submitImage(View view) 
	{
		drawTool.setDrawingCacheEnabled(true);
   	    Bitmap bitmap = drawTool.getDrawingCache();

   	    Model model = Model.getInstance();

   	    Card cardStorage = new Card(bitmap,Card.Type.IMAGE); 

   	    if (model.saveCard(cardStorage)) 
   	    {
   	    	// end of game
	    	Intent intent = new Intent();
	    	intent.setClassName("com.example.telephone_pictionary", "com.example.telephone_pictionary.EndGame");
	    	startActivity(intent);
	    	
			Context context = getApplicationContext();
			CharSequence loadingMessage = "End of Game\nSwipe->";
			int duration = Toast.LENGTH_SHORT;
			Toast.makeText(context, loadingMessage, duration).show();
			
			// alert card viewer to close
			this.setResult(RESULT_OK);
			finish();
   	    }
   	    else 
   	    {
   	    	// pass to next player
   	    	Intent intent = new Intent();
			intent.setClassName("com.example.telephone_pictionary", "com.example.telephone_pictionary.CardViewer"); 
			startActivity(intent);	
			
			Context context = getApplicationContext();
			CharSequence loadingMessage = "Pass to next player";
			int duration = Toast.LENGTH_SHORT;
			Toast.makeText(context, loadingMessage, duration).show();
			// alert card viewer to close
			this.setResult(RESULT_OK);
			finish();
   	    }
	}
	
	// exit current game and return to main
	public void toMain(View view) 
	{
		this.setResult(RESULT_OK);
		finish();
	}
	
}

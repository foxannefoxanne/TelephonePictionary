package com.example.telephone_pictionary;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class CardViewer extends Activity 
{
	public static final int CANVAS_REQUEST = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_viewer);
		
		Model model = Model.getInstance();
		viewPreviousCard(model.getLastCard().getImage());
		
		if(model.getLastCard().getType() == Card.Type.TEXT) 
		{
			ImageButton hideGuess = (ImageButton)findViewById(R.id.toGuess);
			hideGuess.setVisibility(View.GONE); 
			
			ImageButton hideSave = (ImageButton)findViewById(R.id.save_button);
			hideSave.setVisibility(View.GONE); 
		}
		else 
		{
			ImageButton hideDraw = (ImageButton)findViewById(R.id.toDraw);
			hideDraw.setVisibility(View.GONE); 
		}
	}
	
	
	public void toCanvas(View view) 
	{
		// take viewer to appropriate canvas
		Model model = Model.getInstance();
		if (model.getLastCard().getType() == Card.Type.TEXT) 
		{
			Intent intent = new Intent(this, CanvasDrawer.class);
			// if they return to drawer, bring activity to top rather than add a new one
			intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			// wait until drawer is done
			startActivityForResult(intent, CANVAS_REQUEST);
		}
		else 
		{
			Intent intent = new Intent(this, CanvasWriter.class);
			// if they return to writer, bring activity to top rather than add a new one
			intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			// wait until writer is done
			startActivityForResult(intent, CANVAS_REQUEST);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == CANVAS_REQUEST) 
		{
			if (resultCode == RESULT_OK) 
			{
				// when previous activity is done, remove this from stack
				finish();
			}
		}
	}
	
	public void viewPreviousCard(Bitmap bap)
	{
		ImageView previousCard = (ImageView)findViewById(R.id.prevCard);
		previousCard.setImageBitmap(bap); 
	}
	
	// when user clicks on save
	public void savetoDevice(View view) 
	{
		View content = findViewById(R.id.prevCard);
	    content.setDrawingCacheEnabled(true);
	    final Bitmap bitmap = content.getDrawingCache();
		
		AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
		
		saveDialog.setTitle("Save Image?");
		saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() 
		{
			 public void onClick(DialogInterface dialog, int which) 
			 {
				 // create new time-stamped file name & save to gallery
				 String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSS").format(new Date());
				 String filename = "IMG_" + timeStamp;
				 MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, filename, null);
				 Toast.makeText(getApplicationContext(), "Image saved", 5000).show();
			 }
		 });
		 saveDialog.setNegativeButton("No", new DialogInterface.OnClickListener() 
		 {
			 public void onClick(DialogInterface dialog, int which) 
			 {
				 dialog.cancel();
			 }
		 });
		saveDialog.show(); 
	}
	
	// to instructions page 
	public void toInstructions(View view) 
	{
		Intent intent = new Intent(this, Explanation.class);
	    startActivity(intent);
	}

	// to exit current game & return to main
	public void toMain(View view) 
	{
		finish();
	}
}

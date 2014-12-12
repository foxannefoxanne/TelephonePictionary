package com.example.telephone_pictionary;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class CardViewer extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_viewer);
		
		Model model = Model.getInstance();
		viewPreviousCard(model.getLastCard().getImage());
	}
	
	public void toCanvas(View view) {
		// take viewer to appropriate canvas
		Model model = Model.getInstance();
		if (model.getLastCard().getType() == Card.Type.TEXT) {
			Intent intent = new Intent(this, CanvasDrawer.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			startActivity(intent);
		}
		else {
			Intent intent = new Intent(this, CanvasWriter.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			startActivity(intent);
		}
	}
	
	public void viewPreviousCard(Bitmap bap)
	{
		ImageView previousCard = (ImageView)findViewById(R.id.prevCard);
		previousCard.setImageBitmap(bap); 
	
	}
	
	public void savetoDevice(View view) {
	   
		View content = findViewById(R.id.prevCard);
	    content.setDrawingCacheEnabled(true);
	    final Bitmap bitmap = content.getDrawingCache();

	        
		
		AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
		 
		
		 saveDialog.setTitle("Save Image?");
		 saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface dialog, int which) {
				 String path = Environment.getExternalStorageDirectory().getAbsolutePath();
				 File file = new File(path+"/image.png");
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

			 }
		 });
		 saveDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface dialog, int which) {
				 dialog.cancel();
			 }
		 });
		saveDialog.show(); 

	}
	
}

package com.example.telephone_pictionary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CanvasWriter extends Activity {
	private EditText eText;
	private Canvas writingCanvas;
	private Bitmap writingBitmap; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canvas_writer);
		

		eText = (EditText) findViewById(R.id.writingeditor);
		writingBitmap = Bitmap.createBitmap(50,50, Bitmap.Config.ARGB_8888);
		writingCanvas = new Canvas(writingBitmap);

		//if ()
			//ImageButton hideBack = (ImageButton)findViewById(R.id.back_button);
			//hideBack = setInvisibility(View.GONE); 
	}

	

	
	public void submitImage(View view) {
		
		//convert edit text to bitmap
		eText.setDrawingCacheEnabled(true);
		eText.buildDrawingCache(); 
   	    Bitmap bitmap = eText.getDrawingCache();
 
   	    //call model, store card; 
   	    Model model = Model.getInstance();
   	    Card cardStorage = new Card(bitmap,Card.Type.TEXT); 

	    if (model.saveCard(cardStorage)) {
	    	// end game
	    	Intent intent = new Intent();
	    	intent.setClassName("com.example.telephone_pictionary", "com.example.telephone_pictionary.EndGame");
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
	    	startActivity(intent);
	    }
	    else {
	    	// go to card viewer
   	    	Intent intent = new Intent();
			intent.setClassName("com.example.telephone_pictionary", "com.example.telephone_pictionary.CardViewer"); 
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			startActivity(intent);
			
			Context context = getApplicationContext();
			CharSequence loadingMessage = "Pass to next player";
			int duration = Toast.LENGTH_SHORT;
			Toast.makeText(context, loadingMessage, duration).show();
	    }

	}
}

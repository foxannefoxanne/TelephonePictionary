package com.example.telephone_pictionary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class CanvasWriter extends Activity 
{
	private EditText eText;
	private Bitmap writingBitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canvas_writer);

		// starts text editor, bitmap, and canvas
		eText = (EditText) findViewById(R.id.writingeditor);
		writingBitmap = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);

		// if turn one, hide back button
		Model mod = Model.getInstance();
		if (mod.getTurn() == 1) 
		{
			ImageButton hideBack = (ImageButton) findViewById(R.id.back_button);
			hideBack.setVisibility(View.GONE);
		}
	}

	// to instructions page 
	public void toInstructions(View view) 
	{
		Intent intent = new Intent(this, Explanation.class);
	    startActivity(intent);
	}

	// to previous card
	public void toCardView(View view) 
	{
		Intent intent = new Intent(this, CardViewer.class);
		// bring same card-viewer forward if returned to
		intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(intent);
	}

	// send image to model
	public void submitImage(View view) 
	{
		// convert edit text to bitmap
		eText.setCursorVisible(false);
		eText.clearComposingText();
		eText.setDrawingCacheEnabled(true);
		eText.buildDrawingCache();
		Bitmap bitmap = eText.getDrawingCache();

		// call model, store card.
		Model model = Model.getInstance();
		Card cardStorage = new Card(bitmap, Card.Type.TEXT);

		if (model.saveCard(cardStorage)) 
		{
			// end game
			Intent intent = new Intent();
			intent.setClassName("com.example.telephone_pictionary",
					"com.example.telephone_pictionary.EndGame");
			startActivity(intent);

			Context context = getApplicationContext();
			CharSequence loadingMessage = "End of Game\nSwipe ->";
			int duration = Toast.LENGTH_SHORT;
			Toast.makeText(context, loadingMessage, duration).show();

			// tell card viewer to end
			this.setResult(RESULT_OK);
			finish();
		} 
		else 
		{
			// go to card viewer
			Intent intent = new Intent();
			intent.setClassName("com.example.telephone_pictionary",
					"com.example.telephone_pictionary.CardViewer");
			startActivity(intent);

			Context context = getApplicationContext();
			CharSequence loadingMessage = "Pass to next player";
			int duration = Toast.LENGTH_SHORT;
			Toast.makeText(context, loadingMessage, duration).show();
			
			// tell card viewer to end
			this.setResult(RESULT_OK);
			finish();
		}

	}
	
	// exit current game and return to main
	public void toMain(View view) 
	{
		finish();
	}
}

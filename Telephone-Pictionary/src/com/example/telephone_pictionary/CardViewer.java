package com.example.telephone_pictionary;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CardViewer extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_viewer);
	}
	
	public void toCanvas(View view) {
		// take viewer to appropriate canvas
		Model model = Model.getInstance();
		if (model.getLastCard().m_type == Card.Type.TEXT) {
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


}

package com.example.telephone_pictionary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.graphics.Paint;


public class WritingTools extends View {
	
	private Canvas writingCanvas;
	private Bitmap writingBitmap; 
	private Paint canvasPaint; 
	
	public WritingTools(Context context, AttributeSet attrs){
		super(context, attrs);
		setUpWritingTools();
	}
	
	public void setUpWritingTools()
	{
		
	}
	
	protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight){
		
		super.onSizeChanged(width, height, oldWidth, oldHeight);
		writingBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
		writingCanvas = new Canvas(writingBitmap);
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		
		canvas.drawBitmap(writingBitmap, 50, 50, canvasPaint);
	
	}

	
	public void createText(String text)
	{
		Paint paintText = new Paint();
		paintText.setColor(Color.RED);
		paintText.setTextSize(50);
		writingCanvas.drawColor(Color.TRANSPARENT);
		writingCanvas.drawText(text,50,50,paintText); 
	}
}

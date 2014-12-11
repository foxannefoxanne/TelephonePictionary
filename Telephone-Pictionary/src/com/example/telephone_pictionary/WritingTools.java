package com.example.telephone_pictionary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
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

	
		TextPaint textPaint = new TextPaint(); 
		textPaint.setColor(Color.BLACK);
		textPaint.setTextSize(100); 

		writingCanvas.drawColor(Color.WHITE);
		//Paint paintText = new Paint();
		//paintText.setColor(Color.BLACK);
		//paintText.setTextSize(100);
		writingCanvas.drawColor(Color.TRANSPARENT);
		
		StaticLayout textLayout = new StaticLayout(text, textPaint, getWidth(), Alignment.ALIGN_NORMAL, 1, 0, false);
		textLayout.draw(writingCanvas);

		
	//	writingCanvas.drawText(text,15,100,paintText); 
		
	}
}

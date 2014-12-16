package com.example.telephone_pictionary;

import android.view.View;
import android.content.Context;
import android.util.AttributeSet; 

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.util.TypedValue;
import android.graphics.Color;

public class DrawingTools extends View {
	
	
	private Path drawPath;
	private Paint drawPaint, canvasPaint;
	private int paintColor;
	private Canvas drawingCanvas;
	private Bitmap drawingBitmap; 
	private float brushSize;
	private String brushName; 
	private ImageButton currentColor; 
	public DrawingTools(Context context, AttributeSet attrs){
		super(context, attrs);
		setUpDrawingTools();
	}
	
	private void setUpDrawingTools(){
		//set up path, paint
		drawPath = new Path();
		drawPaint = new Paint();
		
		//set paint color to black, width to 20
		String colorName = "#000000";
		paintColor = Color.parseColor(colorName);
		drawPaint.setColor(paintColor);
		drawPaint.setStrokeWidth(20);
		
		//make paint brush "smooth" and round brush. 
		drawPaint.setAntiAlias(true);
		drawPaint.setStyle(Paint.Style.STROKE);
		drawPaint.setStrokeJoin(Paint.Join.ROUND);
		drawPaint.setStrokeCap(Paint.Cap.ROUND);
		
		// Eclipse describes dither_flag as:
		//"Paint flag that enables dithering when blitting."
		canvasPaint = new Paint(Paint.DITHER_FLAG);
		
		brushSize = getResources().getInteger(R.integer.med);
	
		drawPaint.setStrokeWidth(brushSize);
	}
	
	@Override
	protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
		super.onSizeChanged(width, height, oldWidth, oldHeight);
		drawingBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
		drawingCanvas = new Canvas(drawingBitmap);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(drawingBitmap, 0, 0, canvasPaint);
		canvas.drawPath(drawPath, drawPaint);
	}
	
	//detects touch of user on screen
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		float xCoord = event.getX();
		float yCoord = event.getY();
		
		switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:
				drawPath.moveTo(xCoord, yCoord);
				break;
			case MotionEvent.ACTION_MOVE:
				drawPath.lineTo(xCoord, yCoord);
				break;
			case MotionEvent.ACTION_UP:
				drawingCanvas.drawPath(drawPath, drawPaint);
				drawPath.reset(); 
				break;
		
			default:
				return false; 		
		}
		invalidate();
		return true; 
	}
	
	//erases all that is on canvas, starts new
	public void startNew() {
		drawingCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
		invalidate();
	}
	
	//clears old paint color, starts with new
	public void setColor(String color, ImageButton buttonName) {
		changeColor(color); 
		currentColor = buttonName; 
	}
	
	public void changeColor(String color){
		invalidate(); 
		paintColor = Color.parseColor(color);
		drawPaint.setColor(paintColor);  
	}
	//reset brush size
	public void setBrushSize(float newSize, String sizeName) {
		float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, newSize, getResources().getDisplayMetrics());
		brushSize = pixelAmount; 
		drawPaint.setStrokeWidth(brushSize); 
		brushName = sizeName; 
		
	}
	
	public String getBrushSize(){
		return brushName; 
	}
	//set erase (brush color to white)
	public void setErase() {

		this.changeColor("#FFFFFFFF");
	}
	
	public ImageButton getColorButton(){
		return currentColor; 
	}
	
}

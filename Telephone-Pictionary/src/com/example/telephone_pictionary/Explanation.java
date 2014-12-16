package com.example.telephone_pictionary;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Explanation extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_explanation);
	}
	
	public void close(View view) 
	{
		finish();
	}

}

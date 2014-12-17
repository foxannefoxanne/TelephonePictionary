package com.example.telephone_pictionary;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

public class EndGame extends FragmentActivity 
{
	private ViewPager m_pager;
	private CardPagerAdapter m_adapter;
	private static int numItems;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_end_game);
		
		Model model = Model.getInstance();
		numItems = model.getNumUsers();
		
		// create view pager for number of items in model
		m_adapter = new CardPagerAdapter(getSupportFragmentManager(), numItems);
		m_pager = (ViewPager) findViewById(R.id.pager);
		m_pager.setAdapter(m_adapter);
	}
	
	public static class CardPagerAdapter extends FragmentStatePagerAdapter 
	{
		private final int m_size;
		
		public CardPagerAdapter(FragmentManager fm, int size) 
		{
			super(fm);
			m_size = size;
		}
		
		@Override
		public int getCount() 
		{
			return m_size;
		}
		
		@Override
		public Fragment getItem(int position) 
		{
			return CardFragment.newInstance(position);
		}
	}
	
	// when save button is pushed
	public void saveToDevice(View view) 
	{
		AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);

		saveDialog.setTitle("Save All Images?");
		saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() 
		{
			public void onClick(DialogInterface dialog, int which) 
			{
				Model model = Model.getInstance();
				// iterate through all pics in model to save all
				for (int i = 0; i < model.getNumUsers(); ++i) 
				{
					Bitmap bitmap = model.getCard(i).getImage();
					// create new time-stamped file name & save to gallery
					String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSS").format(new Date());
					String filename = "IMG_" + timeStamp;
					MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, filename, null);
				}
				Toast.makeText(getApplicationContext(), "Images saved", 5000).show();
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
	
	// take activity off stack to return to main
	public void toMain(View view) 
	{
		finish();
	}
}

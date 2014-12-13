package com.example.telephone_pictionary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class EndGame extends FragmentActivity {
	private ViewPager m_pager;
	private CardPagerAdapter m_adapter;
	private static int numItems;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_end_game);
		
		Model model = Model.getInstance();
		numItems = model.getNumUsers();
		
		m_adapter = new CardPagerAdapter(getSupportFragmentManager(), numItems);
		m_pager = (ViewPager) findViewById(R.id.pager);
		m_pager.setAdapter(m_adapter);
	}
	
	public static class CardPagerAdapter extends FragmentStatePagerAdapter {
		private final int m_size;
		
		public CardPagerAdapter(FragmentManager fm, int size) {
			super(fm);
			m_size = size;
		}
		
		@Override
		public int getCount() {
			return m_size;
		}
		
		@Override
		public Fragment getItem(int position) {
			return CardFragment.newInstance(position);
		}
	}
	
	// to instructions page. 
	public void toInstructions(View view) {
		Intent intent = new Intent(this, Explanation.class);
	    startActivity(intent);
	}

	
}

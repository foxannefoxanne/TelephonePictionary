package com.example.telephone_pictionary;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CardFragment extends Fragment 
{
	private int m_num;
    private ImageView m_imageView;
	
	static CardFragment newInstance(int cardNum) 
	{
		CardFragment frag = new CardFragment();
		Bundle args = new Bundle();
		args.putInt("resId", cardNum);
		frag.setArguments(args);
		return frag;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		m_num = getArguments() != null ? getArguments().getInt("resId") : -1;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		View v = inflater.inflate(R.layout.card_fragment, container, false);
		m_imageView = (ImageView) v.findViewById(R.id.cardView);
		return v;
	}
	
    @Override
    public void onActivityCreated(Bundle savedInstanceState) 
    {
        super.onActivityCreated(savedInstanceState);
        // get specified image from model
        Model mod = Model.getInstance();
        Bitmap bap = mod.getCard(m_num).getImage();
        // Load image into ImageView
        m_imageView.setImageBitmap(bap); 
    }
}

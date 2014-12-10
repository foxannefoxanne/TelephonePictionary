package com.example.telephone_pictionary;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

public class Model 
{
	private int numUsers;
	private List<Bitmap> cards;
	boolean isGameOver;
	
	public Model () 
	{
		numUsers = 0;
		cards = new ArrayList<Bitmap>();
		isGameOver = false;
	}
	
	public void setUsers(int users) 
	{
		numUsers = users;
	}
	
	public void saveCard(Bitmap card) 
	{
		cards.add(card);
	}
	
	public Bitmap getLastCard()
	{
		if (cards.isEmpty()) {
			return null;
		}
		return cards.get(cards.size() - 1);
	}
	
	public List<Bitmap> getAllCards() 
	{
		return cards;
	}
}

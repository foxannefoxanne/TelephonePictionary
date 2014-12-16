package com.example.telephone_pictionary;

import android.graphics.Bitmap;

public class Card 
{
	// allows you to keep track of type of card
	public enum Type 
	{
		IMAGE,
		TEXT
	}
	private Bitmap m_image;
	private Type m_type;

	public Card ()
	{
	}
	
	public Card (Bitmap image, Type type) 
	{
		m_image = image.copy(image.getConfig(), false);
		m_type = type;
	}
	
	public Bitmap getImage() 
	{
		return m_image;
	}
	
	public Card.Type getType() 
	{
		return m_type;
	}
}

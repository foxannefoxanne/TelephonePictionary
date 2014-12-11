package com.example.telephone_pictionary;

import android.graphics.Bitmap;

public class Card {
	public enum Type {
		IMAGE,
		TEXT
	}
	public Bitmap m_image;
	public Type m_type;

	Card (){
		
	}
	Card (Bitmap image, Type type) {
		m_image = image;
		m_type = type;
	}
}

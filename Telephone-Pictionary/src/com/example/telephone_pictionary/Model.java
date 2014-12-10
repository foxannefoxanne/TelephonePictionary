package com.example.telephone_pictionary;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

public class Model 
{
	private int m_numUsers;
	private List<Card> m_cards;
	boolean m_isGameOver;
	
	public Model () 
	{
		m_numUsers = 0;
		m_cards = new ArrayList<Card>();
		m_isGameOver = false;
	}
	
	public void setUsers(int users) 
	{
		m_numUsers = users;
	}
	
	public boolean saveCard(Card card) 
	{
		m_cards.add(card);
		if (m_cards.size() == m_numUsers) {
			// game needs to end
			return true;
		}
		// game continues
		return false;
	}
	
	public Card getLastCard()
	{
		if (m_cards.isEmpty()) {
			return null;
		}
		return m_cards.get(m_cards.size() - 1);
	}
	
	public List<Card> getAllCards() 
	{
		return m_cards;
	}
}

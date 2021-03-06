package com.example.telephone_pictionary;

import java.util.ArrayList;
import java.util.List;

public class Model 
{
	private int m_numUsers;
	private List<Card> m_cards;
	private static Model m_instance = null;
	private int turn;
	
	// disallow use of this
	protected Model () 
	{
		turn = 1;
		m_numUsers = 0;
		m_cards = new ArrayList<Card>();
	}
	
	// singleton model
	public synchronized static Model getInstance() 
	{
		if (m_instance == null) 
		{
			m_instance = new Model();
		}
		return m_instance;
	}
	
	// clear current model
	public static void clearModel() 
	{
		m_instance = null;
	}
	
	public void setNumUsers(int users) 
	{
		m_numUsers = users;
	}
	
	public int getNumUsers() 
	{
		return m_numUsers;
	}
	
	/*
	 * @return: true if game is over, false otherwise
	 */
	public boolean saveCard(Card card) 
	{
		m_cards.add(card);
		turn++;
		if (m_cards.size() == m_numUsers) 
		{
			// game needs to end
			return true;
		}
		// game continues
		return false;
	}
	
	public Card getLastCard()
	{
		if (m_cards.isEmpty()) 
		{
			return null;
		}
		return m_cards.get(m_cards.size() - 1);
	}
	
	public Card getCard(int index) 
	{
		return m_cards.get(index);
	}
	
	public int getTurn()
	{
		return turn;
	}
}

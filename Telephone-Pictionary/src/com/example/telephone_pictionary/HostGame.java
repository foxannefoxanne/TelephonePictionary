package com.example.telephone_pictionary;

import java.io.IOException;
import java.net.ServerSocket;

import android.app.Activity;
import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdManager.RegistrationListener;
import android.net.nsd.NsdServiceInfo;
import android.os.Bundle;

public class HostGame extends Activity 
{

	private ServerSocket m_serverSocket;
	private int m_localPort;
	private String m_serviceName;
	private NsdManager m_nsdManager;
	private RegistrationListener m_registrationListener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_host_game);
		
		initializeServerSocket();
		initializeRegistrationListener();
		m_nsdManager = (NsdManager) getSystemService(Context.NSD_SERVICE);
		registerService(m_localPort);
	}
	
	public void initializeServerSocket() {
	    // Initialize a server socket on the next available port
	    try {
			m_serverSocket = new ServerSocket(0);
		} catch (IOException e) {
			// TODO: figure out what to do with this
			e.printStackTrace();
		}

	    // Store the chosen port
	    m_localPort =  m_serverSocket.getLocalPort();
	}
	
	public void initializeRegistrationListener() {
		m_registrationListener = new NsdManager.RegistrationListener() {

	        @Override
	        public void onServiceRegistered(NsdServiceInfo NsdServiceInfo) {
	            // Save the service name.  Android may have changed it in order to
	            // resolve a conflict, so update the name you initially requested
	            // with the name Android actually used.
	            m_serviceName = NsdServiceInfo.getServiceName();
	        }

	        @Override
	        public void onRegistrationFailed(NsdServiceInfo serviceInfo, int errorCode) {
	            // Registration failed!  Put debugging code here to determine why.
	        }

	        @Override
	        public void onServiceUnregistered(NsdServiceInfo arg0) {
	            // Service has been unregistered.  This only happens when you call
	            // NsdManager.unregisterService() and pass in this listener.
	        }

	        @Override
	        public void onUnregistrationFailed(NsdServiceInfo serviceInfo, int errorCode) {
	            // Unregistration failed.  Put debugging code here to determine why.
	        }
	    };
	}
	
	public void registerService(int port) {
	    NsdServiceInfo serviceInfo  = new NsdServiceInfo();
	    serviceInfo.setServiceName("Telephonary");
	    serviceInfo.setServiceType("_http._tcp.");
	    serviceInfo.setPort(port);

	    m_nsdManager.registerService(serviceInfo, NsdManager.PROTOCOL_DNS_SD, m_registrationListener);
	}
}

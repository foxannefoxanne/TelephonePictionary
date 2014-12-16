package com.example.telephone_pictionary;

import java.net.InetAddress;

import android.app.Activity;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdManager.DiscoveryListener;
import android.net.nsd.NsdManager.ResolveListener;
import android.net.nsd.NsdServiceInfo;
import android.os.Bundle;
import android.util.Log;

public class JoinGame extends Activity 
{
	private NsdManager m_nsdManager;
	private DiscoveryListener m_discoveryListener;
	private final String TAG = "JoinGame";
	private String m_serviceType = "_http._tcp.";
	private String m_serviceName = "Telephonary";
	private ResolveListener m_resolveListener;
	private NsdServiceInfo m_service;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join_game);
		
//		initializeDiscoveryListener();
//		m_nsdManager.discoverServices(m_serviceType, NsdManager.PROTOCOL_DNS_SD, m_discoveryListener);
	}
	
	public void initializeDiscoveryListener() 
	{
	    // Instantiate a new DiscoveryListener
	    m_discoveryListener = new NsdManager.DiscoveryListener() 
	    {
	        //  Called as soon as service discovery begins.
	        @Override
	        public void onDiscoveryStarted(String regType) 
	        {
	            Log.d(TAG, "Service discovery started");
	        }

	        @Override
	        public void onServiceFound(NsdServiceInfo service) 
	        {
	            // A service was found!  Do something with it.
	            Log.d(TAG, "Service discovery success" + service);
	            if (!service.getServiceType().equals(m_serviceType)) 
	            {
	                // Service type is the string containing the protocol and
	                // transport layer for this service.
	                Log.d(TAG, "Unknown Service Type: " + service.getServiceType());
	            } 
	            else if (service.getServiceName().equals(m_serviceName)) 
	            {
	                // The name of the service tells the user what they'd be
	                // connecting to. It could be "Bob's Chat App".
	                Log.d(TAG, "Same machine: " + m_serviceName);
	            } 
	            else if (service.getServiceName().contains("Telephonary"))
	            {
	                m_nsdManager.resolveService(service, m_resolveListener);
	            }
	        }

	        @Override
	        public void onServiceLost(NsdServiceInfo service) 
	        {
	            // When the network service is no longer available.
	            // Internal bookkeeping code goes here.
	            Log.e(TAG, "service lost" + service);
	        }

	        @Override
	        public void onDiscoveryStopped(String serviceType) 
	        {
	            Log.i(TAG, "Discovery stopped: " + serviceType);
	        }

	        @Override
	        public void onStartDiscoveryFailed(String serviceType, int errorCode) 
	        {
	            Log.e(TAG, "Discovery failed: Error code:" + errorCode);
	            m_nsdManager.stopServiceDiscovery(this);
	        }

	        @Override
	        public void onStopDiscoveryFailed(String serviceType, int errorCode) 
	        {
	            Log.e(TAG, "Discovery failed: Error code:" + errorCode);
	            m_nsdManager.stopServiceDiscovery(this);
	        }
	    };
	}
	
	public void initializeResolveListener() 
	{
	    m_resolveListener = new NsdManager.ResolveListener() 
	    {
	        @Override
	        public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) 
	        {
	            // Called when the resolve fails.  Use the error code to debug.
	            Log.e(TAG, "Resolve failed" + errorCode);
	        }

	        @Override
	        public void onServiceResolved(NsdServiceInfo serviceInfo) 
	        {
	            Log.e(TAG, "Resolve Succeeded. " + serviceInfo);

	            if (serviceInfo.getServiceName().equals(m_serviceName)) 
	            {
	                Log.d(TAG, "Same IP.");
	                return;
	            }
	            m_service = serviceInfo;
	            int port = m_service.getPort();
	            InetAddress host = m_service.getHost();
	        }
	    };
	}
	
	
}

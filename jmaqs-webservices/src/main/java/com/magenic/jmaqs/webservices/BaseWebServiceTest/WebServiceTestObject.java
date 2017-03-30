// --------------------------------------------------
// <copyright file="WebServiceTestObject.java" company="Magenic">
// Copyright 2017 Magenic, All rights Reserved
// </copyright>
// --------------------------------------------------

package com.magenic.jmaqs.webservices.BaseWebServiceTest;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import com.magenic.jmaqs.utilities.Logging.Logger;
import com.magenic.jmaqs.utilities.Logging.LoggingEnabled;

// TODO extend from TestObject once the TestObject class has been updated to follow similar to the MAQS version
public abstract class WebServiceTestObject {

	protected ConcurrentHashMap<String, Logger> loggers;
	protected ConcurrentHashMap<String, ArrayList<String>> loggedExceptions;
	protected LoggingEnabled loggingEnabledSetting;

	public HttpClientWrapper webServiceWrapper;

	public WebServiceTestObject(HttpClientWrapper webServiceWrapper,
			ConcurrentHashMap<String, Logger> loggers) {
		this.webServiceWrapper = webServiceWrapper;
		this.loggers = loggers;
		loggedExceptions = new ConcurrentHashMap<String, ArrayList<String>>();
	}

}
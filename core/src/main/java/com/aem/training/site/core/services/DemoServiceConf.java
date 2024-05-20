package com.aem.training.site.core.services;

import org.apache.sling.api.resource.ResourceResolver;

public interface DemoServiceConf {

	String getName();
	String getEnvironmentName();
	String getInstanceType();
	int getCount();
}

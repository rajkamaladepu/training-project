package com.aem.training.site.core.services;

import org.apache.sling.api.resource.ResourceResolver;

public interface ApplicationService {
	
	String externalizeLink(ResourceResolver resolver, String path);
	String getEnvironmentName();
	String getInstanceType();
}

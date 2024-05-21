package com.aem.training.site.core.services;

import javax.jcr.Session;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import com.day.cq.search.QueryBuilder;
import com.google.gson.JsonArray;

public interface ApplicationService {
	String externalizeLink(ResourceResolver resolver, String path);
	String getEnvironmentName();
	String getInstanceType();
	JsonArray getResources(QueryBuilder queryBuilder, Session session);
	JsonArray getResourcesUsingSystemResourceResolver(QueryBuilder queryBuilder) throws LoginException;
	
}

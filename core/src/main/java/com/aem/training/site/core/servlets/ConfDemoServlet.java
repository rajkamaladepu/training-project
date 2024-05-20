package com.aem.training.site.core.servlets;
import com.aem.training.site.core.services.DemoServiceConf;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import java.io.IOException;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.training.site.core.services.ApplicationService;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = { "training/service/democonf" }, methods = { HttpConstants.METHOD_GET,
		HttpConstants.METHOD_POST }, extensions = "json")
@ServiceDescription("Servlet to demo Service")
public class ConfDemoServlet extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(GetJSONServlet.class);
	
	@Reference
	DemoServiceConf demoServiceconf;


	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("entry into doGet method");

		JsonArray tilesArray = new JsonArray();
		JsonObject test = new JsonObject();

		test.addProperty("Grreting" , "Hello");
		test.addProperty("Environment", demoServiceconf.getEnvironmentName());
		test.addProperty("Instance", demoServiceconf.getInstanceType());
		test.addProperty("Count", demoServiceconf.getCount());
		tilesArray.add(test);
		
		
		response.setContentType("application/json");
		response.getWriter().write(tilesArray.toString());
		
		
	}
}

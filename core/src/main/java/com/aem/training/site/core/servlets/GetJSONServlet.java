package com.aem.training.site.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.training.site.core.services.ApplicationService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = { "training/service/getjson" }, methods = { HttpConstants.METHOD_GET,
		HttpConstants.METHOD_POST }, extensions = "json")
@ServiceDescription("Servlet to get JSON")
public class GetJSONServlet extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(GetJSONServlet.class);

	@Reference
	ApplicationService applicationService;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("entry into doGet method");
		//   try {
		// Get request parameters for search
		String parentPath = request.getParameter("parentPath");
		String searchTerm = request.getParameter("q");
		String sortBy = request.getParameter("sortBy");


		JsonArray tilesArray = new JsonArray();
		JsonObject test = new JsonObject();
		test.addProperty("parentPath", StringUtils.isNotBlank(parentPath) ? parentPath : "Parent Path is Empty");
		test.addProperty("searchTerm", StringUtils.isNotBlank(searchTerm) ? searchTerm : "searchTerm is Empty");
		test.addProperty("sortBy", StringUtils.isNotBlank(sortBy) ? sortBy : "sortBy is Empty");
		test.addProperty("env name", applicationService.getEnvironmentName());
		test.addProperty("instance", applicationService.getEnvironmentName());
		test.addProperty("externalLink", applicationService.externalizeLink(request.getResourceResolver(), parentPath));
		tilesArray.add(test);
		
		
		
		response.setContentType("application/json");
		response.getWriter().write(tilesArray.toString());
		
	}
}

package com.aem.training.site.core.servlets;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

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

import com.day.cq.search.QueryBuilder;
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
	 private transient QueryBuilder queryBuilder;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("entry into doGet method");
		//   try {
		// Get request parameters for search
		
		String pageTitle = "Page Title Not Found";

		ResourceResolver resolver = request.getResourceResolver();

		String parentPath = request.getParameter("parentPath");
		String searchTerm = request.getParameter("q");
		String sortBy = request.getParameter("sortBy");
		String pagePath = request.getParameter("path");
		Resource pageResource = resolver.getResource(pagePath);
		if (pageResource != null && pageResource.adaptTo(Page.class) != null) {
		    Page page = pageResource.adaptTo(Page.class);
		    pageTitle = page.getTitle();
		}


		JsonArray tilesArray = new JsonArray();
		JsonObject test = new JsonObject();
		test.addProperty("parentPath", StringUtils.isNotBlank(parentPath) ? parentPath : "Parent Path is Empty");
		test.addProperty("searchTerm", StringUtils.isNotBlank(searchTerm) ? searchTerm : "searchTerm is Empty");
		test.addProperty("sortBy", StringUtils.isNotBlank(sortBy) ? sortBy : "sortBy is Empty");
		test.addProperty("pagePath", StringUtils.isNotBlank(pagePath) ? pagePath: "No Page Path provided");
		test.addProperty("pageTitle", pageTitle);
		tilesArray.add(test);
		response.setContentType("application/json");
		response.getWriter().write(tilesArray.toString());
		
		
	}
}

package com.aem.training.site.core.servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
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
import com.day.cq.search.QueryBuilder;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
	
	@Reference
	private QueryBuilder queryBuilder;
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("entry into doGet method");
		//   try {
		// Get request parameters for search
		String parentPath = request.getParameter("parentPath");
		String searchTerm = request.getParameter("q");
		String sortBy = request.getParameter("sortBy");
		
		ResourceResolver resourceResolver = request.getResourceResolver();
		Resource pagePath = resourceResolver.getResource(parentPath);
		Resource jcrContent = pagePath.getChild("jcr:content");
		
		//JCR
		String pageNameFromJCR = "";
		try {
			Node jcrContentNode = jcrContent.adaptTo(Node.class);
			pageNameFromJCR = jcrContentNode.getProperty("jcr:title").getValue().toString();
		} catch (PathNotFoundException e) {
			//log
			e.printStackTrace();
		} catch (RepositoryException e) {
			//log
			e.printStackTrace();
		}
		
		
		//Sling
		ValueMap vm = jcrContent.getValueMap();
		String pageNameFromSling = vm.get("jcr:title", String.class);
		
		
		//AEM 
		PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(parentPath);
		String pageNameFromAEM = page.getTitle();

		JsonObject test = new JsonObject();
		test.addProperty("parentPath", StringUtils.isNotBlank(parentPath) ? parentPath : "Parent Path is Empty");
		test.addProperty("title from JCR API", pageNameFromJCR);
		test.addProperty("title from Sling API", pageNameFromSling);
		test.addProperty("title from AEM API", pageNameFromAEM);
		test.addProperty("externalLink", applicationService.externalizeLink(resourceResolver, parentPath));
		test.addProperty("searchTerm", StringUtils.isNotBlank(searchTerm) ? searchTerm : "searchTerm is Empty");
		test.addProperty("sortBy", StringUtils.isNotBlank(sortBy) ? sortBy : "sortBy is Empty");
		test.addProperty("env name", applicationService.getEnvironmentName());
		test.addProperty("instance", applicationService.getInstanceType());
		test.add("pages from query", applicationService.getResources(queryBuilder, resourceResolver.adaptTo(Session.class)));
		
		response.setContentType("application/json");
		response.getWriter().write(test.toString());
		
	}
}

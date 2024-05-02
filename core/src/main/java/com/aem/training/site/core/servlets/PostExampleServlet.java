package com.aem.training.site.core.servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.JsonObject;


@Component(service = { Servlet.class })
@SlingServletResourceTypes(
        resourceTypes={"training/service/postjson"},
        methods= {HttpConstants.METHOD_GET,HttpConstants.METHOD_POST},
        extensions="json")
@ServiceDescription("Post Servlet Example")
public class PostExampleServlet extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostExampleServlet.class);
    
    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws ServletException, IOException {
    	log.debug("entry into doGet method");
		JsonObject test = new JsonObject();
		test.addProperty("test", "testvalue");
        String responseJson = test.toString();
        resp.setContentType("application/json");
        resp.getWriter().write(responseJson);
    }
    
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
    	String json = request.getParameter("json");
    	response.setContentType("application/json");
    	response.getWriter().write(json);
    }

}
package com.aem.training.site.core.servlets;

import java.io.IOException;

import javax.jcr.Session;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=UserID Servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/getuserid" })
public class GetUserIdServlet extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain;charset=UTF-8");
		String responseStatus = "Error while sending email!!";
		try {
			ResourceResolver resourceResolver = req.getResourceResolver();
			Session session = resourceResolver.adaptTo(Session.class);
			String userId = session.getUserID();
			logger.info("userID is {}", userId);
			responseStatus = userId;
			resp.setStatus(SlingHttpServletResponse.SC_OK);
		} catch (Exception e) {
			resp.setStatus(SlingHttpServletResponse.SC_BAD_REQUEST);
		}
		resp.getWriter().print(responseStatus);
	}
}

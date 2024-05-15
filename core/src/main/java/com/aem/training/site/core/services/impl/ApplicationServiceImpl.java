package com.aem.training.site.core.services.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import javax.jcr.Session;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.training.site.core.services.ApplicationService;
import com.aem.training.site.core.services.config.ApplicationConfiguration;
import com.day.cq.commons.Externalizer;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.eval.PathPredicateEvaluator;
import com.day.cq.search.eval.TypePredicateEvaluator;
import com.day.cq.wcm.api.NameConstants;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component(service = ApplicationService.class, immediate = true)
@Designate(ocd = ApplicationConfiguration.class)
public class ApplicationServiceImpl implements ApplicationService {

	@Reference
	private ResourceResolverFactory resolverFactory;
	
	@Reference
	private Externalizer externalizer;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private String environmentName;
	
	private String instanceType;

	@Activate
	@Modified
	protected void activate(final ApplicationConfiguration config) {
		this.instanceType = config.instancetype();
		this.environmentName = config.environmentname();
	}

	@Deactivate
	protected void deactivated() {
		
	}

  @Override
	public String externalizeLink(ResourceResolver resolver, String path) {
		return Optional.ofNullable(path).map(oPath -> {
			try {
				return externalizer.publishLink(resolver, oPath);
			} catch (IllegalArgumentException e) {
				logger.error("Error", e);
			} 
			return StringUtils.EMPTY;
		}).orElse(StringUtils.EMPTY);
	}


	public String getEnvironmentName() {
		return environmentName;
	}

	public String getInstanceType() {
		return instanceType;
	}

	@Override
	public JsonArray getResources(QueryBuilder queryBuilder, Session session) {
		final Map<String, String> predicateMap = new HashMap<>();
		predicateMap.put(PathPredicateEvaluator.PATH, "/content");
		predicateMap.put(TypePredicateEvaluator.TYPE, NameConstants.NT_PAGE);
		predicateMap.put("1_property", "@jcr:content/cq:template");
		predicateMap.put("1_property.value", "/conf/aem-training-site/settings/wcm/templates/page-content");
		predicateMap.put("orderby", "@jcr:content/cq:lastModified");
		predicateMap.put("orderby.sort", "desc");
		predicateMap.put("p.limit", "10");
		
		Iterator<Resource> resources = queryBuilder.createQuery(PredicateGroup.create(predicateMap),session).getResult().getResources();
		JsonArray pageResourcesJson = new JsonArray();
		while (resources.hasNext()) {
			Resource resource = resources.next();
			JsonObject pageResourceJson = new JsonObject();
			pageResourceJson.addProperty("pagePath", resource.getPath());
			pageResourceJson.addProperty("Resource Name", resource.getName());
			pageResourcesJson.add(pageResourceJson);
		}
		return pageResourcesJson;
	}

}
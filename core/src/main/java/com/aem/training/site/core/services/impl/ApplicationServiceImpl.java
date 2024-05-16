package com.aem.training.site.core.services.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
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

}
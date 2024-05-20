package com.aem.training.site.core.services.impl;

import com.aem.training.site.core.services.DemoService;

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

@Component(service = DemoService.class, immediate = true , name = "DemoServiceImplA")
@Designate(ocd = ApplicationConfiguration.class)
public class DemoServiceImplA implements DemoService {

	@Reference
	private ResourceResolverFactory resolverFactory;
	
	private String name= "DemoServiceImplA";

	@Activate
	@Modified


	@Deactivate
	protected void deactivated() {
		
	}

  @Override
	public String getName() {
		return name;
	}


}
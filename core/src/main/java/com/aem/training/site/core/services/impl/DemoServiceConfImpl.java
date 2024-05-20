package com.aem.training.site.core.services.impl;

import com.aem.training.site.core.services.DemoServiceConf;
import com.aem.training.site.core.services.config.DemoConfiguration;

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

@Component(service = DemoServiceConf.class, immediate = true)
@Designate(ocd=DemoConfiguration.class)
public class DemoServiceConfImpl implements DemoServiceConf {

	@Reference
	private ResourceResolverFactory resolverFactory;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	private String name= "DemoServiceConfImpl";
	private String environmentName;
	private String instanceType;
	private int count;

	@Activate
	@Modified
	protected void activate(final DemoConfiguration config) {
		this.instanceType = config.instancetype();
		this.environmentName = config.environmentname();
		this.count = config.count();
	}

	@Deactivate
	protected void deactivated() {
		
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getEnvironmentName() {
		return environmentName;
	}
	
	@Override
	public String getInstanceType() {
		return instanceType;
	}
	
	@Override
	public int getCount() {
		return count;
	}
}
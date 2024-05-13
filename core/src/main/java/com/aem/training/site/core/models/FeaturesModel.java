package com.aem.training.site.core.models;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.aem.training.site.core.services.ApplicationService;

@Model(adaptables = { Resource.class }, adapters = {
		ComponentExporter.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = "apps/aem-training-site/components/features")
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class FeaturesModel implements ComponentExporter {
	
	@OSGiService
	ApplicationService applicationService;

	private String envName;

	@ValueMapValue
	private String title;
	
	public String getTitle() {
		return title;
	}

	@ChildResource(name = "featureItems")
	List<FeatureItem> featureItems;

	public List<FeatureItem> getFeatureItems() {
		return featureItems;
	}

	public String getEnvName() {
		return envName;
	}

	@PostConstruct
	protected void init() {
		//additional processing
		envName = applicationService.getEnvironmentName();
	}

	@Override
	public String getExportedType() {
		return "apps/aem-training-site/components/features";
	}


}

package com.aem.training.site.core.models;


import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.Self;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;

@Model(adaptables = {SlingHttpServletRequest.class},
        adapters = { ComponentExporter.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = "apps/aem-training-site/components/features")
@Exporter( name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class FeaturesModel implements ComponentExporter {

    @Self
    private SlingHttpServletRequest request;
    
    @ChildResource (name="featureItems")
    List<FeatureItem> featureItems;

    public List<FeatureItem> getFeatureItems() {
        return featureItems;
    }

    @PostConstruct
    protected void init() {
    	injectRequestToFeatureItems(featureItems);
    }

    @Override
    public String getExportedType() {
        return "apps/aem-training-site/components/features";
    }
    
    private void injectRequestToFeatureItems(List<FeatureItem> featureItems) {
		Optional.ofNullable(featureItems).ifPresent(  items -> {
			for(FeatureItem item:items) {
				item.injectRequest(request);
			}
		});
    }

}

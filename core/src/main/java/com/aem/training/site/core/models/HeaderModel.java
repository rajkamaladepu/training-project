package com.aem.training.site.core.models;

import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;

@Model(adaptables = { Resource.class }, adapters = {
		ComponentExporter.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = "apps/aem-training-site/components/fheader")
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class HeaderModel implements ComponentExporter {

	@ChildResource(name = "headerItems")
	List<HeaderItem> headerItems;

	public List<HeaderItem> getHeaderItems() {
		return headerItems;
	}

	@PostConstruct
	protected void init() {
		//additional processing
	}

	@Override
	public String getExportedType() {
		return "apps/aem-training-site/components/fheader";
	}


}
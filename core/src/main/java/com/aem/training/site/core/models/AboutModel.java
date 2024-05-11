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
		ComponentExporter.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = "apps/aem-training-site/components/about")
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class AboutModel implements ComponentExporter {

	@ValueMapValue
	private String title;
	
	public String getTitle() {
		return title;
	}
	
	@ValueMapValue
	private String textArea;
	
	public String getTextArea() {
		return textArea;
	}
	
	@ValueMapValue
	private String imagePath;
	
	public String getImagePath() {
		return imagePath;
	}

//	@ChildResource(name = "featureItems")
//	List<FeatureItem> featureItems;

//	public List<FeatureItem> getFeatureItems() {
//		return featureItems;
//	}

	@PostConstruct
	protected void init() {
		//additional processing
		title+= ": Displyed as H1";
		textArea += ": Displayed as H2";
		imagePath += ": Displayed as H3";
			
	}

	@Override
	public String getExportedType() {
		return "apps/aem-training-site/components/about";
	}


}

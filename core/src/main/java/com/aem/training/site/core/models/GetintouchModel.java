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
		ComponentExporter.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = "apps/aem-training-site/components/getintouch")
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class GetintouchModel implements ComponentExporter {

	@ValueMapValue
	private String title;
	
	public String getTitle() {
		return title;
	}

	@ValueMapValue
	private String name;
	
	public String getName() {
		return name;
	}
	
	
	@ValueMapValue
	private String phonenum;
	
	public String getPhonenum() {
		return phonenum;
	}

	
	@ValueMapValue
	private String email;
	
	public String getEmail() {
		return email;
	}

	
	@ValueMapValue
	private String message;
	
	public String getMessage() {
		return message;
	}

	

	@PostConstruct
	protected void init() {
		
		//additional processing
	}

	@Override
	public String getExportedType() {
		return "apps/aem-training-site/components/getintouch";
	}


}
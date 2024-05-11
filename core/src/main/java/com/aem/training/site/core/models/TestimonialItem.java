package com.aem.training.site.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TestimonialItem {

    @ValueMapValue
    public String name;

    @ValueMapValue
    public String testimonial;

    @ValueMapValue
    public String imagePath;

    public String getName() {
        return name;
    }

	public String getTestimonial() {
		return testimonial;
	}

	public String getImagePath() {
		return imagePath;
	}
    
}

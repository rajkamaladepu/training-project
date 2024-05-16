package com.aem.training.site.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OurTeamItem {

    @ValueMapValue
    public String imagePath;

    @ValueMapValue
    public String name;

    @ValueMapValue
    public String designation;
 
    public String getImagePath() {
        return imagePath;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDesignation() {
        return designation;
    }

    
}

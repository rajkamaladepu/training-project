package com.aem.training.site.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class InfoItem {

    @ValueMapValue
    public String title;

    @ValueMapValue
    public String paragraph;

    @ValueMapValue
    private String linkpage;

    @ValueMapValue
    private String pagename;

    public String getTitle() {
        return title;
    }

    public String getParagraph() {
        return paragraph;
    }

    public String getLinkpage() {
        return linkpage;
    }

    public String getPagename() {
        return pagename;
    }
    
}

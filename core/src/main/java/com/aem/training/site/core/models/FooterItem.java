package com.aem.training.site.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterItem {


    @ValueMapValue
    private String url;
    

    @ValueMapValue
    public String linkTitle;
    //done


    public String getUrl() {
        return url;
    }


    public String getLinkTitle() {
        return linkTitle;
    }
}

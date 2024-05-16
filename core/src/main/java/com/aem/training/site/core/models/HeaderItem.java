package com.aem.training.site.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderItem {

    @ValueMapValue
    public String name;

    @ValueMapValue
    public String url;

    @ValueMapValue
    public String pagename;

    @ValueMapValue
    public String pageurl;


    public String getName() {
        return name;
    }


    public String getUrl() {
        return url;
    }
    
    public String getPagename() {
        return pagename;
    }


    public String getPageurl() {
        return pageurl;
    }
    
}

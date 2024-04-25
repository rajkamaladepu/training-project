package com.aem.training.site.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FeatureItem {

    @ValueMapValue
    public String title;

    @ValueMapValue
    public String paragraph;

    @ValueMapValue
    public String imagePath;

    @ValueMapValue
    public String imageAltText;

    @ValueMapValue
    private Boolean imageDecorative;

    @ValueMapValue
    private String desiredPlacement;

    @ValueMapValue
    private String url;

    @ValueMapValue
    public String linkTitle;

    public String getTitle() {
        return title;
    }

    public Boolean getImageDecorative() {
        return imageDecorative;
    }

    public String getDesiredPlacement(){
        return desiredPlacement;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageAltText() {
        return imageAltText;
    }

    public void setImageAltText(String imageAltText) {
        this.imageAltText = imageAltText;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }
    
}

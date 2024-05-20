package com.aem.training.site.core.models;

import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;

@Model(
    adaptables = Resource.class,
    adapters = ComponentExporter.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
    resourceType = "training-site/components/footer"
)
public class FooterModel implements ComponentExporter {

    @ChildResource(name = "linkItems")
    private List<FooterItem> linkItems;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String titlecomp;

    @ValueMapValue
    private String textarea;

    @ValueMapValue
    private String titleloc;

    @ValueMapValue
    private String titlenews;

    @ValueMapValue
    private String email;

    @ValueMapValue
    private String phnum;

    @ValueMapValue
    private String titleser;

    @ValueMapValue
    private String textareaser;

    @ValueMapValue
    private String fburl;

    @ValueMapValue
    private String xurl;

    @ValueMapValue
    private String linurl;

    @ValueMapValue
    private String insurl;

    public List<FooterItem> getLinkItems() {
        return linkItems;
    }

    public String getTitle() {
        return title;
    }

    public String getTitlecomp() {
        return titlecomp;
    }

    public String getTextarea() {
        return textarea;
    }

    public String getTitleloc() {
        return titleloc;
    }

    public String getTitlenews() {
        return titlenews;
    }

    public String getEmail() {
        return email;
    }

    public String getPhnum() {
        return phnum;
    }

    public String getTitleser() {
        return titleser;
    }

    public String getTextareaser() {
        return textareaser;
    }

    public String getFburl() {
        return fburl;
    }

    public String getXurl() {
        return xurl;
    }

    public String getLinurl() {
        return linurl;
    }

    public String getInsurl() {
        return insurl;
    }

    @PostConstruct
    protected void init() {
        // additional processing if required
    }

    @Override
    public String getExportedType() {
        return "training-site/components/footer";
    }
}

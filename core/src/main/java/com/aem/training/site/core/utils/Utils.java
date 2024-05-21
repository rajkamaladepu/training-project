package com.aem.training.site.core.utils;

import java.util.HashMap;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
    protected static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);
    
    public static ResourceResolver getResolver(ResourceResolverFactory resolverFactory) throws LoginException {
    	LOGGER.info("entry into getResolver method");
        HashMap<String, Object> param = new HashMap<>();
        param.put(ResourceResolverFactory.SUBSERVICE, "sys-user");
        return resolverFactory.getServiceResourceResolver(param);
    }

}

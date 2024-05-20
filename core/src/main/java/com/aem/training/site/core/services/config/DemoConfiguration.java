package com.aem.training.site.core.services.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name = "Demo Configuration")
public @interface DemoConfiguration {

	@AttributeDefinition(
			name = "Environment Name", 
			description = "Environment name of AEM", 
			type = AttributeType.STRING)
    String environmentname() default "Default Env";
	
	@AttributeDefinition(
			name = "Instance Type", 
			description = "Instance type of AEM", 
			type = AttributeType.STRING,
			options = {
		            @Option(label = "Author", value = "author"),
		            @Option(label = "Publish", value = "publish")
		        })
    String instancetype() default "Default Instance";
	
	@AttributeDefinition(
			name = "Count", 
			description = "Casual Count", 
			type = AttributeType.INTEGER )

    int count() default 0;
	
}
package com.aem.training.site.core.services.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name = "Application Configuration")
public @interface ApplicationConfiguration {

	@AttributeDefinition(
			name = "Environment Name", 
			description = "Environment name of AEM", 
			type = AttributeType.STRING)
    String environmentname() default "dev";
	
	@AttributeDefinition(
			name = "Instance Type", 
			description = "Instance type of AEMAsACS", 
			type = AttributeType.STRING,
			options = {
		            @Option(label = "Author", value = "author"),
		            @Option(label = "Publish", value = "publish")
		        })
    String instancetype() default "author";
	
}
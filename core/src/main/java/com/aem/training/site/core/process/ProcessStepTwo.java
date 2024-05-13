package com.aem.training.site.core.process;

import org.apache.commons.lang3.StringUtils;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = WorkflowProcess.class, property = {"process.label=Process Step Two" })
public class ProcessStepTwo implements WorkflowProcess {

    protected final Logger logger = LoggerFactory.getLogger(ProcessStepTwo.class);
    
    
    public void execute(WorkItem workItem, WorkflowSession wfSession, MetaDataMap metaDataMap)
            throws WorkflowException {

    	logger.info("page path =  {}", workItem.getContentPath());
    	
    	
        String passedValue = "";
        if (workItem.getWorkflowData().getMetaDataMap().get("key", String.class) != null) {
        	passedValue = workItem.getWorkflowData().getMetaDataMap().get("key", String.class);
		}
        
        logger.info("key value =  {}", passedValue);
        
        if(metaDataMap.containsKey("PROCESS_ARGS")) {
        	 String arguement = metaDataMap.get("PROCESS_ARGS", "string").toString();
             logger.info("arguement =  {}", arguement);
        }
       
        
        if (StringUtils.isEmpty(passedValue)) {
            throw new WorkflowException("Failed to get page path from payload");
        }
    }
}

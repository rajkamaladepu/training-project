package com.aem.training.site.core.process;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = WorkflowProcess.class, property = {"process.label=Process Step One" })
public class ProcessStepOne implements WorkflowProcess {

    protected final Logger logger = LoggerFactory.getLogger(ProcessStepOne.class);
    
    
    public void execute(WorkItem workItem, WorkflowSession wfSession, MetaDataMap metaDataMap)
            throws WorkflowException {
        workItem.getWorkflowData().getMetaDataMap().put("key", "stringOne update");
    }
}

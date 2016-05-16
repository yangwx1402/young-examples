package com.young.java.examples.pentaho;

import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.libraries.resourceloader.*;

import java.net.URL;

/**
 * Created by Administrator on 2016/5/16.
 */
public class HelloPentaho {
    public void boot() throws ResourceException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL reportDefinitionURL = classLoader.getResource("pentaho-example.prpt");
        ResourceManager resourceManager = new ResourceManager();
        resourceManager.registerDefaults();
        Resource directly = resourceManager.createDirectly(reportDefinitionURL, MasterReport.class);
        MasterReport report = (MasterReport)directly.getResource();
    }
}

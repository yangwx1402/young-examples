package com.young.java.examples.pentaho;

import org.pentaho.reporting.engine.classic.core.ClassicEngineBoot;
import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.engine.classic.core.ReportProcessingException;
import org.pentaho.reporting.engine.classic.core.layout.output.AbstractReportProcessor;
import org.pentaho.reporting.engine.classic.core.modules.output.table.base.StreamReportProcessor;
import org.pentaho.reporting.engine.classic.core.modules.output.table.html.*;
import org.pentaho.reporting.libraries.repository.ContentLocation;
import org.pentaho.reporting.libraries.repository.DefaultNameGenerator;
import org.pentaho.reporting.libraries.repository.stream.StreamRepository;
import org.pentaho.reporting.libraries.resourceloader.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by Administrator on 2016/5/16.
 */
public class HelloPentaho {
    public void boot() throws ResourceException, ReportProcessingException, FileNotFoundException {
        ClassicEngineBoot.getInstance().start();
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL reportDefinitionURL = classLoader.getResource("pentaho-example.prpt");
        ResourceManager resourceManager = new ResourceManager();
        resourceManager.registerDefaults();
        Resource directly = resourceManager.createDirectly(reportDefinitionURL, MasterReport.class);
        MasterReport report = (MasterReport)directly.getResource();

        OutputStream outputStream = new FileOutputStream("E:\\test1.html");

        AbstractReportProcessor reportProcessor = null;

        StreamRepository targetRepository = new StreamRepository(outputStream);
        ContentLocation targetRoot = targetRepository.getRoot();
        HtmlOutputProcessor outputProcessor = new
                StreamHtmlOutputProcessor(report.getConfiguration());
        HtmlPrinter printer = new AllItemsHtmlPrinter(report.getResourceManager());
        printer.setContentWriter(targetRoot, new DefaultNameGenerator(targetRoot, "index", "html"));
        printer.setDataWriter(null, null);
        printer.setUrlRewriter(new FileSystemURLRewriter());
        outputProcessor.setPrinter(printer);
        reportProcessor = new StreamReportProcessor(report, outputProcessor);
        reportProcessor.processReport();

    }

    public static void main(String[] args) throws ResourceException, ReportProcessingException, FileNotFoundException {
        HelloPentaho hello = new HelloPentaho();
        hello.boot();
    }
}

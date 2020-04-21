package com.ags.learn.integrate.springbootbirt.service;

import org.eclipse.birt.report.engine.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.ags.learn.integrate.springbootbirt.config.ReportParameter;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
@Slf4j
public class ReportService {

	@Autowired
	private IReportEngine iReportEngine;

	@Value("${birt.generatedreport.location}")
	private String birtGeneratedReportLocation;

	public String generate(ReportParameter reportParameter) 
			throws Exception{
		String status = "Report generated successfully!!! Report Name: ";
		ByteArrayOutputStream baos = null;
		FileOutputStream fops = null;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm"); 
			LocalDateTime today = LocalDateTime.now();
			String reportName = reportParameter.getReportName() + "-"
					+ formatter.format(today) ;
			if (reportParameter.getFormat().equalsIgnoreCase("pdf")) {
				reportName = reportName + ".pdf";
			}
			if (reportParameter.getFormat().equalsIgnoreCase("xlsx")) {
				reportName = reportName + ".xlsx";
			}

			baos = generateReport(reportParameter);
			fops = new FileOutputStream(birtGeneratedReportLocation 
					+ reportName);
			fops.write(baos.toByteArray());
			status = status.concat(reportName);

		} catch (Exception e) {
			log.info("Exception Occured : " + e.getMessage());
			status = e.getMessage();
		}
		finally {
			fops.close();
			baos.close();
		}

		return status;
	}

	@SuppressWarnings("unchecked")
	private ByteArrayOutputStream generateReport(ReportParameter reportParameter) 
			throws Exception{

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		IReportRunnable runnable = null;
		ClassPathResource cpr=new ClassPathResource("report/"+reportParameter.getReportName()+".rptdesign");
		runnable = iReportEngine
				.openReportDesign(cpr.getInputStream());
		IRunAndRenderTask runAndRenderTask = iReportEngine.createRunAndRenderTask(runnable);
		runAndRenderTask.setParameterValues(setParameters(runnable, reportParameter.getParameter()));
		IRenderOption options =new RenderOption();
		if (reportParameter.getFormat().equalsIgnoreCase("pdf")) {
			PDFRenderOption pdfOptions = new PDFRenderOption(options);
			pdfOptions.setOutputFormat("pdf");
			pdfOptions.setOption(IPDFRenderOption.PAGE_OVERFLOW, IPDFRenderOption.FIT_TO_PAGE_SIZE);
			pdfOptions.setOutputStream(baos);
			runAndRenderTask.setRenderOption(pdfOptions);
		}
		if (reportParameter.getFormat().equalsIgnoreCase("xlsx")) {
			EXCELRenderOption excelRenderOption = new EXCELRenderOption(options);
			excelRenderOption.setOutputFormat("xlsx");
			excelRenderOption.setOption(IPDFRenderOption.PAGE_OVERFLOW, IPDFRenderOption.FIT_TO_PAGE_SIZE);
			excelRenderOption.setOutputStream(baos);
			runAndRenderTask.setRenderOption(excelRenderOption);
		}
		runAndRenderTask.getAppContext().
		put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY,
				this.getClass().getClassLoader());
		runAndRenderTask.run();
		runAndRenderTask.close();
		return baos;

	}

	@SuppressWarnings("unchecked")
	protected HashMap<String, Object> setParameters(IReportRunnable report, Map<String,Object> m) throws Exception {

		HashMap<String, Object> parms = new HashMap<String, Object>();

		IGetParameterDefinitionTask task = iReportEngine.createGetParameterDefinitionTask(report);
		//Get all parameter definitions in birt
		Collection<IParameterDefnBase> params = task.getParameterDefns(true);
		Iterator<IParameterDefnBase> iterator = params.iterator();
		while (iterator.hasNext()) {
			IParameterDefnBase param = (IParameterDefnBase) iterator.next();
			Object val=m.get(param.getName());
			//If the parameter of the birt is defined
			if (val!=null) {
				parms.put(param.getName(),val);
			}
		}
		task.close();
		return parms;
	}

}

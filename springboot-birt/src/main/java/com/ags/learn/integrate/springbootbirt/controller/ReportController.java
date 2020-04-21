package com.ags.learn.integrate.springbootbirt.controller;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ags.learn.integrate.springbootbirt.config.ReportParameter;
import com.ags.learn.integrate.springbootbirt.service.ReportService;

@RestController
public class ReportController {

	@Autowired
	ReportService reportService;

	//Generate report using data from .java file
	@GetMapping("/report/car/{searchBy}/{seachValue}")
	public String carReport(
			@PathVariable("searchBy") String searchBy,
			@PathVariable("seachValue") String searchValue)
					throws Exception{
		ReportParameter reportParameter = new ReportParameter("car","PDF");
		reportParameter.setParameter(searchBy, searchValue);
		return reportService.generate(reportParameter);
	}

	//Generate report using data from .json file
	//Integrating sample report found over internet
	@GetMapping("/report/user")
	public String userReport() throws Exception{
		ReportParameter reportParameter = new ReportParameter("user","PDF");
		return reportService.generate(reportParameter);
	}

	//Generate report connecting to database
	@GetMapping("/report/order")
	public String orderReport() throws Exception{
		ReportParameter reportParameter = new ReportParameter("order","PDF");
		return reportService.generate(reportParameter);
	}

	//Generate report connecting to database using parameters fromShipmentDate and toShipmentDate
	@GetMapping("/report/order/byDate/{fromDate}/{toDate}")
	public String orderReport(
			@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate)
					throws Exception{
		ReportParameter reportParameter = new ReportParameter("ordersshippingdate","PDF");
		reportParameter.setParameter("fromShipmentDate", convertDate(fromDate));
		reportParameter.setParameter("toShipmentDate", convertDate(toDate));
		return reportService.generate(reportParameter);
	}

	//Generate report connecting to database using parameters
	@GetMapping("/report/order/{customerNumber}")
	public String orderReportForCustomer(
			@PathVariable("customerNumber") String searchValue)
					throws Exception{
		ReportParameter reportParameter = new ReportParameter("orderforcustomer","xlsx");
		reportParameter.setParameter("customerNumber", Integer.valueOf(searchValue));
		return reportService.generate(reportParameter);
	}

	private Date convertDate(String str) throws Exception{
		Date utilDate =  new SimpleDateFormat("dd-MM-yyyy").parse(str); 
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}
}

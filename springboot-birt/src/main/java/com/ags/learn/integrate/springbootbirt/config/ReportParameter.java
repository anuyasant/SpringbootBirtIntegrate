package com.ags.learn.integrate.springbootbirt.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Configuration
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportParameter {
	private String reportName;
	private String format;
	private Map<String,Object> parameter=null;
	
	public ReportParameter(String reportName,String format) {
		this.reportName = reportName;
		this.format = format;
		parameter=new HashMap<String,Object>();
	}
	
	public void setParameter(String key,Object val){
		this.parameter.put(key, val);
	}
	
	public Object getParameter(String key){
		return this.parameter.get(key);
	}
	
	public Map<String,Object> getParameter(){
		return this.parameter;
	}

	@Override
	public String toString() {
		return "ReportParameter [reportName=" + reportName + ", format=" + format + ", parameter=" + parameter + "]";
	}
	
	
	

}

package com.project.report.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.project.report.exception.ReportException;
import com.project.report.repository.StoreRepo;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class ReportService {
	
	
    @Value("${report.template-path}")
    private String reportTemplatePath;
	
	
	private final DataSource dataSource;
	
	@Autowired
	private StoreRepo storeRepo;
	
    public ReportService(DataSource dataSource) {
        this.dataSource = dataSource;
    }
		
	public byte[] generateReport(String name, String city) throws Exception {
        String templatePath = "";// Load the .JRXML file from the resources folder
        JREmptyDataSource dataSource = new JREmptyDataSource(); // without database connection in jasper report      
        Map<String, Object> parameters = new HashMap<>();		// Parameters map (pass data to the report)
        parameters.put("name", name);
        parameters.put("city", city);
       
        JasperReport report = JasperCompileManager.compileReport(templatePath);
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);// Fill the report  
        return JasperExportManager.exportReportToPdf(jasperPrint);// Export the report to a PDF
    }
	
	
	
	public byte[] generateReportData() throws Exception {
        String templatePath = "";      
        Map<String, Object> parameters = new HashMap<>(); //without parameter
        JasperReport report = JasperCompileManager.compileReport(templatePath);
        try (Connection connection = dataSource.getConnection()) {//with db connection in jasper report
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, connection);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to generate report", e);
        }
    }
	
	public byte[] generateStorePdfData(String userName,String userCity,String cityNameForQuery) throws Exception {
		List<String> storeName = storeRepo.findStoreNameByCityName(cityNameForQuery);
		if(ObjectUtils.isEmpty(userName)) {
			throw new ReportException("User name cannot be empty !!");
		}
		if(ObjectUtils.isEmpty(userCity)) {
			throw new ReportException("User City cannot be empty !!");
		}
		if(ObjectUtils.isEmpty(storeName)) {
			throw new ReportException("Store from this city name not found!!");
		}
        Map<String, Object> parameters = new HashMap<>(); 
        parameters.put("userName", userName);
        parameters.put("userCity", userCity);
        parameters.put("cityName", cityNameForQuery);
        JasperReport report = JasperCompileManager.compileReport(reportTemplatePath);
        try (Connection connection = dataSource.getConnection()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, connection);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to generate report", e);
        }
    }

}

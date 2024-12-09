package com.project.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.report.service.ReportService;

@RestController
@RequestMapping("v1/report")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/generate")
	public ResponseEntity<byte[]> generateReport(@RequestParam(required = true) String name,@RequestParam(required = true) String city) {
        try {
            byte[] pdfData = reportService.generateReport(name,city);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("inline")
                    .filename("report.pdf").build());

            return ResponseEntity.ok().headers(headers).body(pdfData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
	
	@GetMapping("/generatePdf")
	public ResponseEntity<byte[]> generateReportData() {
        try {
            byte[] pdfData = reportService.generateReportData();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("inline")
                    .filename("report.pdf").build());

            return ResponseEntity.ok().headers(headers).body(pdfData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
	
	
	@GetMapping("/generateStorePdfData")
	public ResponseEntity<byte[]> generateStorePdfData(@RequestParam(required = true) String userName,@RequestParam(required = true) String userCity,@RequestParam(required = true) String cityNameForQuery) throws Exception {
            byte[] pdfData = reportService.generateStorePdfData(userName,userCity,cityNameForQuery);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("inline")
                    .filename("report.pdf").build());

            return ResponseEntity.ok().headers(headers).body(pdfData);

    }
	
	

}

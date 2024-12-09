package com.project.report.exception;

@SuppressWarnings("serial")
public class ReportException extends RuntimeException{
	
	public ReportException() {
		super();
	}

	public ReportException(String message) {
		super(message);
	}

	public ReportException(String message, Throwable e) {
		super(message, e);
	}


}

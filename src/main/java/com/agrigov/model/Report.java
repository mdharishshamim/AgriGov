package com.agrigov.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class Report {
	@Id
	@Column(name = "Report ID", nullable = false, length = 50)
	@NotBlank(message = "Report ID is required")
	private int reportId;

	@Column(name = "Scope", nullable = false, length = 50)
	@NotBlank(message = "Scope Type is required")
	private String scope;
	
	@Column(name = "Metrics", nullable = false, length = 50)
	@NotBlank(message = "Metrics  is required")
	private String metrics;
	
	@Column(name = "Date", nullable = false, length = 50)
	@NotBlank(message = "Audit Date is required")
	private LocalDate date;
	
}

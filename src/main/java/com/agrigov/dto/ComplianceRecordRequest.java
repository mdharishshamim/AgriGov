package com.agrigov.dto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplianceRecordRequest {

	@NotBlank(message = "Compliance ID is required")
	private Integer complianceId;

	@Column(name = "entityID", nullable = false)
	@NotBlank(message = "Entity ID is required")
	private Integer entityID;

	@Column(name = "Type", nullable = false, length = 50)
	@NotBlank(message = "Entity Type is required")
	private String type;
	
	@Column(name = "Result", nullable = false, length = 50)
	@NotBlank(message = "Compliance Result is required")
	private String result;

	@Column(name = "Notes", nullable = false, length = 50)
	@NotBlank(message = "Compliance Notes is required")
	private String notes;
	
	@Column(name = "Date", nullable = false, length = 50)
	@NotBlank(message = "Compliance Date is required")
	private LocalDate date;


	private Integer schemeId;
	private Integer subsidyId;
	private Integer projectId;
}

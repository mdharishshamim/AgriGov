package com.agrigov.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplianceRecordResponse {
	
	private Integer complianceId;
	private Integer entityID;
	private String type;
	private String result;
	private String notes;
	private LocalDate date;

	private Integer schemeId;
	private Integer subsidyId;
	private Integer projectId;
}

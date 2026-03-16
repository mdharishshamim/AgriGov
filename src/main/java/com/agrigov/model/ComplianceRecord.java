package com.agrigov.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "Compliance Record")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class ComplianceRecord {

	@Id
	@Column(name = "complianceno", nullable = false)
	@NotBlank(message = "Compliance ID is required")
	private int complianceId;

	@Column(name = "entityID", nullable = false)
	@NotBlank(message = "Entity ID is required")
	private int entityID;

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
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "policyschemeId")
	private PolicyScheme policyScheme;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "subsidyId")
	private Subsidy subsidy;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "ruralprojectId")
	private RuralProject ruralProject;
	

	}




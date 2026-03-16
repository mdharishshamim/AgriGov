package com.agrigov.model;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Audit")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Audit {
	@Id
	@Column(name = "Audit ID", nullable = false)
	@NotBlank(message = "Audit ID is required")
	private int auditId;

	@ManyToOne
	@JoinColumn(name = "Officer ID")
	private User userId;
	

	@Column(name = "Scope", nullable = false, length = 50)
	@NotBlank(message = "Scope Type is required")
	private String scope;
	
	@Column(name = "Findings", nullable = false, length = 50)
	@NotBlank(message = "Findings Result is required")
	private String findings;
	
	@Column(name = "Date", nullable = false, length = 50)
	@NotBlank(message = "Audit Date is required")
	private LocalDate date;
	
	@Column(name = "Status", nullable = false, length = 50)
	@NotBlank(message = "Audit Status is required")
	private String status;
}

package com.agrigov.model;
import java.sql.Timestamp;
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
@Table(name = "AuditLog")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class AuditLog {
	@Id
	@Column(name = "Audit ID", nullable = false)
	@NotBlank(message = "Audit ID is required")
	private int auditLogId;

	@ManyToOne
	@JoinColumn(name = "User ID")
	private User userId;
	

	@Column(name = "Action", nullable = false, length = 50)
	@NotBlank(message = "Entity Type is required")
	private String action;
	
	@Column(name = "Resource", nullable = false, length = 50)
	@NotBlank(message = "Compliance Result is required")
	private String resource;

	@Column(name = "TimeStamp")
	@NotBlank(message = "Compliance Notes is required")
	private Timestamp timestamp;
	
	
	
}

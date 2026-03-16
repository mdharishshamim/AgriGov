package com.agrigov.model;
 
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Table(name = "FarmerDocument")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmerDocument {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long documentId;
 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "farmerId", nullable = false)
	private Farmer farmer;
 
	@Column(nullable = false, length = 50)
	private String docType; // e.g. IDProof, LandRecord
 
	@Column(nullable = false, length = 255)
	private String fileUri;
 
	@Column(nullable = false)
	private LocalDate uploadedDate;
 
	@Column(length = 20)
	private String verificationStatus;

	
}
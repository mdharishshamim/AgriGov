package com.agrigov.model;
 
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Table(name = "Farmer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Farmer {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long farmerId;
 
	@Column(nullable = false, length = 100)
	private String name;
 
	@Column(nullable = false)
	private LocalDate dob;
 
	@Column(length = 10)
	private String gender;
 
	@Column(length = 255)
	private String address;
 
	@Column(length = 50)
	private String contactInfo;
 
	@Column(length = 255)
	private String landDetails;
 
	@Column(length = 20)
	private String status;
 
	@OneToMany(mappedBy = "farmer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FarmerDocument> documents;
 
	// Getters and Setters
	
}
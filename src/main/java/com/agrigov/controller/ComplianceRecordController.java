package com.agrigov.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrigov.dto.ComplianceRecordRequest;
import com.agrigov.dto.ComplianceRecordResponse;
import com.agrigov.service.ComplianceRecordService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/compliance-records")
public class ComplianceRecordController {
	private final ComplianceRecordService complianceRecordService;

	public ComplianceRecordController(ComplianceRecordService complianceRecordService) {
		this.complianceRecordService = complianceRecordService;
	}
 
	// -----------------------------
	// CRUD
	// -----------------------------
 
	@PostMapping("/save") // http://localhost:1234/api/rural-projects/save
	public ResponseEntity<ComplianceRecordResponse> create(@Valid @RequestBody ComplianceRecordRequest request) {
		return ResponseEntity.ok(complianceRecordService.create(request));
	}
 
	@GetMapping("fetchById/{id}")
	public ResponseEntity<ComplianceRecordResponse> get(@PathVariable Integer id) {
		return ResponseEntity.ok(complianceRecordService.get(id));
	}
 
	@GetMapping("fetchAll")
	public ResponseEntity<List<ComplianceRecordResponse>> all() {
		return ResponseEntity.ok(complianceRecordService.getAll());
	}
 
	@PutMapping("updateCompliance/{id}")
	public ResponseEntity<ComplianceRecordResponse> update(@PathVariable Integer id,
			@Valid @RequestBody ComplianceRecordRequest request) {
		return ResponseEntity.ok(complianceRecordService.update(id, request));
	}
 
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		complianceRecordService.delete(id);
		return ResponseEntity.noContent().build();
	}
	

	
}

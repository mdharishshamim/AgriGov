package com.agrigov.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agrigov.dto.ComplianceRecordRequest;
import com.agrigov.dto.ComplianceRecordResponse;
import com.agrigov.model.ComplianceRecord;
import com.agrigov.repository.ComplianceRecordRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComplianceRecordServiceImpl implements ComplianceRecordService {

	@Override
	public ComplianceRecordResponse create(ComplianceRecordRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplianceRecordResponse update(Integer complianceId, ComplianceRecordRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplianceRecordResponse get(Integer complianceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ComplianceRecordResponse> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer complianceId) {
		// TODO Auto-generated method stub
		
	}
	
	
}
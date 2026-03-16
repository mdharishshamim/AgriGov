package com.agrigov.service;

import java.util.List;

import com.agrigov.dto.ComplianceRecordRequest;
import com.agrigov.dto.ComplianceRecordResponse;

public interface ComplianceRecordService {
	ComplianceRecordResponse create(ComplianceRecordRequest request);
    ComplianceRecordResponse update(Integer complianceId, ComplianceRecordRequest request);
    ComplianceRecordResponse get(Integer complianceId);
    List<ComplianceRecordResponse> getAll();
    void delete(Integer complianceId);
}
package com.agrigov.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agrigov.dto.ComplianceRecordRequest;
import com.agrigov.dto.ComplianceRecordResponse;
import com.agrigov.exception.ResourceNotFoundException;
import com.agrigov.model.ComplianceRecord;
import com.agrigov.model.PolicyScheme;
import com.agrigov.model.Subsidy;
import com.agrigov.model.RuralProject;
import com.agrigov.repository.ComplianceRecordRepository;
import com.agrigov.repository.PolicySchemeRepository;
import com.agrigov.repository.SubsidyRepository;
import com.agrigov.repository.RuralProjectRepository;

@Service
@Transactional(readOnly = true) // default: read-only for queries
public class ComplianceRecordServiceImpl implements ComplianceRecordService {

    private final ComplianceRecordRepository complianceRecordRepository;
    private final PolicySchemeRepository policySchemeRepository;
    private final SubsidyRepository subsidyRepository;
    private final RuralProjectRepository ruralProjectRepository;

    public ComplianceRecordServiceImpl(ComplianceRecordRepository complianceRecordRepository,
                                       PolicySchemeRepository policySchemeRepository,
                                       SubsidyRepository subsidyRepository,
                                       RuralProjectRepository ruralProjectRepository) {
        this.complianceRecordRepository = complianceRecordRepository;
        this.policySchemeRepository = policySchemeRepository;
        this.subsidyRepository = subsidyRepository;
        this.ruralProjectRepository = ruralProjectRepository;
    }

    // -----------------------------
    // CRUD (return DTOs)
    // -----------------------------

    @Override
    @Transactional
    public ComplianceRecordResponse create(ComplianceRecordRequest request) {
        ComplianceRecord cr = new ComplianceRecord();
        apply(cr, request); // map DTO -> entity
        cr = complianceRecordRepository.save(cr);
        return toResponse(cr);
    }

    @Override
    @Transactional
    public ComplianceRecordResponse update(Integer complianceId, ComplianceRecordRequest request) {
        ComplianceRecord cr = complianceRecordRepository.findById(complianceId)
            .orElseThrow(() -> new ResourceNotFoundException("ComplianceRecord not found: " + complianceId));

        // Normalize/lock the ID to the path variable (same policy as reference)
        if (request.getComplianceId() != null && request.getComplianceId() != cr.getComplianceId()) {
            request.setComplianceId(cr.getComplianceId());
        }

        apply(cr, request);
        cr = complianceRecordRepository.save(cr);
        return toResponse(cr);
    }

    @Override
    public ComplianceRecordResponse get(Integer complianceId) {
        ComplianceRecord cr = complianceRecordRepository.findById(complianceId)
            .orElseThrow(() -> new ResourceNotFoundException("ComplianceRecord not found: " + complianceId));
        return toResponse(cr);
    }

    @Override
    public List<ComplianceRecordResponse> getAll() {
        return complianceRecordRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Integer complianceId) {
        ComplianceRecord cr = complianceRecordRepository.findById(complianceId)
            .orElseThrow(() -> new ResourceNotFoundException("ComplianceRecord not found: " + complianceId));
        complianceRecordRepository.delete(cr);
        // Note: PolicyScheme/Subsidy/RuralProject referenced via @OneToOne with cascade=ALL, orphanRemoval=true
        // will be removed as per your entity mapping ownership.
    }

    // -----------------------------
    // Mapping helpers
    // -----------------------------

    /** Map flattened ComplianceRecordRequest -> ComplianceRecord entity */
    private void apply(ComplianceRecord cr, ComplianceRecordRequest req) {
        // If complianceId is auto-generated in DB, do not set it here.
        if (req.getComplianceId() != null) {
            cr.setComplianceId(req.getComplianceId());
        }

        cr.setEntityID(req.getEntityID());
        cr.setType(req.getType());
        cr.setResult(req.getResult());
        cr.setNotes(req.getNotes());
        cr.setDate(req.getDate()); // assuming LocalDate in DTO

        // PolicyScheme by ID
//        if (req.getSchemeID() != null) {
//            PolicyScheme ps = policySchemeRepository.findById(req.getSchemeID())
//                    .orElseThrow(() -> new ResourceNotFoundException("PolicyScheme not found: " + req.getPolicySchemeId()));
//            cr.setPolicyScheme(ps);
//        } else {
//            // policy: keep as-is when null (do not clear)
//        }

//        // Subsidy by ID
//        if (req.getSubsidyId() != null) {
//            Subsidy s = subsidyRepository.findById(req.getSubsidyId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Subsidy not found: " + req.getSubsidyId()));
//            cr.setSubsidy(s);
//        } else {
//            // policy: keep as-is when null (do not clear)
//        }
//
//        // RuralProject by ID
//        if (req.getProjectId() != null) {
//            RuralProject rp = ruralProjectRepository.findById(req.getProjectId())
//                    .orElseThrow(() -> new ResourceNotFoundException("RuralProject not found: " + req.getRuralProjectId()));
//            cr.setRuralProject(rp);
//        } else {
//            // policy: keep as-is when null (do not clear)
//        }
    }

    /** Map ComplianceRecord entity -> ComplianceRecordResponse DTO (prevents recursion in JSON) */
    private ComplianceRecordResponse toResponse(ComplianceRecord cr) {
        ComplianceRecordResponse r = new ComplianceRecordResponse();
        r.setComplianceId(cr.getComplianceId());
        r.setEntityID(cr.getEntityID());
        r.setType(cr.getType());
        r.setResult(cr.getResult());
        r.setNotes(cr.getNotes());
        r.setDate(cr.getDate());

        if (cr.getPolicyScheme() != null) {
            r.setSchemeId(cr.getPolicyScheme().getSchemeID());
            // If your response has extra fields (e.g., scheme name), set them here
            // r.setPolicySchemeName(cr.getPolicyScheme().getName());
        }
        if (cr.getSubsidy() != null) {
            r.setSubsidyId(cr.getSubsidy().getSubsidyId());
            // r.setSubsidyName(cr.getSubsidy().getName());
        }
        if (cr.getRuralProject() != null) {
            r.setProjectId(cr.getRuralProject().getProjectId());
            // r.setRuralProjectName(cr.getRuralProject().getProjectName());
        }
        return r;
    }

	
}
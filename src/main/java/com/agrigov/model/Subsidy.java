package com.agrigov.model;
 
import java.math.BigDecimal;
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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Table(name = "subsidies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subsidy {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subsidy_id")
    private Long subsidyId;
 
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "scheme_id", nullable = false)
    private PolicyScheme scheme;
 
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "farmer_id", nullable = false)
    private Farmer farmer;
 
    @Column(name = "amount", nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;
 
    @Column(name = "date", nullable = false)
    private LocalDate date;
 
    @Column(name = "status", length = 40, nullable = false)
    private String status;
}
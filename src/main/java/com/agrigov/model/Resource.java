package com.agrigov.model;
 
import java.math.BigDecimal;

import com.agrigov.enums.ResourceType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity

@Table(name = "resource")

@Data

@NoArgsConstructor

@AllArgsConstructor

public class Resource {
 
    @Id

//    @GeneratedValue(strategy = GenerationType.IDENTITY) // Remove if you set IDs manually

    @Column(name = "resource_id")

    private Long resourceId;
 
    /**

     * Owning side of the relationship: many resources belong to one project.

     */

    @NotNull(message = "Project is required")

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "project_id", nullable = false)

    private RuralProject project;
 
    /**

     * FUNDS -> monetary

     * MATERIALS -> physical items/stock

     */

    @NotNull(message = "Type is required")

    @Enumerated(EnumType.STRING)

    @Column(name = "type", nullable = false, length = 20)

    private ResourceType type;
 
    /**

     * For FUNDS, treat as currency (use scale=2).

     * For MATERIALS, you can decide the unit (e.g., pieces/kg); keep as BigDecimal for flexibility.

     */

    @NotNull(message = "Quantity is required")

    @DecimalMin(value = "0.00", inclusive = true, message = "Quantity cannot be negative")

    @Column(name = "quantity", nullable = false, precision = 18, scale = 2)

    private BigDecimal quantity;
 
    /**

     * Suggested values: PLANNED, ALLOCATED, UTILIZED, ON_HOLD, CANCELLED

     * You can convert to an enum if preferred.

     */

    @NotNull(message = "Status is required")

    @Pattern(

        regexp = "PLANNED|ALLOCATED|UTILIZED|ON_HOLD|CANCELLED",

        message = "Status must be one of: PLANNED, ALLOCATED, UTILIZED, ON_HOLD, CANCELLED"

    )

    @Column(name = "status", nullable = false, length = 20)

    private String status;
 
    // Optional: helper constructor without ID (useful when ID is generated)

    public Resource(RuralProject project, ResourceType type, BigDecimal quantity, String status) {

        this.project = project;

        this.type = type;

        this.quantity = quantity;

        this.status = status;

    }
 
    // If you prefer to store only the projectId instead of an association:

    // @Column(name = "project_id", insertable = false, updatable = false)

    // private Long projectId;

    // And keep the @ManyToOne as is (JPA will manage the FK) or remove the relation entirely if you want a flat model.

}

 
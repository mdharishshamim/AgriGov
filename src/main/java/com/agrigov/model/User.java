
package com.agrigov.model;

import com.agrigov.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private String UserId;

    @Column(name = "name", nullable = false)
    private String Name;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role Role;

    @Column(name = "email", nullable = false)
    private String Email;

    @Column(name = "phone_no", nullable = false)
    private long Phone;

    @Column(name = "status", nullable = false)
    private boolean Status;
}
 
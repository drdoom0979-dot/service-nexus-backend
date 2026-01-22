package com.drdoom.backend.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import java.util.List;
import com.drdoom.backend.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Spring Boot 3+ maneja esto nativamente
    @Column(name = "id", updatable = false, nullable = false)
    private java.util.UUID id;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private  String password;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    private Boolean isActive = true;

    private String timezone;

    private java.time.LocalDateTime createAt = java.time.LocalDateTime.now();

    private java.time.LocalDateTime last_connection;

    private String verificationCode;  // código OTP temporal

    private Boolean isVerified = false; // teléfono confirmado

    private String lastLoginIP;

    private String deviceId;


}

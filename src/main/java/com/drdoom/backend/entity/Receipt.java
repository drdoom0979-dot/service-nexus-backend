package com.drdoom.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "receipts")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String merchantName;
    private Double totalAmount;

    // Aquí guardarás la ruta en tu VPS (ej: /uploads/tickets/abc.jpg)
    private String localFilePath;

    private LocalDateTime purchaseDate;

    // Relación inversa: Muchos recibos pertenecen a UN usuario
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
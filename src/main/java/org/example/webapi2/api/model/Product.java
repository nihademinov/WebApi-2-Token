package org.example.webapi2.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Product")
public class Product  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductId")
    private Long productId;

    @CreationTimestamp
    @Column(name = "CreatedAt", updatable = false)
    private LocalTime createdAt;

    @UpdateTimestamp
    @Column(name = "UpdatedAt")
    private LocalTime updatedAt;

    @Column(name = "DeletedAt")
    private LocalTime deletedAt;

    @Column(name = "ProductName")
    @NotBlank
    private String productName;

    @Column(name = "Description")
    @NotBlank
    private String description;

    @Column(name = "Price")
    @NotNull
    private Double price;

    @Column(name = "Quantity")
    @NotNull
    private Integer quantity;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "CategoryId", nullable = false)
    private Category category;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "UserId")
    private User user;
}

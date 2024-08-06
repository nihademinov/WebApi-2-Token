package org.example.webapi2.api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Roles")
public class Role  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RolesId")
    private Long id;

    @Column(name = "Name")
    @NotBlank
    private String name;

    @CreationTimestamp
    @Column(name = "CreatedAt", updatable = false)
    private LocalTime createdAt;

    @Column(name = "UpdatedAt")
    @UpdateTimestamp
    private LocalTime updatedAt;

    @Column(name = "DeletedAt")
    private LocalTime deletedAt;

}


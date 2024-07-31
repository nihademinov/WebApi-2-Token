package org.example.webapi2.api.model;


import jakarta.persistence.*;
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
@Table(name = "Contact")
@NoArgsConstructor
@AllArgsConstructor
public class Contact  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContactId")
    private Long contactId;

    @CreationTimestamp
    @Column(name = "CreatedAt", updatable = false)
    private LocalTime createdAt;

    @UpdateTimestamp
    @Column(name = "UpdatedAt")
    private LocalTime updatedAt;
    @Column(name = "DeletedAt")
    private LocalTime deletedAt;

    @Column(name = "Address")
    private String address;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "UserId", nullable = false)
    private User user;
}

package org.example.webapi2.api.model;


import jakarta.persistence.*;
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
@Table(name = "Category")
//fixme postgres in default naming strategysi var adlandirmani
// camel case ede bilmek ucun strategu ni deyismek lazimdir
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryId")
    private Long categoryId;

    @CreationTimestamp
    @Column(name = "CreatedAt", updatable = false)
    private LocalTime createdAt;

    @UpdateTimestamp
    @Column(name = "UpdatedAt")
    private LocalTime updatedAt;
    @Column(name = "DeletedAt")
    private LocalTime deletedAt;
    //fixme Naming stategy ni column name lere de tetbiq etmek lazimdir,
    // bizim case de adeten 'camel case' istifade olunur

    @Column(name = "CategoryName")
    private String categoryName;

    @OneToMany(mappedBy = "category",cascade = {CascadeType.PERSIST}, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();
}

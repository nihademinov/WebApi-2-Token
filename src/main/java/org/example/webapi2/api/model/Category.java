package org.example.webapi2.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Category")
public class Category extends  SuperEntity{

    private String categoryName;

    @OneToMany(mappedBy = "category",cascade = {CascadeType.PERSIST}, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();
}

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
//fixme postgres in default naming strategysi var adlandirmani
// camel case ede bilmek ucun strategu ni deyismek lazimdir
public class Category extends  SuperEntity{

    //fixme Naming stategy ni column name lere de tetbiq etmek lazimdir,
    // bizim case de adeten 'camel case' istifade olunur

    private String categoryName;

    @OneToMany(mappedBy = "category",cascade = {CascadeType.PERSIST}, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();
}

package org.example.webapi2.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.webapi2.api.model.Category;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String productName;
    private String description;
    private double price;
    private int quantity;

    private Category category;

}

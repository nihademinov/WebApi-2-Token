package org.example.webapi2.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Product")


public class Product extends SuperEntity {

    private String productName;
    private String description;
    private double price;
    private int quantity;


    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;
}

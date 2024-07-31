package org.example.webapi2.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String productName;

    //fixme primitiv tipler istifade etmek bezi problemlere yol aca biler,
    // bu halda qiymeti teyin olunmayan product double un default deyeri oldugu ucun 0.0 olaraq teyin olunacaq
    // Double olmalidir ki null oldugu nezere alinsin, eks teqdirde 0 olub olmamagi her defe check olunmalidir.

    private Double price;

    // quantity de buna daxildir, her hansi map xetasi oldugu teqdirde product olmasina baxmayaraq map olunmadigi ucun
    // null yox 0 olaraq gorunecek
    private int quantity;

    private int categoryNum;

}

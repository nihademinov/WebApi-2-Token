package org.example.webapi2.api.dto.ResponseDto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @NotBlank
    private String productName;

    @NotBlank
    private String description;

    @NotBlank
    private Double price;

    @NotNull
    private Integer quantity;

    @NotNull
    private Long categoryNum;

}

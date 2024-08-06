package org.example.webapi2.api.controller;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.webapi2.api.dto.ResponseDto.ProductDto;
import org.example.webapi2.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("{productId}")
    public ProductDto getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public String createProduct(@RequestBody ProductDto productDto) {
        return  productService.createProduct(productDto);

    }

    @PutMapping("{productId}")
    public String updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto) {
        return productService.updateProduct(productId, productDto);
    }

    @DeleteMapping("{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return "Product successfully deleted";
    }
}

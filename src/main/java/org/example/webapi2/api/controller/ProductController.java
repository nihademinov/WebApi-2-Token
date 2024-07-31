package org.example.webapi2.api.controller;


import lombok.RequiredArgsConstructor;
import org.example.webapi2.api.dto.ProductDto;
import org.example.webapi2.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    @PreAuthorize("hasRole('ADMIN')")

    //fixme GET /api/products GET bize list of product vermelidir
    @GetMapping()
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @PreAuthorize("hasRole('ADMIN')")
    //fixme GET /api/products/{productId} bize specific product return etmelidir
    @GetMapping("{productId}")
    public ProductDto getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    //fixme POST /api/products yeni product yaratmage kifayet etmelidir
    @PostMapping()
    public String createProduct(@RequestBody ProductDto productDto) {
        productService.createProduct(productDto);
        return"Product successfully created";
    }

    @PreAuthorize("hasRole('ADMIN')")
    //fixme PUT /api/products/{productId} bize movcud product u update etmeye kifayet etmelidir
    @PutMapping("{productId}")
    public String updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto) {
        return productService.updateProduct(productId, productDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    //fixme DEL /api/products/{productId} bize movcud productu silmeye kifayet etmelidir
    @DeleteMapping("{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return "Product successfully deleted";
    }
}

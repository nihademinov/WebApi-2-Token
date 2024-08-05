package org.example.webapi2.service;


import org.example.webapi2.api.bussines.management.CategoryManager;
import org.example.webapi2.api.bussines.management.ProductManager;
import org.example.webapi2.api.dto.ResponseDto.ProductDto;
import org.example.webapi2.api.model.Category;
import org.example.webapi2.api.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


    private final CategoryManager categoryManager;
    private final ProductManager productManager;

    private final ModelMapper modelMapper = new ModelMapper();

    public ProductService(CategoryManager categoryManager, ProductManager productManager) {
        this.categoryManager = categoryManager;
        this.productManager = productManager;
    }

    public List<ProductDto> getAllProducts() {
        return productManager.getAllProducts();
    }

    public ProductDto getProductById(Long id) {

        return productManager.getProductById(id);
    }

    public void createProduct(ProductDto productDto) {

        Category checkCategory = categoryManager.getCategory(productDto.getCategoryNum());
        Product product = modelMapper.map(productDto, Product.class);

        if (checkCategory!=null) {
            product.setCategory(checkCategory);
            product.setUser(null);
        }
        productManager.saveProduct(product);
    }

    public String updateProduct(Long id, ProductDto productDto) {
        return productManager.updateProduct(id,productDto);
    }

    public void deleteProductById(Long userId) {
        Product product = productManager.getProduct(userId);
        productManager.deleteProductById(product.getProductId());

    }
}

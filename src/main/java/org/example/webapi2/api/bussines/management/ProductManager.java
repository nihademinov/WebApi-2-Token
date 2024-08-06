package org.example.webapi2.api.bussines.management;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.webapi2.api.dto.ResponseDto.ProductDto;
import org.example.webapi2.api.model.Category;
import org.example.webapi2.api.model.Product;
import org.example.webapi2.exceptionHandler.AlreadyExistsException;
import org.example.webapi2.exceptionHandler.NotFoundException;
import org.example.webapi2.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductManager {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryManager categoryManager;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(long id) {
        return modelMapper.map(getProduct(id), ProductDto.class);
    }

    @Transactional
    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    private boolean existsProduct(String productName) {
        return productRepository.findByProductName(productName).isPresent();
    }

    public String createProduct(ProductDto productDto) {

        if (existsProduct(productDto.getProductName())) {
            throw new AlreadyExistsException("Product already exists");
        }
        Category checkCategory = categoryManager.getCategory(productDto.getCategoryNum());
        Product product = modelMapper.map(productDto, Product.class);

        if (checkCategory != null) {
            product.setCategory(checkCategory);
            product.setUser(null);
        }
        saveProduct(product);
        return "Product successfully created";
    }


    public String updateProduct(Long id, ProductDto productDto) {
        Product product = getProduct(id);

        modelMapper.map(productDto, product);
        productRepository.save(product);
        return "Product updated";
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
    }
}

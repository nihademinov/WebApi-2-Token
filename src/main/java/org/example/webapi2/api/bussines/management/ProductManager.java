package org.example.webapi2.api.bussines.management;


import lombok.RequiredArgsConstructor;
import org.example.webapi2.exceptionHandler.NotFoundException;
import org.example.webapi2.api.dto.ResponseDto.ProductDto;
import org.example.webapi2.api.model.Product;
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

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(long id) {
        return modelMapper.map(getProduct(id), ProductDto.class);
    }

    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
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

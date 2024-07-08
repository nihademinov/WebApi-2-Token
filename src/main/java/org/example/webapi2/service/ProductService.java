package org.example.webapi2.service;


import org.example.webapi2.api.dto.ProductDto;
import org.example.webapi2.api.model.Category;
import org.example.webapi2.api.model.Product;
import org.example.webapi2.api.model.User;
import org.example.webapi2.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private  ProductRepository productRepository;

    private ModelMapper modelMapper;

    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return modelMapper.map(product, ProductDto.class);
    }

    public void createProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        productRepository.save(product);
    }

    public ProductDto updateProduct(Integer id, ProductDto productDto) {
        ModelMapper modelMapper = new ModelMapper();
        var products = productRepository.findById(Long.valueOf(id));

        Product product = products.get();
        modelMapper.map(productDto, product);

        User updatedPrduct = productRepository.save(product).getUser();
        return modelMapper.map(updatedPrduct, ProductDto.class);
    }
    public void deleteProductById(Integer userId) {
        Product product = productRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new UsernameNotFoundException("Product not found"));
        productRepository.delete(product);
        productRepository.save(product);

    }


}

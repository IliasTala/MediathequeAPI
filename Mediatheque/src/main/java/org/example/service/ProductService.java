package org.example.service;

import org.example.dto.ProductDTO;
import org.example.mapper.ProductMapper;
import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> findAllProducts() {
        return productMapper.productsToProductDTOs(productRepository.findAll());
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = productMapper.productDTOToProduct(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.productToProductDTO(savedProduct);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public Optional<ProductDTO> getProductById(Long productId) {
        return productRepository.findById(productId)
                .map(productMapper::productToProductDTO);
    }

    public List<ProductDTO> filterByPrice(Double minPrice, Double maxPrice) {
        List<Product> products;
        if (minPrice != null && maxPrice != null) {
            products = productRepository.findByPriceBetween(minPrice, maxPrice);
        } else if (minPrice != null) {
            products = productRepository.findByPriceGreaterThanEqual(minPrice);
        } else if (maxPrice != null) {
            products = productRepository.findByPriceLessThanEqual(maxPrice);
        } else {
            products = productRepository.findAll();
        }
        return productMapper.productsToProductDTOs(products);
    }

}


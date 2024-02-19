package be.helb.ilias.service;

import be.helb.ilias.dto.ProductDTO;
import be.helb.ilias.model.Product;
import be.helb.ilias.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Convertit une entité Product en un DTO ProductDTO
    private ProductDTO convertToDTO(Product product) {
        if (product == null) return null;

        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setAvailable(product.isAvailable());
        return dto;
    }

    // Convertit une liste d'entités Product en une liste de DTOs ProductDTO
    private List<ProductDTO> convertToDTOList(List<Product> products) {
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return convertToDTOList(products);
    }

    public List<ProductDTO> findProductByIdAndAvailability(Long id, Boolean available) {
        List<Product> products = productRepository.findByIdAndAvailable(id, available);
        return convertToDTOList(products);
    }

    public ProductDTO findProductById(Long id) {
        return productRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public List<ProductDTO> findAvailableProducts(Boolean available) {
        List<Product> products = productRepository.findByAvailable(available);
        return convertToDTOList(products);
    }

    public ProductDTO addProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }
}


package org.example.controller;

import org.example.dto.ProductDTO;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public List<ProductDTO> list() {
        return productService.findAllProducts();
    }

    @PostMapping("/save")
    public ProductDTO save(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Long productId) {
        productService.deleteProduct(productId);
    }

    @GetMapping("/getBy/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable(name = "id") Long productId) {
        return productService.getProductById(productId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/filterByPrice")
    public List<ProductDTO> filterByPrice(@RequestParam(required = false) Double minPrice,
                                          @RequestParam(required = false) Double maxPrice) {
        return productService.filterByPrice(minPrice, maxPrice);
    }
}

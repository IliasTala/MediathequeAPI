package be.helb.ilias.controller;

import be.helb.ilias.dto.ProductDTO;
import be.helb.ilias.model.Product;
import be.helb.ilias.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }


    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> getProducts(@RequestParam(required = false) Long id,  @RequestParam(required = false) Boolean available
    ) {
        if (id != null && available != null) {
            return ResponseEntity.ok(productService.findProductByIdAndAvailability(id, available));
        } else if (id != null) {
            return ResponseEntity.ok(Arrays.asList(productService.findProductById(id)));
        } else if (available != null) {
            return ResponseEntity.ok(productService.findAvailableProducts(available));
        } else {
            return ResponseEntity.ok(productService.findAllProducts());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        // Créez un nouvel objet Product à partir du DTO
        Product newProduct = new Product();
        newProduct.setName(productDTO.getName());
        newProduct.setPrice(productDTO.getPrice());
        newProduct.setAvailable(productDTO.isAvailable());

        // appel du service pour save le nouveau produit
        ProductDTO savedProductDTO = productService.addProduct(newProduct);

        return ResponseEntity.ok(savedProductDTO);
    }
}

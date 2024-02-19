package org.example.mapper;

import org.example.dto.ProductDTO;
import org.example.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "id", target = "productId")
    ProductDTO productToProductDTO(Product product);


    @Mapping(source = "productId", target = "id")
    Product productDTOToProduct(ProductDTO productDTO);

    List<ProductDTO> productsToProductDTOs(List<Product> products);
}


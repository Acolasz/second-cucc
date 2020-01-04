package hu.kukutyin.engine.dao.service;

import hu.kukutyin.engine.dto.ProductDto;

import java.util.Collection;

public interface ProductService {
    Collection<ProductDto> getProductAll();
    void createProduct(ProductDto product);
}

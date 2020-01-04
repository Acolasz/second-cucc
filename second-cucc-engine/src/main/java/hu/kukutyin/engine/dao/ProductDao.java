package hu.kukutyin.engine.dao;

import hu.kukutyin.engine.dto.ProductDto;

import java.util.Collection;

public interface ProductDao {
    Collection<ProductDto> getProductAll();
    void createProduct(ProductDto product);
}

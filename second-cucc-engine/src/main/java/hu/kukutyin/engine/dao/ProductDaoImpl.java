package hu.kukutyin.engine.dao;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hu.kukutyin.engine.dao.service.ProductService;
import hu.kukutyin.engine.dto.ProductDto;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    ProductService productService;

    @Override
    public Collection<ProductDto> getProductAll() {
        return productService.getProductAll();
    }

    @Override
    public void createProduct(ProductDto product) {
        productService.createProduct(product);
    }
}

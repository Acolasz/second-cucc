package hu.kukutyin.engine.dao.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.kukutyin.engine.dto.ProductDto;
import hu.kukutyin.engine.dao.mapper.ProductMapperXML;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapperXML productMapperXML;

    @Override
    public Collection<ProductDto> getProductAll() {
        return productMapperXML.getProductAll();
    }

    @Override
    public void createProduct(ProductDto product) {
        productMapperXML.createProduct(product);
    }
}

package hu.kukutyin.engine.dao.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.Mapper;

import org.springframework.stereotype.Repository;

import hu.kukutyin.engine.dto.ProductDto;

@Repository
@Mapper
public interface ProductMapperXML extends MyBatisMapper {
    Collection<ProductDto> getProductAll();
//    ProductDto getProductId(String id);
    void createProduct(ProductDto product);
//    void updateProduct(@Param("id") String id,
//                       @Param("product") ProductDto product);
//    void deleteProduct(String id);
}

package hu.kukutyin.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.kukutyin.engine.dao.ProductDao;
import hu.kukutyin.engine.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.log4j.Logger;

@RestController
@RequestMapping("/restapi")
public class TestController {
    private static final Logger logger = Logger.getLogger(TestController.class);

    @Autowired
    ProductDao productDao;

    @GetMapping(value = "/test",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getTest(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        logger.info(String.format("Started \"%s\" REST endpoint.", "/test"));
        return String.format("Started \"%s\" REST endpoint.", "/test");
    }

    @GetMapping(value = "/products")
    public ResponseEntity<Object> getProductAll() {
        logger.info("Ez product listája");
        return new ResponseEntity<>(productDao.getProductAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/product")
    public ResponseEntity<Object> createProduct(@RequestBody ProductDto product) {
        logger.info(product.toString());
        productDao.createProduct(product);
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
    }
}

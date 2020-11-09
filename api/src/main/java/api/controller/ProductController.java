package api.controller;

import api.dto.ProductDTO;
import core.model.Product;
import core.service.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("")
    public ResponseEntity<Product> saveProduct(@RequestBody ProductDTO productDTO){

        ModelMapper modelMapper = new ModelMapper();
        Product createdProduct = modelMapper.map(productDTO, Product.class);

        productService.save(createdProduct);

        logger.info("POST - /product - CREATED WITH ID : {}", createdProduct.getId());
        return new ResponseEntity<>(
                createdProduct, HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("productId")Long productId){
        try {
            productService.deleteById(productId);
            logger.info("DELETE - /product/{} - OK", productId);
            return new ResponseEntity<>(
                Collections.singletonMap("message", "Product "+ productId + " deleted"), HttpStatus.NO_CONTENT
            );
        } catch (Exception e) {
            logger.error("DELETE - /product/{} - UNAUTHORIZED", productId);
            return new ResponseEntity<>(
                    Collections.singletonMap("error", "Product "+ productId +" cannot be deleted" ),
                    HttpStatus.UNAUTHORIZED
            );
        }
    }
}

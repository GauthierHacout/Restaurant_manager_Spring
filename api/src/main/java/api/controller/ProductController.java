package api.controller;

import core.model.Product;
import core.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("")
    public ResponseEntity<Product> saveReview(@RequestBody Product product){
        Product createdProduct = productService.save(product);

        logger.info("POST - /product - CREATED WITH ID : {}", createdProduct.getId());
        return new ResponseEntity<>(
                createdProduct, HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Object> deleteReview(@PathVariable("productId")Long productId){
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

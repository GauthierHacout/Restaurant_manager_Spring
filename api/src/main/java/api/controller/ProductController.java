package api.controller;

import core.model.Product;
import core.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("")
    public Product saveReview(@RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/{productId}")
    public void deleteReview(@PathVariable("productId")Long productId){
        productService.deleteById(productId);
    }
}

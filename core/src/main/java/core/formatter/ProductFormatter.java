package core.formatter;

import core.model.Product;
import core.service.ProductService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.util.Locale;

@Component
public class ProductFormatter implements Formatter<Product> {

    private final ProductService productService;

    public ProductFormatter(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Product parse(String productId, Locale locale) throws ParseException {
        return productService.findById(Long.parseLong(productId)).orElse(null);
    }

    @Override
    public String print(Product product, Locale locale) {
        return product.getName()+" : "+product.getId();
    }
}

package api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.model.Product;
import core.service.ProductService;

import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Named
@Path("/product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductController implements RestController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @POST
    @Path("")
    public Response saveReview(String productJSON){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Product createdProduct = objectMapper.readValue(productJSON, Product.class);
            productService.save(createdProduct);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(createdProduct)
                    .build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{productId}")
    public void deleteReview(@PathParam("productId")Long productId){
        productService.deleteById(productId);
    }
}

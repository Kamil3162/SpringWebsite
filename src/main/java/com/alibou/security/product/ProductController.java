package com.alibou.security.product;

import com.alibou.security.Order.OrderCustomer;
import com.alibou.security.Order.OrderCustomerRepository;
import com.alibou.security.Order.OrderCustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/auth/")
public class ProductController {
    private final ProductService productService;

    private final OrderCustomerRepository orderCustomerRepository;

    public ProductController(ProductService productService, OrderCustomerRepository orderCustomerRepository) {
        this.productService = productService;
        this.orderCustomerRepository = orderCustomerRepository;
    }

    @GetMapping("products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }
    @GetMapping("dupa")
    public List<OrderCustomer> getAllData() {
        return orderCustomerRepository.findAll();
    }


    @GetMapping("products/{pk}")
    public ResponseEntity <Product> getProductByPk(@PathVariable("pk")  Long id,
                                                  Authentication authentication){

        Optional<Product> byId = productService.getProductRepository().findById(id);
        if (byId.isPresent()){
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.notFound().build();
    }
}

package com.alibou.security.OrderItem;

import com.alibou.security.Order.OrderCustomer;
import com.alibou.security.Order.OrderCustomerRepository;
import com.alibou.security.Order.OrderResponse;
import com.alibou.security.product.Product;
import com.alibou.security.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/auth/")
public class OrderitemController {

    @Autowired
    private OrderitemService orderitemService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderCustomerRepository orderCustomerRepository;
    @Autowired
    private OrderitemRepository orderitemRepository;
    public OrderitemController() {}

    @GetMapping("orderitems")
    public ResponseEntity<List<OrderitemResponse>> getAllOrders(Authentication authentication) {
        List<Orderitem> orders = orderitemService.findAll();
        List<OrderitemResponse> finalOrders = new ArrayList<>();

        for (Orderitem order : orders) {
            OrderitemResponse orderitemResponse = new OrderitemResponse();
            orderitemResponse.setOrder(order.getOrder().getId());
            orderitemResponse.setId(order.getId());
            orderitemResponse.setProduct(order.getProduct().getId());
            orderitemResponse.setPrice(order.getPrice());
            orderitemResponse.setQuantity(order.getQuantity());
            orderitemResponse.setProduct_name(order.getProduct().getName());
            finalOrders.add(orderitemResponse);
        }

        return ResponseEntity.ok(finalOrders);
    }

    @PostMapping("orderitems")
    public ResponseEntity<Orderitem> saveOrderitem(@RequestBody OrderitemRequest orderitemRequest) {
        Product product = productRepository.findById(orderitemRequest.getProduct()).get();
        if (product != null) {
            OrderCustomer order = orderCustomerRepository.findById(orderitemRequest.getOrder()).get();
            if (order != null) {
                Orderitem orderitem = new Orderitem();
                orderitem.setOrder(order);
                orderitem.setProduct(product);
                orderitem.setPrice(orderitemRequest.getPrice());
                orderitem.setQuantity(orderitemRequest.getQuantity());
                Orderitem createdOrderitem = orderitemRepository.save(orderitem);
                return ResponseEntity.status(HttpStatus.CREATED).body(null);
            }
        }
        return ResponseEntity.ok(null);
    }

    @GetMapping("orderitems/{id}")
    public ResponseEntity<OrderitemResponse> getOrderitemById(@PathVariable("id") Long id){
        Orderitem orderitem = orderitemService.getOrderitemById(id);
        if (orderitem != null) {
            OrderitemResponse orderitemResponse = new OrderitemResponse();
            orderitemResponse.setId(orderitem.getId());
            orderitemResponse.setOrder(orderitem.getOrder().getId());
            orderitemResponse.setPrice(orderitem.getProduct().getPrice());
            orderitemResponse.setProduct(orderitem.getProduct().getId());
            orderitemResponse.setQuantity(orderitem.getQuantity());
            orderitemResponse.setProduct_name(orderitem.getProduct().getName());
            return ResponseEntity.ok(orderitemResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("orderitems/{id}")
    public ResponseEntity<Orderitem> modifyOrderById(@PathVariable("id") Long id,
                                                     @RequestBody OrderitemRequest orderitemRequest){
        Orderitem orderitem = orderitemService.getOrderitemById(id);
        if (orderitem != null) {
            int quantity = orderitemRequest.getQuantity();
            orderitem.setQuantity(quantity);
            orderitemRepository.save(orderitem);
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("orderitems/{id}")
    public ResponseEntity<String> deleteOrderitemById(@PathVariable("id") Long id) {
        Orderitem orderitem = orderitemService.getOrderitemById(id);
        if (orderitem != null) {
            orderitemService.deleteOrderitemById(id);
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.notFound().build();
    }
}
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
            orderitemResponse.setProduct(order.getProduct().getId());
            orderitemResponse.setPrice(order.getPrice());
            orderitemResponse.setQuantity(order.getQuantity());
            finalOrders.add(orderitemResponse);
        }

        return ResponseEntity.ok(finalOrders);
    }

    @PostMapping("orderitems")
    public ResponseEntity<Orderitem> saveOrderitem(@RequestBody OrderitemRequest orderitemRequest) {
        System.out.print(orderitemRequest.getProduct());
        System.out.print(orderitemRequest.getOrder());
        System.out.print(orderitemRequest.getQuantity());
        System.out.print(orderitemRequest.getPrice());
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
    public Orderitem getOrderitemById(@PathVariable("id") Long id) {
        return orderitemService.getOrderitemById(id);
    }

    @DeleteMapping("orderitems/{id}")
    public void deleteOrderitemById(@PathVariable("id") Long id) {
        orderitemService.deleteOrderitemById(id);
    }
}
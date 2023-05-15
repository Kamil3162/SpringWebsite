package com.alibou.security.Order;

import com.alibou.security.user.User;
import com.alibou.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class OrderCustomerController {

    private final UserRepository userRepository;
    private final OrderCustomerService orderCustomerService;

    // do axios dodac token z cookies
    @GetMapping("orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders(Authentication authentication) {
        List<OrderCustomer> orders = orderCustomerService.findAll();
        List<OrderResponse> final_orders = new ArrayList<>();
        for (OrderCustomer order: orders) {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(order.getId());
            orderResponse.setFirstname(order.getUser().getFirstname());
            orderResponse.setStatus(order.getStatus());
            orderResponse.setEmail(order.getUser().getEmail());
            orderResponse.setTotalPrice(order.getTotalPrice());
            orderResponse.setOrderDate(order.getOrderDate());
            final_orders.add(orderResponse);
        }
        return ResponseEntity.ok(final_orders);
    }

    @GetMapping("orders/{id}")
    public ResponseEntity<OrderCustomer> getOrderById(@PathVariable("id") Long id) {
        Optional<OrderCustomer> order = orderCustomerService.findById(id);
        return order.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("orders")
    public ResponseEntity<OrderCustomer> createOrder(@RequestBody OrderRequest orderRequest) {
        System.out.print(orderRequest.getUser());
        User user = userRepository.findById(orderRequest.getUser()).get();

        OrderCustomer orderCustomer = new OrderCustomer();
        orderCustomer.setStatus(orderRequest.getStatus());
        orderCustomer.setTotalPrice(orderRequest.getTotalPrice());
        orderCustomer.setOrderDate(LocalDateTime.now());
        orderCustomer.setUser(user);
        OrderCustomer createdOrder = orderCustomerService.save(orderCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("orders/{id}")
    public ResponseEntity<OrderCustomer> updateOrder(@PathVariable("id") Long id, @RequestBody OrderCustomer orderCustomer) {
        Optional<OrderCustomer> existingOrder = orderCustomerService.findById(id);
        if (existingOrder.isPresent()) {
            orderCustomer.setId(id);
            OrderCustomer updatedOrder = orderCustomerService.save(orderCustomer);
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        Optional<OrderCustomer> existingOrder = orderCustomerService.findById(id);
        if (existingOrder.isPresent()) {
            orderCustomerService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

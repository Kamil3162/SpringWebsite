package com.alibou.security.OrderItem;

import com.alibou.security.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderitemService {

    @Autowired
    private OrderitemRepository orderitemRepository;

    public OrderitemService(OrderitemRepository orderitemRepository) {
        this.orderitemRepository = orderitemRepository;
    }

    public List<Orderitem> findAll() {
        return orderitemRepository.findAll();
    }

    public Orderitem getOrderitemById(Long id) {
        return orderitemRepository.findById(id).orElse(null);
    }

    public void deleteOrderitemById(Long id) {
        orderitemRepository.deleteById(id);
    }

}

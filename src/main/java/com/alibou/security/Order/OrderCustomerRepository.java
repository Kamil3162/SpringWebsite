package com.alibou.security.Order;

import com.alibou.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCustomerRepository extends JpaRepository<OrderCustomer, Long> {
    OrderCustomer findByUserAndStatus(User user, boolean status);

    boolean existsByUserAndStatus(User user, boolean status);
}
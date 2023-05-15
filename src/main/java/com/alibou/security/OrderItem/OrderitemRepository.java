package com.alibou.security.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderitemRepository extends JpaRepository<Orderitem, Long> {
}
package com.alibou.security.OrderItem;

import lombok.*;

import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderitemRequest {
    private Long order;
    private Long product;
    private BigDecimal price;
    private int quantity;

    // Getters and Setters
}
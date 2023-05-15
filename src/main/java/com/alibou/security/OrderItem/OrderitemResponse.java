package com.alibou.security.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderitemResponse {
    private Long order;
    private Long product;
    private BigDecimal price;
    private int quantity;
}

package com.alibou.security.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;
    private Boolean status;
    private String firstname;
    private String lastname;
    private String email;
}

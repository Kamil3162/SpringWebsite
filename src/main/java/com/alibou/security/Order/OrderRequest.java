package com.alibou.security.Order;

import com.alibou.security.user.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {

    private LocalDateTime orderDate;
    private Integer user;
    private BigDecimal totalPrice;
    private Boolean status;

}

package com.alibou.security.OrderItem;
import com.alibou.security.user.UserDTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderitemDTO {
    private Orderitem orderitem;
    private UserDTO user;

}

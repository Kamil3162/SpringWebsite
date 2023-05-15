package com.alibou.security.user;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;
}

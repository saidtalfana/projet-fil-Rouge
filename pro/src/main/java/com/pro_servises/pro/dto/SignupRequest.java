package com.pro_servises.pro.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private String name;
    private String username;
    private String email;
    private String password;
}

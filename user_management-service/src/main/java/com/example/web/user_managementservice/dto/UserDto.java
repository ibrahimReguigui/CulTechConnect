package com.example.web.user_managementservice.dto;




import com.example.web.user_managementservice.Enum.AcountStatus;
import com.example.web.user_managementservice.Enum.Role;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private String image;
    private int phoneNumber;
    private AcountStatus acountStatus;

}

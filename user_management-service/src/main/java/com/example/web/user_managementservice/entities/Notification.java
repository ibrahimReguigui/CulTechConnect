package com.example.web.user_managementservice.entities;




import com.example.web.user_managementservice.Enum.AcountStatus;
import com.example.web.user_managementservice.Enum.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notification {

    private String userId;
    private String content;
    private Date time;

}

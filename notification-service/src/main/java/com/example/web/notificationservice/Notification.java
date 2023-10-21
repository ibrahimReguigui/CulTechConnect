package com.example.web.notificationservice;


import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "EEE MMM dd HH:mm:ss zzz yyyy")
    private Date time;
}

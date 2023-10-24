package blogservice.blog.entities;


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

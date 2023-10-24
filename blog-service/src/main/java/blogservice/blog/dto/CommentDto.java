package blogservice.blog.dto;

import blogservice.blog.entities.Blog;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private int id;
    private String content;

}

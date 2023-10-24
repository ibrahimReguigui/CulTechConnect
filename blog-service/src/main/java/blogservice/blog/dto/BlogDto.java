package blogservice.blog.dto;

import blogservice.blog.entities.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BlogDto {

    private Long id;

    private String title;

    private String description;

    private Date createdOn;

    private Boolean isPublished;

    private byte[] image;

    private Set<Comment>  comments = new HashSet<>();
}

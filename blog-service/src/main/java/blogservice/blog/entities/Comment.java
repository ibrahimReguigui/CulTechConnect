package blogservice.blog.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @Column(unique = true , nullable= false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @JsonIgnore
    @ManyToOne
    private Blog blog;

}

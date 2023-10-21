package blogservice.blog.entities;

import blogservice.blog.controller.FileRessource;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    @Id
    @Column (unique = true , nullable= false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private Date createdOn;

    private Boolean isPublished;
    @Lob
    @Column(length = 1048576)
    private byte[] image;

    @JsonIgnore
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();


    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return this.image;
    }
}


package blogservice.blog.repository;

import blogservice.blog.entities.Blog;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Registered
public interface BlogRepository extends JpaRepository<Blog, Long> {

    public List<Blog> findAllByIsPublished(Boolean isPublished);

}

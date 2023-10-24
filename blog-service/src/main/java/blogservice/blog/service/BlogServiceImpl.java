package blogservice.blog.service;

import blogservice.blog.entities.Blog;
import blogservice.blog.entities.Notification;
import blogservice.blog.repository.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final KafkaTemplate<String, Notification> kafkaTemplate;
    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository ,KafkaTemplate<String, Notification> kafkaTemplate) {
        this.blogRepository = blogRepository;
        this.kafkaTemplate =kafkaTemplate;
    }


    @Override
    public Blog createBlog(Map<String, Object> map) {
        var blog = new Blog();
        blog.setTitle(map.get("title").toString());
        blog.setDescription(map.get("description").toString());
        blog.setCreatedOn(Date.valueOf(LocalDate.now()));
        blog.setIsPublished(true);
        kafkaTemplate.send("notification",Notification.builder().time(new java.util.Date()).content("Blog added !!!")
                .userId(blog.getCreatedOn().toString()).build());
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Long blogId, Map<String, Object> map) {
        Blog blog = getBlogById(blogId);
        if (blog != null) {
            if (map.containsKey("title")) {
                blog.setTitle(map.get("title").toString());
            }
            if (map.containsKey("description")) {
                blog.setDescription(map.get("description").toString());
            }
            return blogRepository.save(blog);
        }
        return null;
    }

    @Override
    public List<Blog> getallBlogs() {
        return blogRepository.findAllByIsPublished(true);
    }

    @Override
    public Blog getBlogById(Long blogId) {
        Optional<Blog> blogOptional = blogRepository.findById(blogId);
        return blogOptional.orElse(null);
    }


}

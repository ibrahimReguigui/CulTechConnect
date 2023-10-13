package blogservice.blog.service;

import blogservice.blog.entities.Blog;
import blogservice.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }


    @Override
    public Blog createBlog(Map<String, Object> map) {
        var blog = new Blog();
        blog.setTitle(map.get("title").toString());
        blog.setDescription(map.get("description").toString());
        blog.setCreatedOn(Date.valueOf(LocalDate.now()));
        blog.setIsPublished(true);
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

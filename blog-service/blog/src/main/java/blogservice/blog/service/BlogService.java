package blogservice.blog.service;

import blogservice.blog.entities.Blog;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog createBlog(Map<String, Object> map);
    Blog updateBlog(Long blogId, Map<String, Object> map);

    List<Blog> getallBlogs();
    Blog getBlogById(Long blogId);


}

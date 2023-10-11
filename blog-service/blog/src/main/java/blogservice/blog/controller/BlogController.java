package blogservice.blog.controller;

import blogservice.blog.entities.Blog;
import blogservice.blog.service.BlogService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@RestController
@Validated
@RequestMapping("/")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping(value = {"createBlog"})
    public Callable<Blog> createBlog(@Valid @RequestBody Map<String, Object> map) {
        return  () -> blogService.createBlog(map);
    }

    @GetMapping(value = {"getAllBlog"})
    public Callable<List<Blog>> getAllBlogs() {
        return  () -> blogService.getallBlogs();
    }

    @PutMapping(value = {"/UpdateBlog/{blogId}"})
    public Callable<Blog> updateBlog(@PathVariable Long blogId, @Valid @RequestBody Map<String, Object> map) {
        return  () -> blogService.updateBlog(blogId, map);
    }


    @GetMapping(value = {"getBlogById/{blogId}"})
    public Callable<Blog> getBlogById(@Valid @PathVariable @Positive(message = "id must be positive number")Long blogId) {
        return  () -> blogService.getBlogById(blogId);
    }
}

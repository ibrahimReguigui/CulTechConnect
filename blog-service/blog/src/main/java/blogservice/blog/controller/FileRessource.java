package blogservice.blog.controller;

import blogservice.blog.entities.Blog;
import blogservice.blog.repository.BlogRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/file")
public class FileRessource {

    private final BlogRepository blogRepository;

    public FileRessource(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> multipartFiles,
                                                    @RequestParam("title") String title,
                                                    @RequestParam("description") String description) {
        List<String> filenames = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            try {
                Blog blog = new Blog();
                blog.setTitle(title);
                blog.setDescription(description);
                blog.setImage(file.getBytes());
                blog.setCreatedOn(new Date());
                blog.setIsPublished(true);
                blogRepository.save(blog);
                filenames.add(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.ok().body(filenames);
    }
    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        return ResponseEntity.ok().body(blogs);
    }


}

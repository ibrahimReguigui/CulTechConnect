package blogservice.blog.controller;

import blogservice.blog.client.EmailDto;
import blogservice.blog.client.EmailServiceClient;
import blogservice.blog.entities.Blog;
import blogservice.blog.repository.BlogRepository;
import org.springframework.http.HttpStatus;
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
   private final EmailServiceClient emailServiceClient;

    public FileRessource(BlogRepository blogRepository, EmailServiceClient emailServiceClient) {
        this.blogRepository = blogRepository;
        this.emailServiceClient = emailServiceClient;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFiles(@RequestParam("files") List<MultipartFile> multipartFiles,
                                         @RequestParam("title") String title,
                                         @RequestParam("description") String description) {
        List<String> filenames = new ArrayList<>();
        try {
            for (MultipartFile file : multipartFiles) {
                String filename = StringUtils.cleanPath(file.getOriginalFilename());
                Blog blog = new Blog();
                blog.setTitle(title);
                blog.setDescription(description);
                blog.setImage(file.getBytes());
                blog.setCreatedOn(new Date());
                blog.setIsPublished(true);
                blogRepository.save(blog);
                filenames.add(filename);
            }

            // Envoi d'un e-mail de notification
            EmailDto emailDto = new EmailDto();
            emailDto.setOwnerRef("Nadine");
            emailDto.setEmailFrom("mili.nadine07@gmail.com");
            emailDto.setEmailTo("nadine.mili@esprit.tn");
            emailDto.setSubject("Notification de création de blog");
            emailDto.setText("Vous avez créé le blog avec succès.");

            ResponseEntity<EmailDto> response = emailServiceClient.sendingEmail(emailDto);
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("L'e-mail a été envoyé avec succès !");
            } else {
                System.out.println("Échec de l'envoi d'e-mail : " + response.getStatusCode());
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du traitement des fichiers.");
        }
        return ResponseEntity.ok().body(filenames);
    }
    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        return ResponseEntity.ok().body(blogs);
    }

    @GetMapping("/blogs/{blogId}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long blogId) {
        Optional<Blog> blogOptional = blogRepository.findById(blogId);
        return blogOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/blogs/{blogId}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getBlogImage(@PathVariable Long blogId) {
        Optional<Blog> blogOptional = blogRepository.findById(blogId);
        if (blogOptional.isPresent()) {
            byte[] image = blogOptional.get().getImage();
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

package blogservice.blog.controller;

import blogservice.blog.client.EmailDto;
import blogservice.blog.client.EmailServiceClient;
import blogservice.blog.client.UserServiceClient;
import blogservice.blog.entities.Blog;
import blogservice.blog.entities.Notification;
import blogservice.blog.repository.BlogRepository;
import blogservice.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/file")
public class FileRessource {

    private final BlogRepository blogRepository;
   private final EmailServiceClient emailServiceClient;

    private final BlogService blogService;
    private final KafkaTemplate<String, Notification> kafkaTemplate;


    public FileRessource(BlogRepository blogRepository, EmailServiceClient emailServiceClient, BlogService blogService,KafkaTemplate<String, Notification> kafkaTemplate) {

        this.blogRepository = blogRepository;
        this.emailServiceClient = emailServiceClient;
        this.blogService = blogService;
        this.kafkaTemplate=kafkaTemplate;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFiles(@RequestParam("files") List<MultipartFile> multipartFiles,
                                         @RequestParam("title") String title,
                                         @RequestParam("description") String description) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur non authentifié");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();

        try {
            String userId = userServiceClient.getUserId(email);

            List<String> filenames = new ArrayList<>();
            for (MultipartFile file : multipartFiles) {
                String filename = StringUtils.cleanPath(file.getOriginalFilename());
                Blog blog = new Blog();
                blog.setTitle(title);
                blog.setDescription(description);
                blog.setImage(file.getBytes());
                blog.setCreatedOn(new Date());
                blog.setIsPublished(true);
                blogRepository.save(blog);
                kafkaTemplate.send("notification",Notification.builder().time(new java.util.Date()).content("Blog added !!!")
                        .userId(blog.getCreatedOn().toString()).build());
                filenames.add(filename);
            }

            // Envoi d'un e-mail de notification
            EmailDto emailDto = new EmailDto();
            emailDto.setOwnerRef("Nadine");
            emailDto.setEmailFrom("ibrahim.reguigui@esprit.tn");
            emailDto.setEmailTo("ibrahim.reguigui@esprit.tn");
            emailDto.setSubject("Notification de création de blog");
            emailDto.setText("Vous avez créé le blog avec succès.");

            ResponseEntity<EmailDto> response = emailServiceClient.sendingEmail(emailDto);
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("L'e-mail a été envoyé avec succès !");
            } else {
                System.out.println("Échec de l'envoi d'e-mail : " + response.getStatusCode());
            }

            return ResponseEntity.ok().body(filenames);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du traitement des fichiers.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite : " + ex.getMessage());
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

    @GetMapping(value = "/blogs/{blogId}/image", produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getBlogImage(@PathVariable Long blogId) {
        Optional<Blog> blogOptional = blogRepository.findById(blogId);
        if (blogOptional.isPresent()) {
            byte[] image = blogOptional.get().getImage();
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/update/{blogId}")
    public ResponseEntity<?> updateBlogWithImage(@PathVariable Long blogId,
                                                 @RequestParam("file") MultipartFile file,
                                                 @RequestParam("title") String title,
                                                 @RequestParam("description") String description) {
        try {
            Blog blog = blogRepository.findById(blogId).orElse(null);
            if (blog != null) {
                if (title != null) {
                    blog.setTitle(title);
                }
                if (description != null) {
                    blog.setDescription(description);
                }
                if (file != null) {
                    blog.setImage(file.getBytes());
                }
                blogRepository.save(blog);

                // Envoi d'un e-mail de notification
                EmailDto emailDto = new EmailDto();
                emailDto.setOwnerRef("Nadine");
                emailDto.setEmailFrom("mili.nadine07@gmail.com");
                emailDto.setEmailTo("nadine.mili@esprit.tn");
                emailDto.setSubject("Notification de mise à jour de blog");
                emailDto.setText("Vous avez mis à jour le blog avec succès");

                ResponseEntity<EmailDto> response = emailServiceClient.sendingEmail(emailDto);
                if (response.getStatusCode().is2xxSuccessful()) {
                    System.out.println("L'e-mail a été envoyé avec succès !");
                } else {
                    System.out.println("Échec de l'envoi d'e-mail : " + response.getStatusCode());
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog non trouvé pour l'ID donné.");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour du blog avec l'image.");
        }
        return ResponseEntity.ok().body("Blog mis à jour avec succès.");
    }



}

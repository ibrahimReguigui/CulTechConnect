package blogservice.blog.client;


import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name = "email-service")
public interface EmailServiceClient {
    @PostMapping("/sending-email")
    ResponseEntity<EmailDto> sendingEmail(@Valid @RequestBody EmailDto emailDto);
}

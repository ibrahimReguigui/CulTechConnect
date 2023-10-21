package blogservice.blog.controller;

import blogservice.blog.dto.CommentDto;
import blogservice.blog.response.ApiResponse;
import blogservice.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment (@RequestBody CommentDto comment, @PathVariable Long postId){
      CommentDto createComment =  this.commentService.createComment(comment, postId);
      return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment (@PathVariable Long commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully!!",
                true),
                HttpStatus.OK);
    }

    @GetMapping("/blog/{blogId}/comments")
    public Set<CommentDto> getCommentsByBlogId(@PathVariable Long blogId) {
        return commentService.getCommentsByBlogId(blogId);
    }
}

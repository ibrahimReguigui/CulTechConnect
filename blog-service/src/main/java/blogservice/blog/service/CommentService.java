package blogservice.blog.service;

import blogservice.blog.dto.CommentDto;

import java.util.Set;

public interface CommentService {

    CommentDto createComment (CommentDto commentDto, Long blogId);

    void deleteComment(Long commentId);


    Set<CommentDto> getCommentsByBlogId(Long blogId);
}

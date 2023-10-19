package blogservice.blog.service;

import blogservice.blog.dto.CommentDto;

public interface CommentService {

    CommentDto createComment (CommentDto commentDto, Long blogId);

    void deleteComment(Long commentId);
}

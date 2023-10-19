package blogservice.blog.service;

import blogservice.blog.dto.CommentDto;
import blogservice.blog.entities.Blog;
import blogservice.blog.entities.Comment;
import blogservice.blog.exception.ResourceNotFoundException;
import blogservice.blog.repository.BlogRepository;
import blogservice.blog.repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CommentServiceImpl implements CommentService{

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CommentRepository commentRepository;

    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Long blogId) {
        Blog blog = this.blogRepository.findById(blogId).
                orElseThrow(()->new ResourceNotFoundException("Blog","blog id",blogId));
       Comment comment =  this.modelMapper.map(commentDto, Comment.class);
       comment.setBlog(blog);
       Comment saveComment =  this.commentRepository.save(comment);
        return this.modelMapper.map(saveComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = this.commentRepository.findById(commentId).
                orElseThrow(()->new ResourceNotFoundException("Comment","comment id",commentId));
        this.commentRepository.delete(comment);
    }
}

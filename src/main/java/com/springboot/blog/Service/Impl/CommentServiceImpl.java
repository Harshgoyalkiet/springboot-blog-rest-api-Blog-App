package com.springboot.blog.Service.Impl;

import com.springboot.blog.Entity.Comment;
import com.springboot.blog.Entity.Post;
import com.springboot.blog.Exception.BlogAPIException;
import com.springboot.blog.Exception.ResourceNotFoundException;
import com.springboot.blog.Payload.CommentDto;
import com.springboot.blog.Repository.CommentRepository;
import com.springboot.blog.Repository.PostRepository;
import com.springboot.blog.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    private CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<CommentDto> getAllComments(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> convertToDto(comment)).collect(Collectors.toList());
    }


    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {

        Comment comment = convertToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Comment", "postId", postId));
        comment.setPost(post);
        Comment response = commentRepository.save(comment);
        return convertToDto(response);
    }

    //get comment By commentId
    @Override
    public CommentDto getComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "commenttId", commentId));
        // now we have to apply a logic to check whether this comment belongs to this post or not.
        if (!comment.getPost().getId().equals(postId)) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "comment does not belongs to the post");
        }
        return convertToDto(comment);

    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "commenttId", commentId));
        // now we have to apply a logic to check whether this comment belongs to this post or not.
        if (!comment.getPost().getId().equals(postId)) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "comment does not belongs to the post");
        }
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        Comment updatedComment = commentRepository.save(comment);
        return convertToDto(updatedComment);
    }

    @Override
    public String deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "commenttId", commentId));
        // now we have to apply a logic to check whether this comment belongs to this post or not.
        if (!comment.getPost().getId().equals(postId)) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "comment does not belongs to the post");
        }
        commentRepository.deleteById(commentId);
        return "Comment deleted successfully with Id: " + commentId;
    }


    //map to Dto
    private CommentDto convertToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    //map to Entity
    private Comment convertToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        return comment;
    }


}

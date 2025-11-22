package com.springboot.blog.Service;


import com.springboot.blog.Payload.CommentDto;
import org.jspecify.annotations.Nullable;

import java.net.URI;
import java.util.List;

public interface CommentService {
    List<CommentDto> getAllComments(Long postId);

    CommentDto createComment(Long postId, CommentDto commentDto);

    CommentDto getComment(Long postId, Long commentId);

    CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);

    String deleteComment(Long postId, Long commentId);
}

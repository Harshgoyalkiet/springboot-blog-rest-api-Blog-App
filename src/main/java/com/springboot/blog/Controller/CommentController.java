package com.springboot.blog.Controller;

import com.springboot.blog.Payload.CommentDto;
import com.springboot.blog.Service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {


    private CommentService commentService;

    private CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //get all comments
    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getAllComments(postId));
    }

    //get comment by id
    @GetMapping("post/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Long postId, @PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.getComment(postId, commentId));
    }


    //create new comment
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long postId, @RequestBody CommentDto commentDto) {
        return new ResponseEntity(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    // update comment by id
    @PutMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.updateComment(postId,commentId, commentDto));
    }

    // delete comment by id
    @DeleteMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.deleteComment(postId, commentId));
    }


}

package com.springboot.blog.Controller;

import com.springboot.blog.Payload.PostDto;
import com.springboot.blog.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    //get all posts
    @GetMapping("/")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    //get post by ID
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    //create a post
    @PostMapping("/")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //update existing post with id
    @PutMapping("/update")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.updatePost(postDto));
    }

    // delete post by Id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@RequestParam Long id) {
        return ResponseEntity.ok(postService.deletePost(id));
    }
    // get pagination and sorting posts

}

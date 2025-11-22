package com.springboot.blog.Service;

import com.springboot.blog.Payload.PostDto;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto);

    String deletePost(Long id);
}

package com.springboot.blog.Service.Impl;

import com.springboot.blog.Entity.Post;
import com.springboot.blog.Exception.ResourceNotFoundException;
import com.springboot.blog.Payload.PostDto;
import com.springboot.blog.Repository.PostRepository;
import com.springboot.blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post = convertToEntity(postDto);
        Post savedPost = postRepository.save(post);
        return convertToDto(savedPost);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postResponse = new ArrayList<>();
        if (!posts.isEmpty()) {
            postResponse = convertToDtoLists(posts);

        }
        return postResponse;

    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return convertToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto) {
        Long Id = postDto.getId();
        Post post = postRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", Id));
        ;
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        postRepository.save(post);
        return convertToDto(post);
    }

    @Override
    public String deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
        return "post deleted successfully with id " + id;
    }

    public PostDto convertToDto(Post post) {

        PostDto postResponseDto = new PostDto();
        postResponseDto.setTitle(post.getTitle());
        postResponseDto.setDescription(post.getDescription());
        postResponseDto.setContent(post.getContent());
        return postResponseDto;
    }

    public Post convertToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }

    public List<PostDto> convertToDtoLists(List<Post> posts) {
        List<PostDto> responseDtoList = new ArrayList<>();
        for (int i = 0; i < posts.size(); i++) {
            PostDto responseDto = convertToDto(posts.get(i));
            responseDtoList.add(responseDto);

        }
        return responseDtoList;
    }


}

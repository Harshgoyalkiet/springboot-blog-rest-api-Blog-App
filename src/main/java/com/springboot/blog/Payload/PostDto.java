package com.springboot.blog.Payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private Long id;
    //title should not be null or empty and should have atleast 2 characters
    @NotEmpty
    @Size(min=2,message = "Post title should have atleast two characters")
    private String title;
    //post description should not be null or empty and should have atleast 10 characters.
    @NotEmpty
    @Size(min=10,message="Post description  should have atleast 10 characters")
    private String description;
    // post content should not be null or empty
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;

}

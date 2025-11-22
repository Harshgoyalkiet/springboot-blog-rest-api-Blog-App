package com.springboot.blog.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CommentDto {
    private Long Id;
    private String name;
    private String email;
    private String body;


}

package com.fashionblogweek9.dtos.commentdtos;

import com.fashionblogweek9.models.Post;
import com.fashionblogweek9.models.User;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CreateCommentDto {
    private String comment;
    private Timestamp createdAt;
    private User user;
    private Post post;
}

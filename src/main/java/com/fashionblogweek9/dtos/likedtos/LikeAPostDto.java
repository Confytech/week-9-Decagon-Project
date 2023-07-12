package com.fashionblogweek9.dtos.likedtos;

import com.fashionblogweek9.models.Post;
import com.fashionblogweek9.models.User;
import lombok.Data;

@Data
public class LikeAPostDto {
    private Long noOfLikes = 0L;
    private User user;
    private Post post;
}

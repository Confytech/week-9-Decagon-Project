package com.fashionblogweek9.services;

import com.fashionblogweek9.exceptions.NotFoundException;
import com.fashionblogweek9.exceptions.UnauthorizedException;
import com.fashionblogweek9.models.ApiResponse;
import com.fashionblogweek9.models.Like;

public interface LikeService {
    ApiResponse<Like> likeAPost(Long postId) throws UnauthorizedException;
    ApiResponse<String> unLikeAPost(Long postId) throws UnauthorizedException, NotFoundException;
    ApiResponse<Like> likeAComment(Long postId) throws UnauthorizedException;

    ApiResponse<String> unLikeAComment(Long commentId) throws UnauthorizedException, NotFoundException;
}

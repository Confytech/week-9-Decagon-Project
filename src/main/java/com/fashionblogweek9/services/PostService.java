package com.fashionblogweek9.services;

import com.fashionblogweek9.dtos.postdtos.CreatePostDto;
import com.fashionblogweek9.exceptions.NotFoundException;
import com.fashionblogweek9.exceptions.NotNullException;
import com.fashionblogweek9.exceptions.UnauthorizedException;
import com.fashionblogweek9.models.ApiResponse;
import com.fashionblogweek9.models.Post;

import java.util.List;

public interface PostService {
    ApiResponse<Post> createPost(CreatePostDto createPostDto) throws NotNullException, UnauthorizedException;
    ApiResponse<Post> findPostById(Long postId) throws NotFoundException, UnauthorizedException;
    ApiResponse<List<Post>> findAllPosts() throws NotFoundException, UnauthorizedException;
    ApiResponse<Post> updatePostById(Long postId, CreatePostDto createPostDto) throws NotFoundException, UnauthorizedException;
    void deletePostById(Long postId) throws UnauthorizedException, NotFoundException;
}

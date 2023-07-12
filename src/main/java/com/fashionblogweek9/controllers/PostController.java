package com.fashionblogweek9.controllers;

import com.fashionblogweek9.dtos.postdtos.CreatePostDto;
import com.fashionblogweek9.exceptions.NotFoundException;
import com.fashionblogweek9.exceptions.NotNullException;
import com.fashionblogweek9.exceptions.UnauthorizedException;
import com.fashionblogweek9.models.ApiResponse;
import com.fashionblogweek9.models.Post;
import com.fashionblogweek9.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/v1/fashion-blog")
public class PostController {
    private final PostService postService;

    @PostMapping("/blogger/create-post")
    public ResponseEntity<ApiResponse<Post>> createPost(@RequestBody CreatePostDto createPostDto) throws UnauthorizedException, NotNullException {
      ApiResponse<Post> post =  postService.createPost(createPostDto);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/view-post/{postId}")
    public ResponseEntity<ApiResponse<Post>> viewPostById(@PathVariable Long postId) throws NotFoundException, UnauthorizedException {
        ApiResponse<Post> post = postService.findPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.FOUND);
    }

    @GetMapping("/all-posts")
    public ResponseEntity<ApiResponse<List<Post>>> viewAllPosts() throws NotFoundException, UnauthorizedException {
        ApiResponse<List<Post>> allPosts = postService.findAllPosts();
        return new ResponseEntity<>(allPosts, HttpStatus.ACCEPTED);
    }

    @PutMapping("/blogger/update-post/{postId}")
    public ResponseEntity<ApiResponse<Post>> updatePost(@PathVariable Long postId, @RequestBody CreatePostDto createPostDto) throws NotFoundException, UnauthorizedException {
        ApiResponse<Post> post = postService.updatePostById(postId, createPostDto);
        return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/blogger/delete-post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) throws UnauthorizedException, NotFoundException {
        postService.deletePostById(postId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.ACCEPTED);
    }
}

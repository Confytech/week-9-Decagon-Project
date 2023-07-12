package com.fashionblogweek9.services;

import com.fashionblogweek9.dtos.commentdtos.CreateCommentDto;
import com.fashionblogweek9.exceptions.NotFoundException;
import com.fashionblogweek9.exceptions.NotNullException;
import com.fashionblogweek9.models.ApiResponse;
import com.fashionblogweek9.models.Comment;

import java.util.List;

public interface CommentService {
    ApiResponse<Comment> createComment(CreateCommentDto createCommentDto, Long postId) throws NotNullException, NotFoundException;
    ApiResponse<List<Comment>> findAllPostComments(Long postId) throws NotFoundException;
    ApiResponse<Comment> updateComment(Long commentId, Comment comment) throws NotNullException;
    ApiResponse<String> deleteCommentById(Long commentId);
}

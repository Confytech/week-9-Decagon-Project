package com.fashionblogweek9.services.serviceimpl;

import com.fashionblogweek9.enums.Role;
import com.fashionblogweek9.exceptions.NotFoundException;
import com.fashionblogweek9.exceptions.UnauthorizedException;
import com.fashionblogweek9.models.*;
import com.fashionblogweek9.repositories.CommentRepository;
import com.fashionblogweek9.repositories.LikeRepository;
import com.fashionblogweek9.repositories.PostRepository;
import com.fashionblogweek9.services.LikeService;
import com.fashionblogweek9.util.LoggedInUser;
import com.fashionblogweek9.util.ResponseManager;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final ResponseManager responseManager;
    private final HttpSession httpSession;
    private final LoggedInUser loggedInUser;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public ApiResponse<Like> likeAPost(Long postId) throws UnauthorizedException {
        List<Like> likes = likeRepository.findLikesByPostId(postId);

        for(int i = 0; i < likes.size(); i++){
            if(likes.get(i).getUser() == loggedInUser.findLoggedInUser()){
                throw new UnauthorizedException("You can't like this post more than once");
            }
        }

        Like like = new Like();

        if(httpSession.getAttribute("userId") == null){
            User user = new User(Role.ANONYMOUS_USER);
            like.setUser(user);
        }
        else {
            like.setUser(loggedInUser.findLoggedInUser());
        }

        Post post = postRepository.findById(postId).get();
        like.setPost(post);

        likeRepository.save(like);
        return responseManager.success(like);
    }

    @Override
    public ApiResponse<String> unLikeAPost(Long postId) throws UnauthorizedException, NotFoundException {
        if(httpSession.getAttribute("userId") == null)
            throw new UnauthorizedException("Please log in to unlike a post");
        
        Like userLike =likeRepository.findLikeByPostIdAndUser(postId,loggedInUser.findLoggedInUser());
        if(userLike == null)
            throw new NotFoundException("You didn't like this post");
        likeRepository.deleteById(userLike.getId());
        return responseManager.success("Unliked Successfully");
    }

    @Override
    public ApiResponse<Like> likeAComment(Long commentId) throws UnauthorizedException {
        if(httpSession.getAttribute("userId") == null)
            throw new UnauthorizedException("Please log in to like a comment");

        List<Like> likes = likeRepository.findLikesByCommentId(commentId);
        for(int i = 0; i < likes.size(); i++){
            if(likes.get(i).getUser() == loggedInUser.findLoggedInUser()){
                throw new UnauthorizedException("You can't like this comment more than once");
            }
        }

        Like like = new Like();
        like.setUser(loggedInUser.findLoggedInUser());

        Comment comment = commentRepository.findById(commentId).get();
        like.setComment(comment);

        likeRepository.save(like);
        return responseManager.success(like);
    }

    @Override
    public ApiResponse<String> unLikeAComment(Long commentId) throws UnauthorizedException, NotFoundException {
        if(httpSession.getAttribute("userId") == null)
            throw new UnauthorizedException("Please log in to unlike a comment");

        Like userLike = likeRepository.findLikeByCommentIdAndUser(commentId,loggedInUser.findLoggedInUser());
        if(userLike == null)
            throw new NotFoundException("You didn't like this comment");
        
        likeRepository.deleteById(userLike.getId());
        return responseManager.success("Comment unliked successfully");
    }

}

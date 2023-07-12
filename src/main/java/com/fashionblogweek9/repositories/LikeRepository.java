package com.fashionblogweek9.repositories;

import com.fashionblogweek9.models.Like;
import com.fashionblogweek9.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    List<Like> findLikesByPostId(Long postId);
    Like findLikeByPostIdAndUser(Long postId, User user);
    List<Like> findLikesByCommentId(Long commentId);
    Like findLikeByCommentIdAndUser(Long commentId, User user);

}

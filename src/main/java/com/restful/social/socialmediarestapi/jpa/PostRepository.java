package com.restful.social.socialmediarestapi.jpa;

import com.restful.social.socialmediarestapi.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}

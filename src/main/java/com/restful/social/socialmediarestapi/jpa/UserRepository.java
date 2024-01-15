package com.restful.social.socialmediarestapi.jpa;

import com.restful.social.socialmediarestapi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}

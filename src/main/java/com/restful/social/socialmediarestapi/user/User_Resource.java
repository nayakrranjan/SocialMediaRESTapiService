package com.restful.social.socialmediarestapi.user;

import com.restful.social.socialmediarestapi.Exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class User_Resource {
    private UserDAO_Service service;
    public User_Resource(UserDAO_Service service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return service.getAll();
    }

    @GetMapping("/users/{id}")
    public User getUsersById(@PathVariable int id) {
        User user = service.findById(id);

        if (user == null)
            throw new UserNotFoundException("Id="+id);

        return user;
    }

    @DeleteMapping("/users/{id}")
    public void removeUserById(@PathVariable int id) {
        service.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> newUser(@Valid @RequestBody User user) {
        User newUser = service.createUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newUser.getId()).toUri();
        try {
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }
}

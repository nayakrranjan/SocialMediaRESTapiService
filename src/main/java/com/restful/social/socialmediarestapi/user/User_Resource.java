package com.restful.social.socialmediarestapi.user;

import com.restful.social.socialmediarestapi.Exceptions.UserNotFoundException;
import com.restful.social.socialmediarestapi.jpa.PostRepository;
import com.restful.social.socialmediarestapi.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class User_Resource {
    private final UserRepository userRepo;
    private final PostRepository postRepo;
    public User_Resource(UserRepository userRepo, PostRepository postRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getUsersById(@PathVariable int id) {
        Optional<User> user = userRepo.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("Id="+id);

        var entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link =
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers()
                );
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @DeleteMapping("/users/{id}")
    public void removeUserById(@PathVariable int id) {
        userRepo.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> newUser(@Valid @RequestBody User user) {
        User newUser = userRepo.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newUser.getId()).toUri();
        try {
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> getPostsById(@PathVariable int id) {
        Optional<User> user = userRepo.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("Id="+id);

        return user.get().getPosts();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepo.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("Id="+id);

        post.setUser(user.get());
        var newPost = postRepo.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newPost.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

//    private static MappingJacksonValue filter(MappingJacksonValue mappingJacksonValue) {
//        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "birthDate");
//        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserFilter", filter);
//        mappingJacksonValue.setFilters(filterProvider);
//        return mappingJacksonValue;
//    }
}

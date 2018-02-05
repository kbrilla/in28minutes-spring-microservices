package in28minutes.rest.webservices.restfulwebservices.controller;

import in28minutes.rest.webservices.restfulwebservices.domain.Post;
import in28minutes.rest.webservices.restfulwebservices.domain.User;
import in28minutes.rest.webservices.restfulwebservices.error.UserNotFoundException;
import in28minutes.rest.webservices.restfulwebservices.repos.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import in28minutes.rest.webservices.restfulwebservices.repos.UserRepository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    private static List<User> users = new ArrayList<>(
            Arrays.asList(
                    new User(0L, "U 1", new Date()),
                    new User(1L, "U 2", new Date()),
                    new User(2L, "U 3", new Date())
            )
    );

    @GetMapping("")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Resource<User> retrieveUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id-" + id));

        Resource<User> resource = new Resource<>(user);
        resource.add(linkTo(methodOn(this.getClass()).retrieveAllUsers()).withRel("all-users"));
        resource.add(linkTo(methodOn(this.getClass()).retrieveUserPosts(id)).withRel("posts"));
        return resource;

    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id-" + id));
        userRepository.delete(user);
    }

    @PostMapping()
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User createdUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable Long id, @Valid @RequestBody Post post) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id-" + id));

        post.setUser(user);
        Post createdPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}/posts/{id}")
                .buildAndExpand(createdPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/posts")
    public List<Post> retrieveUserPosts(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id-" + id));

       // Resource<Post> resource = new Resource<Post>();
       // resource.add(linkTo(methodOn(this.getClass()).retrieveUser(id)).withRel("user"));
        return user.getPosts();

    }

}

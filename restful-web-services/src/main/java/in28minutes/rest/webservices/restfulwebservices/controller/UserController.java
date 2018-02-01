package in28minutes.rest.webservices.restfulwebservices.controller;

import in28minutes.rest.webservices.restfulwebservices.domain.User;
import in28minutes.rest.webservices.restfulwebservices.error.UserNotFoundException;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {
    private static List<User> users = new ArrayList<>(
            Arrays.asList(
                    new User(0L, "U 1", new Date()),
                    new User(1L, "U 2", new Date()),
                    new User(2L, "U 3", new Date())
            )
    );

    @GetMapping("")
    public List<User> retrieveAllUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public Resource<User> retrieveUser(@PathVariable Long id) {
        User user = users.stream().filter(u -> u.getId() == id).findFirst().get();

        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        Resource<User> resource = new Resource<>(user);
        resource.add(linkTo(methodOn(this.getClass()).retrieveAllUsers()).withRel("all-users"));
        return resource;

    }
}

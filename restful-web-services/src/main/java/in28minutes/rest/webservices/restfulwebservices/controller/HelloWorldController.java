package in28minutes.rest.webservices.restfulwebservices.controller;

import org.springframework.web.bind.annotation.*;

@RestController()
public class HelloWorldController {
    @GetMapping("hello-world")
    public String helloWorld(){
        return "hello world";
    }

    @GetMapping("hello-world-bean")
    public HelloWorldBean helloWordBean() {
        return new HelloWorldBean("Hello Wolrld");
    }

    @GetMapping("hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable() String name) {
        return new HelloWorldBean(name);
    }
}

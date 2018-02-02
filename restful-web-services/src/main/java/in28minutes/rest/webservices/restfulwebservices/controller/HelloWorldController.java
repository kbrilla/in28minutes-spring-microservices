package in28minutes.rest.webservices.restfulwebservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController()
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("hello-world")
    public String helloWorld(@RequestHeader(value = "Accept-Language", required = false) Locale locale){
        return messageSource.getMessage("good.morning.message", null,  locale);
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

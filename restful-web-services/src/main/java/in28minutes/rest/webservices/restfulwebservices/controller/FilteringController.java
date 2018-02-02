package in28minutes.rest.webservices.restfulwebservices.controller;

import in28minutes.rest.webservices.restfulwebservices.domain.SomeBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filtering")
public class FilteringController {

    @GetMapping
    public SomeBean retrieveSomeBean(){
        return new SomeBean("value 1", "value 2", "value 2");
    }
}

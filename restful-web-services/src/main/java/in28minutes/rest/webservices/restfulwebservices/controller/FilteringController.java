package in28minutes.rest.webservices.restfulwebservices.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import in28minutes.rest.webservices.restfulwebservices.domain.SomeBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter.filterOutAllExcept;

@RestController
@RequestMapping("/filtering")
public class FilteringController {

    public static final FilterProvider FILTERS = new SimpleFilterProvider()
            .addFilter("SomeBeanFilter", filterOutAllExcept("s1", "s2"));

    @GetMapping
    public MappingJacksonValue retrieveSomeBean() {
        SomeBean someBean = new SomeBean("value 1", "value 2", "value 2");

        return  getMappingJacksonValue(someBean);
    }

    private MappingJacksonValue getMappingJacksonValue(Object someBean) {
        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        mapping.setFilters(FILTERS);
        return mapping;
    }
}

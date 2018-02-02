package in28minutes.rest.webservices.restfulwebservices.domain;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.context.annotation.Bean;

//@JsonIgnoreProperties(value = {"s2"})
@JsonFilter("SomeBeanFilter")
public class SomeBean {
    private final String s;
    private final String s1;

//    @JsonIgnore
    private final String s2;

    public SomeBean(String s, String s1, String s2) {
        this.s = s;
        this.s1 = s1;
        this.s2 = s2;
    }


    public String getS() {
        return s;
    }

    public String getS1() {
        return s1;
    }

    public String getS2() {
        return s2;
    }
}

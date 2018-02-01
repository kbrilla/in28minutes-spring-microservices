package in28minutes.rest.webservices.restfulwebservices.error;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String s) {
        super(s);
    }
}

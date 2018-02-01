package in28minutes.rest.webservices.restfulwebservices.error;

import java.util.Date;

public class ExceptionResponse {
    private final Date date;
    private final String message;
    private final String description;

    public ExceptionResponse(Date date, String message, String description) {
        this.date = date;
        this.message = message;
        this.description = description;
    }
    
    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}

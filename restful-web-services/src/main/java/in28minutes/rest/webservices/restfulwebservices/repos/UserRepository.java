package in28minutes.rest.webservices.restfulwebservices.repos;

import in28minutes.rest.webservices.restfulwebservices.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

package pl.coderslab.surveyapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.surveyapp.entity.User;

@Repository
interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

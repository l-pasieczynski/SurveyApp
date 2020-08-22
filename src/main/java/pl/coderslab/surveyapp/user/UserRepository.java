package pl.coderslab.surveyapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.surveyapp.survey.Survey;

import java.util.List;

@Repository
interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE " +
            "(:username is null or u.username = :username) and " +
            "(:email is null or u.email = :email) and " +
            "(:education is null or u.education = :education) and" +
            "(:gender is null or u.gender = :gender) and " +
            "(:placeOfLiving is null or u.placeOfLiving = :placeOfLiving) and " +
            "(:survey is null or u.survey = :survey) and" +
            "(:active is null or u.active = :active)")
    List<User> findByParams(String education, String email, String gender, String placeOfLiving, Survey survey, String username, boolean active);
}

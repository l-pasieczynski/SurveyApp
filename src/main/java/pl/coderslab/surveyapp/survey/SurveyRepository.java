package pl.coderslab.surveyapp.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.surveyapp.user.User;

import java.util.List;

@Repository
interface SurveyRepository extends JpaRepository <Survey, Long> {

    Survey findByName(String name);

    List<Survey> findAllSurveyByUserOrderByCreatedDesc(User user);

}

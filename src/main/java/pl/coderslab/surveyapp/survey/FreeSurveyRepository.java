package pl.coderslab.surveyapp.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface FreeSurveyRepository extends JpaRepository<FreeSurvey, Long> {

    List<FreeSurvey> findAllByActiveOrderByCreatedDesc(boolean active);

}

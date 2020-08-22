package pl.coderslab.surveyapp.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SurveyRepository extends JpaRepository <Survey, Long> {
//    Survey findBySurveyName(String name);
}

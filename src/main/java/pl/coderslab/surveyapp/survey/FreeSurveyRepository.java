package pl.coderslab.surveyapp.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface FreeSurveyRepository extends JpaRepository<FreeSurvey, Long> {
}

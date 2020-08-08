package pl.coderslab.surveyapp.answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.surveyapp.entity.Answer;

@Repository
interface AnswerRepository extends JpaRepository<Answer, Long>
{
}

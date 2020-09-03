package pl.coderslab.surveyapp.answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.surveyapp.question.Question;

import java.util.List;

@Repository
interface AnswerRepository extends JpaRepository<Answer, Long> {


    Answer findByQuestion(Question question);
}

package pl.coderslab.surveyapp.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findByQuestion(String question);
}

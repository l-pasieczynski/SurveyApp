package pl.coderslab.surveyapp.answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AnswerRepository extends JpaRepository<Answer, Long>
{
}

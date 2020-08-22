package pl.coderslab.surveyapp.question;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void save(List<Question> question) {
        question.forEach(questionRepository::save);
    }
}

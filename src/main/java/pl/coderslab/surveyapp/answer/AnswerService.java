package pl.coderslab.surveyapp.answer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public void save(List<Answer> answer) {
        answer.forEach(answerRepository::save);
    }
}

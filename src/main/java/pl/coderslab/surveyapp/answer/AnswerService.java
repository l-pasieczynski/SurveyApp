package pl.coderslab.surveyapp.answer;

import org.springframework.stereotype.Service;
import pl.coderslab.surveyapp.question.Question;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> findAll(){
        return answerRepository.findAll();
    }


    public void save(List<Answer> answer) {
        answer.forEach(answerRepository::save);
    }

    public List<Answer> findAllByQuestion(List<Question> questions) {
        List<Answer> answerList = new ArrayList<>();
        for (Question question : questions){
            Answer answerByQuestion = answerRepository.findByQuestion(question);
            answerList.add(answerByQuestion);
        }
        return answerList;
    }
}

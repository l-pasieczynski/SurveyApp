package pl.coderslab.surveyapp.RestApi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.surveyapp.answer.Answer;
import pl.coderslab.surveyapp.question.Question;
import pl.coderslab.surveyapp.survey.Survey;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("survey/rest")
public class SurveyRestController {

    @GetMapping("/getSurvey")
    public Survey getSurvey(){
        Survey survey = new Survey();
        survey.setId(1L);
        survey.setName("Ankieta testowa");
        survey.setActive(true);

        Question q = new Question();
        q.setQuestion("Twoj ulubiony kolor");
        q.setQuestionType("radio");
        Answer a = new Answer();
        Answer a1 = new Answer();
        a.setAnswer("Czerwony");
        a1.setAnswer("Bialy");
        List<Answer> answers = new ArrayList<>();
        answers.add(a);
        answers.add(a1);
        q.setAnswer(answers);

        Question q1 = new Question();
        q1.setQuestion("Twoj ulubiony pasztet");
        q1.setQuestionType("checkbox");
        Answer ax = new Answer();
        Answer ax1 = new Answer();
        ax.setAnswer("Zajeczy");
        ax1.setAnswer("Z krolika");
        List<Answer> answers1 = new ArrayList<>();
        answers1.add(ax);
        answers1.add(ax1);
        q1.setAnswer(answers1);

        Question q2 = new Question();
        q2.setQuestion("Co lubisz najbardziej");
        q2.setQuestionType("text");

        List<Question> questions = new ArrayList<>();
        questions.add(q);
        questions.add(q1);
        questions.add(q2);
        survey.setQuestions(questions);
        return survey;
    }
}

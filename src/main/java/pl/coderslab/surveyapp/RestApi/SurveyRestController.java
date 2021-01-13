package pl.coderslab.surveyapp.RestApi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.surveyapp.answer.Answer;
import pl.coderslab.surveyapp.question.Question;
import pl.coderslab.surveyapp.survey.Survey;
import pl.coderslab.surveyapp.survey.SurveyFacade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("survey/rest")
public class SurveyRestController {
    private final SurveyFacade surveyFacade;

    public SurveyRestController(SurveyFacade surveyFacade) {
        this.surveyFacade = surveyFacade;
    }

    @GetMapping("/getSurvey/{id}")
    public Survey getSurvey(@PathVariable Long id){

        Survey survey = surveyFacade.findById(id);
        List<Question> surveyQuestions = survey.getQuestions();

        for(int i = 0;i<surveyQuestions.size();i++){

            String s = surveyQuestions.get(i).getQuery();
            String[] parts = s.split(",");

            List<Answer> answerList = new ArrayList<>();
            for(int j=0;j< parts.length;j++){
                Answer queryAnswer= new Answer();
                queryAnswer.setAnswer(parts[j]);
                answerList.add(queryAnswer);
            }
            surveyQuestions.get(i).setAnswer(answerList);
        }
        return survey;
    }
}

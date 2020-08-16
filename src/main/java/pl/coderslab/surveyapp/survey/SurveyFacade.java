package pl.coderslab.surveyapp.survey;

import org.springframework.stereotype.Component;
import pl.coderslab.surveyapp.answer.AnswerService;
import pl.coderslab.surveyapp.question.QuestionService;

import java.util.List;

@Component
public class SurveyFacade {

    private final SurveyService surveyService;
    private final FreeSurveyService freeSurveyService;
    private final AnswerService answerService;
    private final QuestionService questionService;

    public SurveyFacade(SurveyService surveyService, FreeSurveyService freeSurveyService, AnswerService answerService, QuestionService questionService) {
        this.surveyService = surveyService;
        this.freeSurveyService = freeSurveyService;
        this.answerService = answerService;
        this.questionService = questionService;
    }

    public List<FreeSurvey> findAllFreeSurveys (){
        return freeSurveyService.findAll();
    }

    public FreeSurvey findFreeSurveyById(Long id){
        return freeSurveyService.findById(id);
    }

    public void saveFreeSurvey(FreeSurvey survey) {
        freeSurveyService.save(survey);
    }

    public void deleteFreeSurvey(Long id) {
        freeSurveyService.delete(id);
    }

    public List<Survey> findAll() {
        return surveyService.findAll();
    }

    public Survey findById(Long id) {
        return surveyService.findById(id);
    }

    public void saveSurvey(Survey survey) {
        surveyService.save(survey);
    }
}

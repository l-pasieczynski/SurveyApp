package pl.coderslab.surveyapp.survey;

import org.springframework.stereotype.Component;
import pl.coderslab.surveyapp.answer.Answer;
import pl.coderslab.surveyapp.answer.AnswerService;
import pl.coderslab.surveyapp.question.Question;
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

    public List<FreeSurvey> findAllFreeSurveys() {
        return freeSurveyService.findAll();
    }

    public List<FreeSurvey> findAllActiveFreeSurvey(boolean active) {
        return freeSurveyService.findAllActive(active);
    }

    public FreeSurvey findFreeSurveyById(Long id) {
        return freeSurveyService.findById(id);
    }

    public void saveFreeSurvey(FreeSurvey survey, List<Question> questions) {
        survey.setActive(true);
        survey.setQuestionCount(survey.getQuestions().size());
        questionService.save(questions);
        freeSurveyService.save(survey);
    }

    public void deleteFreeSurvey(Long id) {
        freeSurveyService.delete(id);
    }

    public void deactivateFreeSurvey(Long id) {
        FreeSurvey freeSurveyToDeactivate = findFreeSurveyById(id);
        freeSurveyToDeactivate.setActive(false);
        freeSurveyService.save(freeSurveyToDeactivate);
    }


    public List<Survey> findAll() {
        return surveyService.findAll();
    }

    public Survey findById(Long id) {
        return surveyService.findById(id);
    }

    public void saveSurvey(Survey survey, List<Question> question) {
        surveyService.save(survey);
        questionService.save(question);
    }


    public void deactivate(Long id) {
        Survey surveyToDeactivate = surveyService.findById(id);
        surveyToDeactivate.setActive(false);
        surveyService.save(surveyToDeactivate);
    }

    public void delete(Long id) {
        surveyService.delete(id);
    }

    Survey findBySurveyName(String name){return surveyService.findBySurveyName(name);}
}

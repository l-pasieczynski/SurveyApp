package pl.coderslab.surveyapp.survey;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SurveyFacade {

    private final SurveyService surveyService;
    private final FreeSurveyService freeSurveyService;

    public SurveyFacade(SurveyService surveyService, FreeSurveyService freeSurveyService) {
        this.surveyService = surveyService;
        this.freeSurveyService = freeSurveyService;
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
}

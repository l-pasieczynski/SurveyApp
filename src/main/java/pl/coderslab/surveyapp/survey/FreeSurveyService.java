package pl.coderslab.surveyapp.survey;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class FreeSurveyService {

    private final FreeSurveyRepository freeSurveyRepository;

    public FreeSurveyService(FreeSurveyRepository freeSurveyRepository) {
        this.freeSurveyRepository = freeSurveyRepository;
    }


    public List<FreeSurvey> findAll() {
        return freeSurveyRepository.findAll();
    }

    public FreeSurvey findById(Long id) {
        return freeSurveyRepository.getOne(id);
    }

    public void save(FreeSurvey survey) {
        survey.setNumberOfParticipant(survey.getNumberOfParticipant() + 1);
        freeSurveyRepository.save(survey);
    }

    public void create(){
        FreeSurvey survey = new FreeSurvey();
        survey.setNumberOfParticipant(0);
    }

    public void delete(Long id) {
        freeSurveyRepository.deleteById(id);
    }


    public List<FreeSurvey> findAllActive(boolean active) {
        return freeSurveyRepository.findAllByActiveOrderByCreatedDesc(active);
    }

    public void deactivate(Long id) {
        FreeSurvey surveyToDeactivate = freeSurveyRepository.getOne(id);
        if (surveyToDeactivate.isActive()){
            surveyToDeactivate.setActive(false);
        } else {
            surveyToDeactivate.setActive(true);
        }
        freeSurveyRepository.save(surveyToDeactivate);

    }
}

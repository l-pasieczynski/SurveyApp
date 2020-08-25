package pl.coderslab.surveyapp.survey;

import org.springframework.stereotype.Service;
import pl.coderslab.surveyapp.EntityNotFoundException;

import java.util.List;

@Service
class SurveyService {

    private final SurveyRepository surveyRepository;

    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public Survey findById(Long id) {
        return surveyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Survey.class.getSimpleName()));
    }

    public List<Survey> findAll() {
        return surveyRepository.findAll();
    }

    public void save(Survey survey) {
        surveyRepository.save(survey);
    }

    public void delete(Long id) {
        surveyRepository.deleteById(id);
    }

    Survey findBySurveyName(String name){return surveyRepository.findByName(name);}
}

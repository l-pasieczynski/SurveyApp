package pl.coderslab.surveyapp.survey;

import org.springframework.stereotype.Service;
import pl.coderslab.surveyapp.EntityNotFoundException;

@Service
class SurveyService {

    private final SurveyRepository surveyRepository;

    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public Survey findById(Long id) {
        return surveyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Survey.class.getSimpleName()));
    }
}

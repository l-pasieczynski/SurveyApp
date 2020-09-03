package pl.coderslab.surveyapp.admin;

import org.springframework.stereotype.Service;
import pl.coderslab.surveyapp.answer.Answer;
import pl.coderslab.surveyapp.question.Question;
import pl.coderslab.surveyapp.survey.FreeSurvey;
import pl.coderslab.surveyapp.survey.Survey;
import pl.coderslab.surveyapp.survey.SurveyFacade;
import pl.coderslab.surveyapp.user.User;
import pl.coderslab.surveyapp.user.UserSearch;
import pl.coderslab.surveyapp.user.UserService;

import java.util.List;

@Service
class AdminService {

    private final AdminRepository adminRepository;
    private final UserService userService;
    private final SurveyFacade surveyFacade;

    public AdminService(AdminRepository adminRepository, UserService userService, SurveyFacade surveyFacade) {
        this.adminRepository = adminRepository;
        this.userService = userService;
        this.surveyFacade = surveyFacade;
    }

    public List<User> getAllUsers(){
        return userService.findAll();
    }

    public User getUserById(Long id) {
        return userService.findById(id);
    }

    public List<User> getUsersByRequestParam(UserSearch userSearch) {
        return userService.findAllByRequestParam(userSearch);
    }

    public void deactivateUser(Long id) {
        userService.deactivate(id);
    }

    public void createFreeSurvey(FreeSurvey freeSurvey, List<Question> questions) {
        surveyFacade.saveFreeSurvey(freeSurvey, questions);
    }

    public void deactivateFreeSurvey(Long id) {
        surveyFacade.deactivateFreeSurvey(id);
    }

    public void deleteFreeSurvey(Long id) {
        surveyFacade.deleteFreeSurvey(id);
    }

    public List<FreeSurvey> findAllFreeSurveys() {
        return surveyFacade.findAllFreeSurveys();
    }

    public FreeSurvey findFreeSurveyById(Long id) {
        return surveyFacade.findFreeSurveyById(id);
    }

    public List<Survey> findAllSurveys() {
        return surveyFacade.findAll();
    }

    public Survey findSurveyById(Long id) {
        return surveyFacade.findById(id);
    }

    public void deactivateSurvey(Long id) {
        surveyFacade.deactivate(id);
    }

    public void deleteSurvey(Long id) {
        surveyFacade.delete(id);
    }

    public void createSurvey(Survey survey, List<Question> question) {
        surveyFacade.saveSurvey(survey, question);
    }

    public List<Survey> findSurveysByUser(User user) {
        User byId = userService.findById(user.getId());
        return byId.getSurvey();
    }

    public List<Question> getFreSurveyQuestionList(Long id) {
        return surveyFacade.findFreeSurveyQuestionList(id);
    }

    public List<Question> getSurveyQuestionList(Long id) {
        return surveyFacade.findSurveyQuestionList(id);
    }

    public List<User> getSurveyUsers(Long id) {
        return surveyFacade.getSurveyUsers(id);
    }
}

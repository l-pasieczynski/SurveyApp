package pl.coderslab.surveyapp.admin;

import org.springframework.stereotype.Service;
import pl.coderslab.surveyapp.survey.SurveyFacade;
import pl.coderslab.surveyapp.user.User;
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
}

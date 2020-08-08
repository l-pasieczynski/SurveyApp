package pl.coderslab.surveyapp.admin;

import org.springframework.stereotype.Controller;
import pl.coderslab.surveyapp.survey.SurveyFacade;
import pl.coderslab.surveyapp.user.UserService;

@Controller
public class AdminController {

    private final UserService userService;
    private final SurveyFacade surveyFacade;

    public AdminController(UserService userService, SurveyFacade surveyFacade) {
        this.userService = userService;
        this.surveyFacade = surveyFacade;
    }

}

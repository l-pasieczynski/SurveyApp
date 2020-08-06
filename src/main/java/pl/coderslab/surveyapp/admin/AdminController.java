package pl.coderslab.surveyapp.admin;

import org.springframework.stereotype.Controller;
import pl.coderslab.surveyapp.survey.SurveyFacade;
import pl.coderslab.surveyapp.user.UserFacade;

@Controller
public class AdminController {

    private final UserFacade userFacade;
    private final SurveyFacade surveyFacade;

    public AdminController(UserFacade userFacade, SurveyFacade surveyFacade) {
        this.userFacade = userFacade;
        this.surveyFacade = surveyFacade;
    }
}

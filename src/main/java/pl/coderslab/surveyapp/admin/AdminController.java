package pl.coderslab.surveyapp.admin;

import org.springframework.stereotype.Controller;
import pl.coderslab.surveyapp.survey.SurveyFacade;
import pl.coderslab.surveyapp.user.UserService;

@Controller
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
}

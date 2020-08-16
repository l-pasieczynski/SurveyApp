package pl.coderslab.surveyapp.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.surveyapp.survey.SurveyFacade;
import pl.coderslab.surveyapp.user.UserService;

@Controller
@RequestMapping("/app/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/home")
    public String admin(){
        return "admin/home";
    }

    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute("users", adminService.getAllUsers());
        return "admin/users";
    }
}

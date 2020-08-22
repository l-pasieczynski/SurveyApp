package pl.coderslab.surveyapp.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.surveyapp.answer.Answer;
import pl.coderslab.surveyapp.question.Question;
import pl.coderslab.surveyapp.survey.FreeSurvey;
import pl.coderslab.surveyapp.survey.Survey;
import pl.coderslab.surveyapp.user.User;
import pl.coderslab.surveyapp.user.UserSearch;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/app/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/home")
    public String admin() {
        return "admin/home";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", adminService.getAllUsers());
        model.addAttribute("search", new UserSearch());
        return "admin/users";
    }

    @GetMapping("/users/search")
    public String users(@ModelAttribute("search") UserSearch searchedUsers, Model model) {
        model.addAttribute("users", adminService.getUsersByRequestParam(searchedUsers));
        return "admin/search";
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        User user = adminService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("userSurveys", adminService.findSurveysByUser(user));
        return "admin/userDetails";
    }

    @PostMapping("/users/deactivate/{id}")
    public String deactivateUser(@PathVariable Long id) {
        adminService.deactivateUser(id);
        return "redirect:../";
    }

    @GetMapping("/freeSurveys")
    public String freeSurveys(Model model) {
        model.addAttribute("freeSurveys", adminService.findAllFreeSurveys());
        return "admin/freeSurveys";
    }

    @GetMapping("/freeSurveys/{id}")
    public String freeSurveysById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("freeSurveys", adminService.findFreeSurveyById(id));
        return "admin/freeSurveys/freeSurveyDetails";
    }

    @PostMapping("/freeSurveys/{id}/deactivate")
    public String deactivateFreeSurveys(@PathVariable Long id) {
        adminService.deactivateFreeSurvey(id);
        return "redirect:../admin/freeSurveys";
    }

    @PostMapping("/freeSurveys/{id}/delete")
    public String deleteFreeSurveys(@PathVariable Long id) {
        adminService.deleteFreeSurvey(id);
        return "redirect:../admin/freeSurveys";
    }

    @GetMapping("/freeSurveys/add")
    public String addFreeSurveys(Model model) {
        model.addAttribute("freeSurveys", new FreeSurvey());
        model.addAttribute("question", new ArrayList<Question>());
        return "admin/freeSurveys/add/freeSurveysForm";
    }

    @PostMapping("freeSurveys/add")
    public String addFreeSurveysPost(@ModelAttribute("freeSurveys") FreeSurvey freeSurvey,
                                     @ModelAttribute("question") List<Question> question) {
        adminService.createFreeSurvey(freeSurvey, question);
        return "redirect:../admin/freeSurveys";
    }

    @GetMapping("/surveys")
    public String surveys(Model model) {
        model.addAttribute("surveys", adminService.findAllSurveys());
        return "admin/surveys";
    }

    @GetMapping("/surveys/{id}")
    public String surveysById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("surveys", adminService.findSurveyById(id));
        return "admin/surveys/surveysDetails";
    }

    @PostMapping("/surveys/{id}/deactivate")
    public String deactivateSurveys(@PathVariable Long id) {
        adminService.deactivateSurvey(id);
        return "redirect:../admin/surveys";
    }

    @PostMapping("/surveys/{id}/delete")
    public String deleteSurveys(@PathVariable Long id) {
        adminService.deleteSurvey(id);
        return "redirect:../admin/surveys";
    }

    @GetMapping("/surveys/add")
    public String addSurveys(Model model) {
        model.addAttribute("surveys", new Survey());
        model.addAttribute("question", new ArrayList<Question>());
        return "admin/surveys/add/SurveysForm";
    }

    @PostMapping("surveys/add")
    public String addSurveysPost(@ModelAttribute("surveys") Survey survey,
                                 @ModelAttribute("question") List<Question> question){
        adminService.createSurvey(survey, question);
        return "redirect:../admin/freeSurveys";
    }

    @GetMapping("/results")
    public String results(Model model) {
        model.addAttribute("allSurveys", adminService.findAllSurveys());
        model.addAttribute("allFreeSurveys", adminService.findAllFreeSurveys());
        return "admin/results";
    }

    @GetMapping("/results/survey/{id}")
    public String surveyResults(@PathVariable Long id) {
        return "admin/results/surveys";
    }

    @GetMapping("/results/freeSurvey/{id}")
    public String freeSurveyResults(@PathVariable Long id) {
        return "admin/results/freeSurveys";
    }

    //TODO obsługa tworzenia PDF z przeglądanych wynikow


}

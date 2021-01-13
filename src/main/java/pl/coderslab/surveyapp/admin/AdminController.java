package pl.coderslab.surveyapp.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        model.addAttribute("questions", adminService.getFreSurveyQuestionList(id));
        return "admin/freeSurveyDetails";
    }

    @PostMapping("/freeSurveys/deactivate/{id}")
    public String deactivateFreeSurveys(@PathVariable Long id) {
        adminService.deactivateFreeSurvey(id);
        return "redirect:../";
    }

    @PostMapping("/freeSurveys/delete/{id}")
    public String deleteFreeSurveys(@PathVariable Long id) {
        adminService.deleteFreeSurvey(id);
        return "redirect:../";
    }

    @GetMapping("/freeSurveys/add")
    public String addFreeSurveys(Model model) {
        model.addAttribute("freeSurveys", new FreeSurvey());
        model.addAttribute("question", new ArrayList<Question>());
        return "admin/add/freeSurveysForm";
    }

    @PostMapping("freeSurveys/add")
    public String addFreeSurveysPost(@ModelAttribute("freeSurveys") FreeSurvey freeSurvey,
                                     @ModelAttribute("question") ArrayList<Question> question) {
        adminService.createFreeSurvey(freeSurvey, question);
        return "redirect:../";
    }

    @GetMapping("/surveys")
    public String surveys(Model model) {
        model.addAttribute("surveys", adminService.findAllSurveys());
        return "admin/surveys";
    }

    @GetMapping("/surveys/{id}")
    public String surveysById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("surveys", adminService.findSurveyById(id));
        model.addAttribute("questions", adminService.getSurveyQuestionList(id));
        model.addAttribute("users", adminService.getSurveyUsers(id));
        return "admin/surveysDetails";
    }

    @PostMapping("/surveys/deactivate/{id}")
    public String deactivateSurveys(@PathVariable Long id) {
        adminService.deactivateSurvey(id);
        return "redirect:../";
    }

    @PostMapping("/surveys/delete/{id}")
    public String deleteSurveys(@PathVariable Long id) {
        adminService.deleteSurvey(id);
        return "redirect:../";
    }

    @GetMapping("/surveys/add")
    public String addSurveys(Model model) {
        model.addAttribute("surveys", new Survey());
        model.addAttribute("question1", new Question());
        model.addAttribute("question2", new Question());
        model.addAttribute("question3", new Question());
        model.addAttribute("question4", new Question());
        model.addAttribute("question5", new Question());
        model.addAttribute("question6", new Question());
        return "admin/add/surveysForm";
    }

    @PostMapping("surveys/add")
    public String addSurveysPost(@ModelAttribute("surveys") Survey survey,
                                 @ModelAttribute("question1") Question question1,
                                 @ModelAttribute("question2") Question question2,
                                 @ModelAttribute("question3") Question question3,
                                 @ModelAttribute("question4") Question question4,
                                 @ModelAttribute("question5") Question question5,
                                 @ModelAttribute("question6") Question question6){

        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);
        questions.add(question6);

        survey.setQuestionCount(questions.size());
        survey.setQuestions(questions);
        survey.setActive(true);
        adminService.createSurvey(survey, questions);
        return "redirect:../surveys";
    }

    @GetMapping("/results")
    public String results(Model model) {
        model.addAttribute("allSurveys", adminService.findAllSurveys());
        model.addAttribute("allFreeSurveys", adminService.findAllFreeSurveys());
        return "admin/results";
    }

    @GetMapping("/results/survey/{id}")
    public String surveyResults(@PathVariable Long id, Model model) {
        model.addAttribute("result", adminService.findSurveyById(id));
        return "admin/results/surveys";
    }

    @GetMapping("/results/freeSurvey/{id}")
    public String freeSurveyResults(@PathVariable Long id, Model model) {
        model.addAttribute("result", adminService.findFreeSurveyById(id));
        return "admin/results/freeSurveys";
    }

    //TODO obsługa tworzenia PDF z przeglądanych wynikow


}

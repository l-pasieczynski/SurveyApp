package pl.coderslab.surveyapp.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.surveyapp.survey.FreeSurvey;
import pl.coderslab.surveyapp.survey.SurveyFacade;

@Controller
@RequestMapping("/")
class HomeController {

    private final UserService userService;
    private final SurveyFacade surveyFacade;

    public HomeController(UserService userService, SurveyFacade surveyFacade) {
        this.userService = userService;
        this.surveyFacade = surveyFacade;
    }

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("")
    public String registerUser(@RequestBody User user) {
        userService.saveUser(user);
        return "redirect:login";
    }

    @GetMapping("about")
    public String about() {
        return "about";
    }

    @GetMapping("contact")
    public String contact() {
        return "contact";
    }


    @GetMapping("surveys")
    public String surveys(Model model) {
        model.addAttribute("surveys", surveyFacade.findAllFreeSurveys());
        return "surveys";
    }

    @GetMapping("surveys/{id}")
    public String freeSurvey(@PathVariable("id") Long id, Model model) {
        model.addAttribute("surveys", surveyFacade.findFreeSurveyById(id));
        return "surveys/${id}";
    }

    @PostMapping("surveys/{id}")
    public String freeSurveyPost(@RequestBody FreeSurvey freeSurvey) {
        surveyFacade.saveFreeSurvey(freeSurvey);
        return "surveys";
    }
}
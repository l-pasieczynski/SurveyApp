package pl.coderslab.surveyapp.controller;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.surveyapp.survey.FreeSurvey;
import pl.coderslab.surveyapp.survey.SurveyFacade;
import pl.coderslab.surveyapp.user.User;
import pl.coderslab.surveyapp.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/")
class HomeController {

    private final UserService userService;
    private final SurveyFacade surveyFacade;

    public HomeController(UserService userService, SurveyFacade surveyFacade) {
        this.userService = userService;
        this.surveyFacade = surveyFacade;
    }

    private ModelAndView modelAndView = new ModelAndView();

    @PostMapping(value = "register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void registerUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PostMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User loginUser(@RequestBody User user){
        userService.loginUser(user);
        return user;
    }

    @PostMapping(value = "surveys/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void freeSurveyPost(@RequestBody FreeSurvey freeSurvey) {
        surveyFacade.saveFreeSurvey(freeSurvey);
    }

    @GetMapping
    public ModelAndView index () {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("about")
    public ModelAndView about() {
        modelAndView.setViewName("about");
        return modelAndView;
    }

    @GetMapping("contact")
    public ModelAndView contact() {
        modelAndView.setViewName("contact");
        return modelAndView;
    }

    @GetMapping("surveys")
    public ModelAndView surveys(Model model) {
        List<FreeSurvey> surveys = surveyFacade.findAllFreeSurveys();
        model.addAttribute("surveys", surveys);
        modelAndView.setViewName("surveys");
        return modelAndView;
    }

    @GetMapping("surveys/{id}")
    public String freeSurvey(@PathVariable("id") Long id, Model model) {
        model.addAttribute("surveys", surveyFacade.findFreeSurveyById(id));
        return "surveys/${id}";
    }
}

package pl.coderslab.surveyapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.coderslab.surveyapp.mail.ContactMessage;
import pl.coderslab.surveyapp.mail.Email;
import pl.coderslab.surveyapp.mail.EmailSender;
import pl.coderslab.surveyapp.question.Question;
import pl.coderslab.surveyapp.survey.FreeSurvey;
import pl.coderslab.surveyapp.survey.Survey;
import pl.coderslab.surveyapp.survey.SurveyFacade;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class RESTController {

    private final SurveyFacade surveyFacade;

    public RESTController(SurveyFacade surveyFacade, EmailSender emailSender, TemplateEngine templateEngine) {
        this.surveyFacade = surveyFacade;
    }

    private ModelAndView modelAndView = new ModelAndView();

//    @PostMapping(value = "/app/admin/surveys/add", consumes= MediaType.APPLICATION_JSON_VALUE ,headers = "content-type=application/x-www-form-urlencoded")
//    public ModelAndView addAppSurvey (@ModelAttribute Survey survey){
//        System.out.println(survey.toString());
//        System.out.println(survey.getQuestions());
//        ObjectMapper mapper = new ObjectMapper();
//        List<Question> questionJsonList = mapper.readValue(survey.getQuestions().toString(), new TypeReference<List<Question>>(){});
//        surveyFacade.saveSurvey(survey, survey.getQuestions());
//        modelAndView.setViewName("redirect:../surveys");
//        return modelAndView;
//    }


    @GetMapping("/surveys")
    public ModelAndView allSurveys() {
        modelAndView.addObject("surveys", surveyFacade.findAll());
        modelAndView.setViewName("admin/surveys");
        return modelAndView;
    }

    @GetMapping("surveys/{id}")
    public ModelAndView getSurveyById(@PathVariable Long id) {
        modelAndView.addObject("survey", surveyFacade.findById(id));
        modelAndView.setViewName("admin/surveys/" + id);
        return modelAndView;
    }

    @GetMapping("/freesurveys")
    public ModelAndView allFreeSurveys() {
        modelAndView.addObject("freeSurveys", surveyFacade.findAllFreeSurveys());
        modelAndView.setViewName("admin/freesurveys");
        return modelAndView;
    }

    @GetMapping("freesurveys/{id}")
    public ModelAndView getFreeSurveyById(@PathVariable Long id) {
        modelAndView.addObject("freeSurvey", surveyFacade.findFreeSurveyById(id));
        modelAndView.setViewName("admin/freesurveys/" + id);
        return modelAndView;
    }

    @DeleteMapping("admin/freesurveys/delete/{id}")
    public ModelAndView deleteFreeSurvey(@PathVariable Long id) {
        surveyFacade.deleteFreeSurvey(id);
        modelAndView.setViewName("surveys");
        return modelAndView;
    }

    @PutMapping("admin/freesurveys/edit/{id}")
    public ModelAndView editFreeSurvey(@RequestBody FreeSurvey freeSurvey, Long id) {
        modelAndView.setViewName("surveys");
        return modelAndView;
    }

//
//    @PostMapping("survey/{id}")
//    public ModelAndView addFreeSurvey(@RequestBody FreeSurvey freeSurvey) {
//        surveyFacade.saveFreeSurvey(freeSurvey);
//        modelAndView.setViewName("surveys");
//        return modelAndView;
//    }

//    @PostMapping(value = "contact", consumes = "application/json", produces = "application/json")
//    public ModelAndView sendContactMessage(@RequestBody ContactMessage contactMessage) {
//        emailSender.sendContactForm(contactMessage.getName(), contactMessage.getEmail(), contactMessage.getMessage());
//        modelAndView.setViewName("contact");
//        return modelAndView;
//    }

//    @PostMapping("/app/admin/email")
//    public ModelAndView sendEmail(@RequestBody Email email) {
//
//        Context context = new Context();
//        context.setVariable("header", email.getHeader());
//        context.setVariable("title", email.getTitle());
//        context.setVariable("description", email.getDescription());
//        String body = templateEngine.process("template", context);
//
//        emailSender.sendEmail(email.getUsersEmailAddress(), email.getSubject(), body);
//        modelAndView.setViewName("admin/home");
//        return modelAndView;
//    }


}

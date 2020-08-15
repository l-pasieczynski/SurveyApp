package pl.coderslab.surveyapp.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.surveyapp.survey.FreeSurvey;
import pl.coderslab.surveyapp.survey.SurveyFacade;

import javax.mail.Message;

@RestController
@RequestMapping("/")
public class RESTController {

    private final SurveyFacade surveyFacade;

    public RESTController(SurveyFacade surveyFacade) {
        this.surveyFacade = surveyFacade;
    }

    @PostMapping("surveys/{id}")
    public ModelAndView addFreeSurvey(@RequestBody FreeSurvey freeSurvey) {
        surveyFacade.saveFreeSurvey(freeSurvey);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("surveys");
        return modelAndView;
    }

    @DeleteMapping("admin/surveys/delete/{id}")
    public ModelAndView deleteFreeSurvey(@PathVariable Long id) {
        surveyFacade.deleteFreeSurvey(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("surveys");
        return modelAndView;
    }

    @PutMapping("admin/surveys/edit/{id}")
    public ModelAndView editFreeSurvey(@RequestBody FreeSurvey freeSurvey, Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("surveys");
        return modelAndView;
    }

    @PostMapping("contact")
    public ModelAndView sendContactMessage(@RequestBody Message message) {

        //TODO write logic to get message and send it to gmail

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("contact");
        return modelAndView;
    }


}

package pl.coderslab.surveyapp.survey;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/app/survey/create")
public class SurveyCreatingController {

    @GetMapping("")
    public String createSurvey(){
        return ("surveyCreating/surveyCreate");
    }

    @PostMapping("")
    public String createSurveyFirsStep(Model m, @RequestParam(required = false) String title, String questionType, String question, String quantityAnswer){
        List<Integer> quantity = new ArrayList<>();
        for(int i=0; i<Integer.parseInt(quantityAnswer);i++){
            quantity.add(i+1);
        }
        System.out.println(title+" "+questionType+" "+question+" "+quantityAnswer);
        m.addAttribute("howMuch",quantity);
        m.addAttribute("surveyTitle",title);
        m.addAttribute("question",question);

        return ("surveyCreating/surveyCreateSecondStep");
    }
    @PostMapping("/answer")
    public String createSurveySecondStep(@RequestParam List<String> answers, String action){
        System.out.println(action);
        System.out.println(answers);


        if(action.equals("next")){
            return ("surveyCreating/surveyCreateNextQuestion");
        }else{
            return ("surveyCreating/surveyCreate");
        }


    }


}

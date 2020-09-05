package pl.coderslab.surveyapp.pdf;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.surveyapp.answer.Answer;
import pl.coderslab.surveyapp.survey.SurveyFacade;

import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
@RequestMapping("/app/admin")
public class PdfController {

    private final SurveyFacade surveyFacade;

    public PdfController(SurveyFacade surveyFacade) {
        this.surveyFacade = surveyFacade;
    }

    @GetMapping(value = "/results/survey/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)

    public ResponseEntity<InputStreamResource> createPdf(@PathVariable Long id){

        List<Answer> results = surveyFacade.findAllAnswerBySurveyId(id);

        ByteArrayInputStream bis = GeneratePdfReport.resultsReport(results);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=resultsReport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}


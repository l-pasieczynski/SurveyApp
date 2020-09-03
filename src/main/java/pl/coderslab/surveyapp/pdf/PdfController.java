package pl.coderslab.surveyapp.pdf;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.surveyapp.answer.Answer;
import pl.coderslab.surveyapp.answer.AnswerService;

import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
@RequestMapping("/app/admin")
public class PdfController {

    private final AnswerService answerService;

    public PdfController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping(value = "/results/survey/{id}/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> createPdf(){

        List<Answer> results = answerService.findAll();

        ByteArrayInputStream bis = GeneratePdfReport.resultsReport(results);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=resultsreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}


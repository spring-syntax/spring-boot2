package ro.bytechnology.springboot2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ro.bytechnology.springboot2.model.Question;
import ro.bytechnology.springboot2.service.SurveyService;

import java.util.List;

@RestController
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping(value = "/survey/{id}/questions")
    public List<Question> surveyQuestions(@PathVariable String id){
        return surveyService.retrieveQuestions(id);
    }
}

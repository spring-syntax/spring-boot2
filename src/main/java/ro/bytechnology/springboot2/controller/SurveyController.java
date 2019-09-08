package ro.bytechnology.springboot2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.bytechnology.springboot2.model.Question;
import ro.bytechnology.springboot2.service.SurveyService;

import java.net.URI;
import java.util.List;

@RestController
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping(value = "/survey/{id}/questions")
    public List<Question> surveyQuestions(@PathVariable String id){
        return surveyService.retrieveQuestions(id);
    }

    @GetMapping(value = "/survey/{surveyId}/questions/{questionId}")
    public Question surveyQuestion(@PathVariable String surveyId, @PathVariable String questionId){
        return surveyService.retrieveQuestion(surveyId,questionId);
    }

    @PostMapping(value = "/survey/{surveyId}/questions")
    public ResponseEntity<?> addQuestionToSurvey(@PathVariable String surveyId, @RequestBody Question newQuestion){
        Question question = surveyService.addQuestion(surveyId, newQuestion);
        if(question==null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(question.getId()).toUri();

        return ResponseEntity.created(location).body(question);
    }
}

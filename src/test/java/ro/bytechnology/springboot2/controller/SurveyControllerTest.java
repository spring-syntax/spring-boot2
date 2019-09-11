package ro.bytechnology.springboot2.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ro.bytechnology.springboot2.model.Question;
import ro.bytechnology.springboot2.service.SurveyService;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SurveyController.class)
public class SurveyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SurveyService service;

    @Test
    public void addQuestionToSurvey() throws Exception {
        Question question= new Question("Question1","Description","CorrectAnswer", Arrays.asList("no","CorrectAnswer"));
        Mockito.when(service.addQuestion(Mockito.anyString(),Mockito.any())).thenReturn(question);
        String questionJson = "{\"id\":\"Question1\",\"description\":\"Description\",\"correctAnswer\":\"CorrectAnswer\"}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/survey/Survey1/questions")
                .accept(MediaType.APPLICATION_JSON)
                .content(questionJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        String headers = mvcResult.getResponse().getHeader(HttpHeaders.LOCATION);
        assertEquals("http://localhost/survey/Survey1/questions/Question1",headers);
    }

    @Test
    public void addQuestionWithNoSurvey() throws Exception {
        Mockito.when(service.addQuestion(Mockito.anyString(),Mockito.any())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/survey/Survey1/questions").accept(MediaType.APPLICATION_JSON)
        .content("{}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}

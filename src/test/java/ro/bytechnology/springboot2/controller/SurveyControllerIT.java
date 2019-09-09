package ro.bytechnology.springboot2.controller;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import ro.bytechnology.springboot2.SpringBoot2Application;
import ro.bytechnology.springboot2.model.Question;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = SpringBoot2Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate;
    private HttpHeaders headers;

    @BeforeEach
    public void setup(){
        restTemplate = new TestRestTemplate();
        headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testRetrieveSurveyQuestion() throws JSONException {

        //given
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        //when
        ResponseEntity<String> response = restTemplate.exchange(createUrl("/survey/Survey1/questions/Question1"),
                HttpMethod.GET, entity, String.class);

        //expected
        String expected = "{id:Question1,description:\"Largest Country in the World\",correctAnswer:Russia}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void retrieveAllSurveyQuestions() {

        ResponseEntity<List<Question>> response = restTemplate.exchange(
                createUrl("/survey/Survey1/questions"),
                HttpMethod.GET, new HttpEntity<>("DUMMY_DOESNT_MATTER",
                        headers),
                new ParameterizedTypeReference<List<Question>>() {
                });

        Question sampleQuestion = new Question("Question1",
                "Largest Country in the World", "Russia", Arrays.asList(
                "India", "Russia", "United States", "China"));

        assertTrue(response.getBody().contains(sampleQuestion));
    }


    @Test
    public void createSurveyQuestion() {
        Question question = new Question("DOESN'T MATTER", "Smallest Number",
                "1", Arrays.asList("1", "2", "3", "4"));

        ResponseEntity<String> response = restTemplate.exchange(
                createUrl("/survey/Survey1/questions/"), HttpMethod.POST,
                new HttpEntity<>(question, headers), String.class);

        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
        assertTrue(actual.contains("/survey/Survey1/questions/"));
    }

    private String createUrl(String str) {
        return "http://localhost:" + port + str;
    }
}

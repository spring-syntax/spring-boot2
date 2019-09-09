package ro.bytechnology.springboot2.controller;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import ro.bytechnology.springboot2.SpringBoot2Application;

import java.util.Arrays;

@SpringBootTest(classes = SpringBoot2Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {

    @LocalServerPort
    private int port;

    @Test
    public void testRetrieveSurveyQuestion() throws JSONException {
        //given
        String url = "http://localhost:" + port + "/survey/Survey1/questions/Question1";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        //when
        ResponseEntity<String> response = restTemplate.exchange(url,
                HttpMethod.GET, entity, String.class);

        //expected
        String expected = "{id:Question1,description:\"Largest Country in the World\",correctAnswer:Russia}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
}

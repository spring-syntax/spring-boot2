package ro.bytechnology.springboot2.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ro.bytechnology.springboot2.db.entities.User;
import ro.bytechnology.springboot2.service.UserService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void displayUsersTest() throws Exception {
        List<User> users = Arrays.asList(new User("Ion","User"),new User("Mihai","Admin"));
        Mockito.when(userService.findAll()).thenReturn(users);
        String actual = getActual("/all_users");
        String expected = "[{\"name\":\"Ion\",\"role\":\"User\"},{\"name\":\"Mihai\",\"role\":\"Admin\"}]";
        JSONAssert.assertEquals(expected,actual,true);
    }

    @Test
    public void displayAdminsTest() throws Exception {
        List<User> users = Arrays.asList(new User("Ion","Admin"),new User("Mihai","Admin"));
        Mockito.when(userService.findAdmins()).thenReturn(users);
        String actual = getActual("/admins");
        String expected = "[{\"name\":\"Ion\",\"role\":\"Admin\"},{\"name\":\"Mihai\",\"role\":\"Admin\"}]";
        JSONAssert.assertEquals(expected,actual,true);
    }

    private String getActual(String uri) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Ion")))
                .andReturn();
        return mvcResult.getResponse().getContentAsString();
    }
}

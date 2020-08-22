package pl.coderslab.surveyapp.controller;

import org.aspectj.lang.annotation.Before;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.coderslab.surveyapp.user.User;
import pl.coderslab.surveyapp.user.UserService;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

class HomeControllerTest {

//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private UserService userService;
//
//    @InjectMocks
//    HomeController homeController;
//
//
//    @Test
//    public void shouldAddUser() {
//
//        this.userService.saveUser(new User().toBuilder()
//                .username("Lucas")
//                .build());
//        User savedUser = userService.findByUsername("Lucas");
//        assertNotNull(savedUser);
//
//    }
}
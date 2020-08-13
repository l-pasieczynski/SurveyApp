package pl.coderslab.surveyapp.controller;

import org.aspectj.lang.annotation.Before;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.coderslab.surveyapp.user.User;
import pl.coderslab.surveyapp.user.UserService;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

class HomeControllerTest {

    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserService();
    }

    @Test
    public void shouldAddUser() {
        //given
        User user = new User();

        //when
        UserService userService = Mockito.mock(UserService.class);

        //then SUCCESS
        List<User> users = userService.findAll();
        assertThat(users, CoreMatchers.hasItem(user));

    }

}
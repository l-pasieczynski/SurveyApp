package pl.coderslab.surveyapp.user;

import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    private final UserService userService;

    public UserFacade(UserService userService) {
        this.userService = userService;
    }

    public void deleteUser (Long userId){
        userService.delete(userId);
    }

    public User findById(Long userId) {
        return userService.findById(userId);
    }

    public void saveUser(User user) {
        userService.saveUser(user);
    }

    public void fillUserData(User user, Long id){
        userService.fillUserData(user, id);
    }
}

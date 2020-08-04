package pl.coderslab.surveyapp.user;

import pl.coderslab.surveyapp.entity.User;

interface UserService {

    User findByUsername(String username);

    void saveUser(User user);
}

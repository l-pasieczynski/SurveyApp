package pl.coderslab.surveyapp.user;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    void saveUser(User user);

    User findById(Long id);

    void delete(Long id);

    void fillUserData(User user, Long id);

    void loginUser(User user);

    List<User> findAll();

    List<String> findAllUsersEmailAddresses();
}

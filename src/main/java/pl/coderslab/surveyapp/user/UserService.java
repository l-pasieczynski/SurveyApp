package pl.coderslab.surveyapp.user;

public interface UserService {

    User findByUsername(String username);

    void saveUser(User user);

    User findById(Long id);

    void delete(Long id);

    void fillUserData(User user, Long id);
}

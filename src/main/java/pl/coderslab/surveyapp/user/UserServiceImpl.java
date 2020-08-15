package pl.coderslab.surveyapp.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.surveyapp.role.Role;
import pl.coderslab.surveyapp.role.RoleRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(findById(id));
    }

    @Override
    public void fillUserData(User user, Long id) {
        User currentUser = userRepository.getOne(id);
        currentUser.toBuilder()
                .gender(user.getGender())
                .dateOfBirth(user.getDateOfBirth())
                .education(user.getEducation())
                .placeOfLiving(user.getPlaceOfLiving())
                .build();
        userRepository.save(currentUser);
    }

    @Override
    public void loginUser(User user) {
//        userRepository.getOne()

    }

    @Override
    public List<User> findAll() {
        List<User> all = userRepository.findAll();
        return all;
    }


}

package pl.coderslab.surveyapp.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.surveyapp.entity.Role;
import pl.coderslab.surveyapp.entity.RoleRepository;
import pl.coderslab.surveyapp.entity.Participant;

import java.util.Arrays;
import java.util.HashSet;

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
    public Participant findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(Participant participant) {
        participant.setPassword(passwordEncoder.encode(participant.getPassword()));
        participant.setActive(true);
        Role userRole = roleRepository.findByName("ROLE_USER");
        participant.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(participant);

    }
}

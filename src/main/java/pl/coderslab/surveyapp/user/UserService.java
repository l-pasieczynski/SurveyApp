package pl.coderslab.surveyapp.user;

import pl.coderslab.surveyapp.entity.Participant;

interface UserService {

    Participant findByUsername(String username);

    void saveUser(Participant participant);
}

package pl.coderslab.surveyapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.surveyapp.entity.Participant;

@Repository
interface UserRepository extends JpaRepository<Participant, Long> {
    Participant findByUsername(String username);
}

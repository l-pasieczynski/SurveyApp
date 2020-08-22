package pl.coderslab.surveyapp.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.surveyapp.user.User;

@Repository
interface AdminRepository extends JpaRepository <User, Long> {
}

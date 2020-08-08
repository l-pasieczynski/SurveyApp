package pl.coderslab.surveyapp.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.surveyapp.user.User;

interface AdminRepository extends JpaRepository <User, Long> {
}

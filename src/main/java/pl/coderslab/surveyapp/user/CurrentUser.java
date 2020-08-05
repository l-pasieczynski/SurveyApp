package pl.coderslab.surveyapp.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import pl.coderslab.surveyapp.entity.Participant;

import java.util.Collection;

public class CurrentUser extends User {

    private final Participant participant;

    public CurrentUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Participant participant){
        super(username, password, authorities);
        this.participant = participant;
    }

    public Participant getParticipant(){
        return participant;
    }

}

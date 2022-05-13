package mk.ukim.finki.wp.embeddedsystemsmanager.service;

import mk.ukim.finki.wp.embeddedsystemsmanager.model.User;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name,
                  String surname, Role role);
}

package rs.ac.uns.sw.xml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.sw.xml.domain.AppUser;
import rs.ac.uns.sw.xml.domain.User;
import rs.ac.uns.sw.xml.repository.UserRepositoryXML;

@Service
public class UserServiceXML {

    @Autowired
    UserRepositoryXML userRepositoryXML;

    public AppUser create(AppUser user) {
        if (user.getLozinka() != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(user.getLozinka());
            user.setLozinka(hashedPassword);
        }

        return userRepositoryXML.save(user);
    }

    public AppUser findById(Long id) { return userRepositoryXML.findById(id); }

    public AppUser findOneByUsername(String email) { return userRepositoryXML.findOneByUsername(email);}
}

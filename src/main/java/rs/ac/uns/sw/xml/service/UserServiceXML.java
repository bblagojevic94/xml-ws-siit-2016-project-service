package rs.ac.uns.sw.xml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.sw.xml.domain.AppUser;
import rs.ac.uns.sw.xml.domain.User;
import rs.ac.uns.sw.xml.repository.UserRepositoryXML;

@Service
public class UserServiceXML {

    @Autowired
    UserRepositoryXML userRepositoryXML;

    public AppUser create(AppUser user) {
        return userRepositoryXML.save(user);
    }

    public AppUser findById(Long id) { return userRepositoryXML.findById(id); }

    public AppUser findByEmail(String email) { return userRepositoryXML.findByEmail(email);}
}

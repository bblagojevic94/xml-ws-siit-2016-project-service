package rs.ac.uns.sw.xml.security;

import org.springframework.security.core.authority.AuthorityUtils;
import rs.ac.uns.sw.xml.domain.AppUser;

/**
 * A user factory which creates instances of SecurityUser object.
 */
public class UserFactory {

    private UserFactory() {
    }

    /**
     * Method that creates new SecurityUser.
     *
     * @param user user to be converted
     * @return Security User
     */
    public static SecurityUser create(AppUser user) {
        return new SecurityUser(
                user.getId(),
                user.getKorisnickoIme(),
                user.getLozinka(),
                user.getUloga(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(user.getUloga())
        );
    }
}

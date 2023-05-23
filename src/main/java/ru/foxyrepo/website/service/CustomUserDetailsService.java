package ru.foxyrepo.website.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.foxyrepo.website.dao.ProfileDao;
import ru.foxyrepo.website.dao.StatusDao;
import ru.foxyrepo.website.model.Profile;
import ru.foxyrepo.website.model.enums.Role;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        ProfileDao profileDao = new ProfileDao();
        Profile user = profileDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid User");
        } else {
            StatusDao statusDao = new StatusDao();
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(statusDao.findById(user.getIdRole()).getName()));
            return new org
                    .springframework
                    .security
                    .core
                    .userdetails
                    .User(user.getLogin(), user.getPassword(), grantedAuthorities);
        }
    }
}
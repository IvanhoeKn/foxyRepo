package ru.foxyrepo.website.service;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.foxyrepo.website.dao.ProfileDao;
import ru.foxyrepo.website.dao.StatusDao;
import ru.foxyrepo.website.model.Profile;

@Service
@Log4j
public class SaveNewUserImpl implements SaveNewUser {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Profile user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        StatusDao statusDao = new StatusDao();
        user.setIdRole(statusDao.findById(3L).getIdStatus());
        ProfileDao profileDao = new ProfileDao();
        System.out.println("login \"" + user.getLogin() + "\"");
        System.out.println("password \"" + user.getPassword() + "\"");
        profileDao.persist(user);
    }

    @Override
    public Profile findByUsername(String username) {
        ProfileDao profileDao = new ProfileDao();
        return profileDao.findByUsername(username);
    }
}

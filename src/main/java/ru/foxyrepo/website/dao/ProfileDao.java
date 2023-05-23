package ru.foxyrepo.website.dao;

import org.springframework.stereotype.Component;
import ru.foxyrepo.website.model.Article;
import ru.foxyrepo.website.model.Profile;

import javax.persistence.NoResultException;
import java.util.List;

@Component
public class ProfileDao extends DAO<Profile> {
    public ProfileDao() {
        super();
        setModelClass(Profile.class);
    }

    public Profile findByUsername(String searchUser) {
        try {
            Profile username = openCurrentSession()
                    .createNativeQuery("SELECT * FROM profile WHERE login = :searchUser", Profile.class)
                    .setParameter("searchUser", searchUser)
                    .getSingleResult();
            closeCurrentSession();
            return username;
        }
        catch (NoResultException nre) {
            return null;
        }
    }

    public List<Profile> userRequestBecomeWriter() {
        List<Profile> requestWriters = openCurrentSession()
                    .createNativeQuery("SELECT * FROM profile JOIN writer_request ON profile.id_profile = writer_request.id_user_profile", Profile.class)
                    .list();
            closeCurrentSession();
        return requestWriters;
    }
}
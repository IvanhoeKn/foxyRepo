package ru.foxyrepo.website.dao;

import ru.foxyrepo.website.model.Profile;
import ru.foxyrepo.website.model.WriterRequest;

import javax.persistence.NoResultException;

public class WriterRequestDao extends DAO<WriterRequest> {
    public WriterRequestDao() {
        super();
        setModelClass(WriterRequest.class);
    }

    public WriterRequest findByUserId(Long userId) {
        WriterRequest writerRequest = openCurrentSession()
                .createNativeQuery("SELECT * FROM writer_request WHERE id_user_profile = :userId", WriterRequest.class)
                .setParameter("userId", userId)
                .getSingleResult();
        closeCurrentSession();
        return writerRequest;
    }
}

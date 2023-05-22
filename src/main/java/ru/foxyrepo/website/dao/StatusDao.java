package ru.foxyrepo.website.dao;

import ru.foxyrepo.website.model.Status;

public class StatusDao extends DAO<Status> {
    public StatusDao() {
        super();
        setModelClass(Status.class);
    }
}

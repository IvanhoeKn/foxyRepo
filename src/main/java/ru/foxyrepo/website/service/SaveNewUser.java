package ru.foxyrepo.website.service;

import ru.foxyrepo.website.model.Profile;

public interface SaveNewUser {
    void save(Profile user);

    Profile findByUsername(String username);
}

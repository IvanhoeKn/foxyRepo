package ru.foxyrepo.website.service;

public interface SecurityService {
    boolean isAuthenticated();
    void autoLogin(String username, String password);
}

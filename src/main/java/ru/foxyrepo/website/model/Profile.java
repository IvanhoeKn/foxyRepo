package ru.foxyrepo.website.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Profile {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_profile", nullable = false)
    private Long idProfile;
    @Basic
    @Column(name = "id_role", nullable = false)
    private Long idRole;
    @Basic
    @Column(name = "login", nullable = false, length = 32)
    private String login;
    @Basic
    @Column(name = "password", nullable = false, length = 32)
    private String password;
    @Basic
    @Column(name = "mail", nullable = true, length = 64)
    private String mail;
    @Basic
    @Column(name = "about", nullable = true, length = 512)
    private String about;
    @Basic
    @Column(name = "registration", nullable = false)
    private Timestamp registration;

    public Long getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Long idProfile) {
        this.idProfile = idProfile;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Timestamp getRegistration() {
        return registration;
    }

    public void setRegistration(Timestamp registration) {
        this.registration = registration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(idProfile, profile.idProfile) && Objects.equals(idRole, profile.idRole) && Objects.equals(login, profile.login) && Objects.equals(password, profile.password) && Objects.equals(mail, profile.mail) && Objects.equals(about, profile.about) && Objects.equals(registration, profile.registration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProfile, idRole, login, password, mail, about, registration);
    }
}

package ru.foxyrepo.website.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "writer_request", schema = "public", catalog = "foxyrepo")
public class WriterRequest {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_request", nullable = false)
    private Long idRequest;
    @Basic
    @Column(name = "id_user_profile", nullable = false)
    private Long idUserProfile;

    public Long getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(Long idRequest) {
        this.idRequest = idRequest;
    }

    public Long getIdUserProfile() {
        return idUserProfile;
    }

    public void setIdUserProfile(Long idUserProfile) {
        this.idUserProfile = idUserProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WriterRequest that = (WriterRequest) o;
        return Objects.equals(idRequest, that.idRequest) && Objects.equals(idUserProfile, that.idUserProfile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRequest, idUserProfile);
    }
}

package ru.foxyrepo.website.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Favorites {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_favorites", nullable = false)
    private Long idFavorites;
    @Basic
    @Column(name = "id_user_for", nullable = false)
    private Long idUserFor;
    @Basic
    @Column(name = "id_what", nullable = false)
    private Long idWhat;

    public Long getIdFavorites() {
        return idFavorites;
    }

    public void setIdFavorites(Long idFavorites) {
        this.idFavorites = idFavorites;
    }

    public Long getIdUserFor() {
        return idUserFor;
    }

    public void setIdUserFor(Long idUserFor) {
        this.idUserFor = idUserFor;
    }

    public Long getIdWhat() {
        return idWhat;
    }

    public void setIdWhat(Long idWhat) {
        this.idWhat = idWhat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorites favorites = (Favorites) o;
        return Objects.equals(idFavorites, favorites.idFavorites) && Objects.equals(idUserFor, favorites.idUserFor) && Objects.equals(idWhat, favorites.idWhat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFavorites, idUserFor, idWhat);
    }
}

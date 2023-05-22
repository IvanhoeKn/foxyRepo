package ru.foxyrepo.website.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Status {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_status", nullable = false)
    private Long idStatus;
    @Basic
    @Column(name = "name", nullable = false, length = 16)
    private String name;

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return Objects.equals(idStatus, status.idStatus) && Objects.equals(name, status.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStatus, name);
    }
}

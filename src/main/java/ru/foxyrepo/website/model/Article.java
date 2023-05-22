package ru.foxyrepo.website.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Article {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_article", nullable = false)
    private Long idArticle;
    @Basic
    @Column(name = "id_writer", nullable = false)
    private Long idWriter;
    @Basic
    @Column(name = "id_class", nullable = false)
    private Long idClass;
    @Basic
    @Column(name = "title", nullable = false, length = 64)
    private String title;
    @Basic
    @Column(name = "summary", nullable = false, length = 256)
    private String summary;
    @Basic
    @Column(name = "document", nullable = false, length = -1)
    private String document;
    @Basic
    @Column(name = "picture", nullable = true, length = 256)
    private String picture;
    @Basic
    @Column(name = "creation_date", nullable = false)
    private Timestamp creationDate;
    @Basic
    @Column(name = "like_counter", nullable = false)
    private Long likeCounter;

    public Long getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Long idArticle) {
        this.idArticle = idArticle;
    }

    public Long getIdWriter() {
        return idWriter;
    }

    public void setIdWriter(Long idWriter) {
        this.idWriter = idWriter;
    }

    public Long getIdClass() {
        return idClass;
    }

    public void setIdClass(Long idClass) {
        this.idClass = idClass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Long getLikeCounter() {
        return likeCounter;
    }

    public void setLikeCounter(Long likeCounter) {
        this.likeCounter = likeCounter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(idArticle, article.idArticle) && Objects.equals(idWriter, article.idWriter) && Objects.equals(idClass, article.idClass) && Objects.equals(title, article.title) && Objects.equals(summary, article.summary) && Objects.equals(document, article.document) && Objects.equals(picture, article.picture) && Objects.equals(creationDate, article.creationDate) && Objects.equals(likeCounter, article.likeCounter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArticle, idWriter, idClass, title, summary, document, picture, creationDate, likeCounter);
    }
}

package ru.foxyrepo.website.dao;

import ru.foxyrepo.website.model.Article;
import java.util.List;

public class ArticleDao extends DAO<Article> {
    public ArticleDao() {
        super();
        setModelClass(Article.class);
    }

    public List<Article> getLastAdded() {
            List<Article> lastAdded = openCurrentSession()
                    .createNativeQuery("SELECT * FROM article ORDER BY creation_date DESC limit(5)", Article.class)
                    .list();
            closeCurrentSession();
            return lastAdded;
    }

    public List<Article> getBestFavorites() {
        List<Article> bestFavorites = openCurrentSession()
                .createNativeQuery("SELECT * FROM article ORDER BY like_counter DESC limit(5)", Article.class)
                .list();
        closeCurrentSession();
        return bestFavorites;
    }

    public List<Article> getArticlesWithCategory(Long idCategory) {
        List<Article> bestFavorites = openCurrentSession()
                .createNativeQuery("SELECT * FROM article WHERE id_class = :idCategory", Article.class)
                .setParameter("idCategory", idCategory)
                .list();
        closeCurrentSession();
        return bestFavorites;
    }

    public List<Article> getPopularArticleWithFilter(Long idCategory) {
        List<Article> bestFavorites = openCurrentSession()
                .createNativeQuery("SELECT * FROM article WHERE id_class = :idCategory ORDER BY like_counter DESC limit(3)", Article.class)
                .setParameter("idCategory", idCategory)
                .list();
        closeCurrentSession();
        return bestFavorites;
    }

    public List<Article> getFavoritesForUser(Long idUser) {
        List<Article> favorites = openCurrentSession()
                .createNativeQuery("SELECT * FROM article JOIN favorites ON article.id_article = favorites.id_what WHERE favorites.id_user_for = :idUser", Article.class)
                .setParameter("idUser", idUser)
                .list();
        closeCurrentSession();
        return favorites;
    }

    public List<Article> getPublishedArticles(Long idUser) {
        List<Article> publishedArticles = openCurrentSession()
                .createNativeQuery("SELECT * FROM article WHERE id_writer = :idUser", Article.class)
                .setParameter("idUser", idUser)
                .list();
        closeCurrentSession();
        return publishedArticles;
    }
}

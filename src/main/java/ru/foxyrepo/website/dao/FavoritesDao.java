package ru.foxyrepo.website.dao;

import ru.foxyrepo.website.model.Favorites;

public class FavoritesDao extends DAO<Favorites> {

    public FavoritesDao() {
        super();
        setModelClass(Favorites.class);
    }
}

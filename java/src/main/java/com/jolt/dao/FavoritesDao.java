package com.jolt.dao;

import com.jolt.model.Favorites;

import java.util.List;

public interface FavoritesDao {

    List<Favorites> getFavorites(int userId);

    Favorites getFavoriteById(int favoriteId, int userId);

    Favorites createFavorite(Favorites favorite, int userId);

    void deleteFavorite(int favoriteId, int userId);

    boolean isFavoriteExists(int userId, String businessId);

}

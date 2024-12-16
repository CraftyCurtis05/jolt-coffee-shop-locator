package com.jolt.dao;

import com.jolt.model.Favorites;

import java.util.List;

public interface FavoritesDao {

    // Get all favorites for a user
    List<Favorites> getFavorites(int userId);

    // Get a specific favorite by ID and user ID
    Favorites getFavoriteById(int favoriteId, int userId);

    // Create a new favorite for the user
    Favorites createFavorite(Favorites favorite, int userId);

    // Delete a favorite by its ID and user ID
    void deleteFavorite(int favoriteId, int userId);

    // Check is favorite already exists for userId
    boolean isFavoriteExists(int userId, String businessId);
}
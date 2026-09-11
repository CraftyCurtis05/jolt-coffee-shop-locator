package com.jolt.controller;

import com.jolt.dao.FavoritesDao;
import com.jolt.dao.UserDao;
import com.jolt.exception.DaoException;
import com.jolt.model.Favorites;
import com.jolt.model.User;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/favorites")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://jolt.jennifercurtis.me"
})
public class FavoritesController {

    private final FavoritesDao favoritesDao;
    private final UserDao userDao;

    public FavoritesController(FavoritesDao favoritesDao, UserDao userDao) {
        this.favoritesDao = favoritesDao;
        this.userDao = userDao;
    }

    @GetMapping
    public List<Favorites> getFavorites(Principal principal) {
        String username = principal.getName();
        User user = userDao.getUserByUsername(username);
        int userId = user.getId();

        return favoritesDao.getFavorites(userId);
    }

    @GetMapping("/{favoriteId}")
    public Favorites getFavoriteById(
            @PathVariable int favoriteId,
            Principal principal) {

        String username = principal.getName();
        User user = userDao.getUserByUsername(username);
        int userId = user.getId();

        return favoritesDao.getFavoriteById(favoriteId, userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Favorites createFavorite(
            @RequestBody Favorites favorite,
            Principal principal) {

        String username = principal.getName();
        User user = userDao.getUserByUsername(username);
        int userId = user.getId();

        boolean exists = favoritesDao.isFavoriteExists(
                userId,
                favorite.getBusinessId()
        );

        if (exists) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "This favorite already exists for this user!"
            );
        }

        return favoritesDao.createFavorite(favorite, userId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{favoriteId}")
    public void deleteFavorite(
            @PathVariable int favoriteId,
            Principal principal) {

        String username = principal.getName();
        User user = userDao.getUserByUsername(username);
        int userId = user.getId();

        try {
            favoritesDao.deleteFavorite(favoriteId, userId);

        } catch (DaoException e) {
            if ("Favorite not found or not authorized to delete".equals(e.getMessage())) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Favorite not found",
                        e
                );
            }

            throw e;
        }
    }

}

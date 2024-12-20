package com.jolt.controller;

import com.jolt.model.User;
import com.jolt.model.Favorites;
import com.jolt.dao.UserDao;
import com.jolt.dao.FavoritesDao;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/favorites")
@CrossOrigin(origins = "http://localhost:5173")  // Allow this controller to accept requests from the frontend
public class FavoritesController {

    private final FavoritesDao favoritesDao;
    private final UserDao userDao;

    public FavoritesController(FavoritesDao favoritesDao, UserDao userDao) {
        this.favoritesDao = favoritesDao;
        this.userDao = userDao;
    }

    /**
     * Retrieves the favorites for the authenticated user.
     */
    @GetMapping()
    public List<Favorites> getFavorites(Principal principal) {

        String username = principal.getName();  // Get the username from the authenticated user
        User user = userDao.getUserByUsername(username); // Get User object from username
        int userId = user.getId(); // Get userId from User object

        return favoritesDao.getFavorites(userId);  // Pass the userId to the DAO
    }

    /**
     * Retrieves a specific favorite for the authenticated user by favoriteId.
     */
    @GetMapping("/{favoriteId}")
    public Favorites getFavoriteById(@PathVariable int favoriteId, Principal principal) {

        String username = principal.getName();  // Get the username from the authenticated user
        User user = userDao.getUserByUsername(username); // Get User object from username
        int userId = user.getId(); // Get userId from User object

        return favoritesDao.getFavoriteById(userId, favoriteId);  // Pass the userId to the DAO
    }

    /**
     * Create a new favorite for a given user
     *
     * @param favorite body
     **/
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Favorites createFavorite(@RequestBody Favorites favorite, Principal principal) {

        String username = principal.getName();  // Get the username from the authenticated user
        User user = userDao.getUserByUsername(username); // Get User object from username
        int userId = user.getId(); // Get userId from User object

        // Check if the favorite already exists
        boolean exists = favoritesDao.isFavoriteExists(userId, favorite.getBusinessId());
        if (exists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This favorite already exists for this user!");
        }

        // Save the new favorite to the database
        return favoritesDao.createFavorite(favorite, userId);  // Passing the userId
    }

    /**
     * Delete a favorite for a given user
     *
     * @param favoriteId from favorite body
     **/
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/{favoriteId}")
    public void deleteFavorite(@PathVariable int favoriteId, Principal principal) {
        
        String username = principal.getName();  // Get the username from the authenticated user
        User user = userDao.getUserByUsername(username); // Get User object from username
        int userId = user.getId(); // Get userId from User object

        // Call the DAO to delete the favorite
        favoritesDao.deleteFavorite(favoriteId, userId);
    }
}
package com.techelevator.controller;

import com.techelevator.model.User;
import com.techelevator.model.Favorites;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.FavoritesDao;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin(origins = "http://localhost:5173")  // Allow this controller to accept requests from the frontend
public class FavoritesController {

    private final FavoritesDao favoritesDao;
    private UserDao userDao;

    public FavoritesController(FavoritesDao favoritesDao, UserDao userDao) {
        this.favoritesDao = favoritesDao;
        this.userDao = userDao;
    }

    /**
     * Retrieves the favorites for the authenticated user.
     */
    @GetMapping("/favorites")
    public List<Favorites> getFavorites(Principal principal) {

        String username = principal.getName();  // Get the username from the authenticated user
        User user = userDao.getUserByUsername(username); // Get User object from username
        int userId = user.getId(); // Get userId from User object

        return favoritesDao.getFavorites(userId);  // Pass the userId to the DAO
    }

    /**
     * Retrieves a specific favorite for the authenticated user by favoriteId.
     */
    @GetMapping("/favorites/{favoriteId}")
    public Favorites getFavoriteById(@PathVariable int favoriteId, Principal principal) {

        String username = principal.getName();  // Get the username from the authenticated user
        User user = userDao.getUserByUsername(username); // Get User object from username
        int userId = user.getId(); // Get userId from User object

        return favoritesDao.getFavoriteById(userId, favoriteId);  // Pass the userId to the DAO
    }

    /**
     * Create a new favorite for a given user
     *
     * @param favorite
     **/
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/favorites", method = RequestMethod.POST)
    public Favorites createFavorite(@RequestBody Favorites favorite, Principal principal) {

        String username = principal.getName();  // Get the username from the authenticated user
        User user = userDao.getUserByUsername(username); // Get User object from username
        int userId = user.getId(); // Get userId from User object

        // Save the new favorite to the database
        return favoritesDao.createFavorite(favorite, userId);  // Passing the userId
    }

    /**
     * Delete a favorite for a given user
     *
     * @param favoriteId
     **/
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/favorites/{favoriteId}", method = RequestMethod.DELETE)
    public void deleteFavorite(@PathVariable int favoriteId, Principal principal) {
        
        String username = principal.getName();  // Get the username from the authenticated user
        User user = userDao.getUserByUsername(username); // Get User object from username
        int userId = user.getId(); // Get userId from User object

        // Call the DAO to delete the favorite
        favoritesDao.deleteFavorite(favoriteId, userId);
    }
}
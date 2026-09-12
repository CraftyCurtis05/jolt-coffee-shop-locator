package com.jolt.controller;

import com.jolt.dao.FavoritesDao;
import com.jolt.dao.UserDao;
import com.jolt.model.Favorites;
import com.jolt.model.User;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.web.server.ResponseStatusException;
import com.jolt.exception.DaoException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class FavoritesControllerTests {

    private FavoritesController sut;

    @Mock
    private FavoritesDao favoritesDao;

    @Mock
    private UserDao userDao;

    @Mock
    private Principal principal;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        sut = new FavoritesController(
                favoritesDao,
                userDao
        );
    }

    @Test
    public void getFavorites_returns_favorites_for_authenticated_user() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        Favorites favorite = new Favorites();
        favorite.setFavoriteId(10);
        favorite.setBusinessId("business-1");

        List<Favorites> expectedFavorites =
                Collections.singletonList(favorite);

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);
        when(favoritesDao.getFavorites(1)).thenReturn(expectedFavorites);

        List<Favorites> actualFavorites =
                sut.getFavorites(principal);

        Assert.assertEquals(
                expectedFavorites,
                actualFavorites
        );
    }

    @Test
    public void getFavoriteById_returns_favorite_for_authenticated_user() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        Favorites favorite = new Favorites();
        favorite.setFavoriteId(10);
        favorite.setBusinessId("business-1");

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);
        when(favoritesDao.getFavoriteById(10, 1)).thenReturn(favorite);

        Favorites actualFavorite =
                sut.getFavoriteById(10, principal);

        Assert.assertEquals(
                favorite,
                actualFavorite
        );
    }

    @Test
    public void createFavorite_given_new_favorite_returns_created_favorite() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        Favorites favorite = new Favorites();
        favorite.setBusinessId("business-2");
        favorite.setBusinessName("Test Coffee");

        Favorites createdFavorite = new Favorites();
        createdFavorite.setFavoriteId(20);
        createdFavorite.setBusinessId("business-2");
        createdFavorite.setBusinessName("Test Coffee");

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);
        when(favoritesDao.isFavoriteExists(1, "business-2"))
                .thenReturn(false);
        when(favoritesDao.createFavorite(favorite, 1))
                .thenReturn(createdFavorite);

        Favorites actualFavorite =
                sut.createFavorite(favorite, principal);

        Assert.assertEquals(
                createdFavorite,
                actualFavorite
        );
    }

    @Test
    public void createFavorite_given_duplicate_favorite_throws_conflict() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        Favorites favorite = new Favorites();
        favorite.setBusinessId("business-2");

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);
        when(favoritesDao.isFavoriteExists(1, "business-2"))
                .thenReturn(true);

        try {
            sut.createFavorite(favorite, principal);
            Assert.fail("Expected ResponseStatusException");

        } catch (ResponseStatusException e) {
            Assert.assertEquals(
                    409,
                    e.getStatus().value()
            );
        }
    }

    @Test
    public void deleteFavorite_given_existing_favorite_deletes_favorite() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);

        sut.deleteFavorite(10, principal);

        org.mockito.Mockito.verify(favoritesDao)
                .deleteFavorite(10, 1);
    }

    @Test
    public void deleteFavorite_given_missing_or_unauthorized_favorite_throws_not_found() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);

        org.mockito.Mockito.doThrow(
                new DaoException("Favorite not found or not authorized to delete")
        ).when(favoritesDao).deleteFavorite(10, 1);

        try {
            sut.deleteFavorite(10, principal);
            Assert.fail("Expected ResponseStatusException");

        } catch (ResponseStatusException e) {
            Assert.assertEquals(
                    404,
                    e.getStatus().value()
            );
        }
    }

    @Test
    public void deleteFavorite_given_other_dao_exception_rethrows_exception() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);

        DaoException expectedException =
                new DaoException("Database error");

        org.mockito.Mockito.doThrow(expectedException)
                .when(favoritesDao)
                .deleteFavorite(10, 1);

        try {
            sut.deleteFavorite(10, principal);
            Assert.fail("Expected DaoException");

        } catch (DaoException e) {
            Assert.assertSame(
                    expectedException,
                    e
            );
        }
    }
}

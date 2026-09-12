package com.jolt.dao;

import com.jolt.model.Favorites;

import com.jolt.exception.DaoException;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcFavoritesDaoTests extends BaseDaoTests {

    private JdbcFavoritesDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        JdbcUserDao userDao = new JdbcUserDao(jdbcTemplate);

        sut = new JdbcFavoritesDao(dataSource, userDao);
    }

    @Test
    public void getFavorites_given_user_with_no_favorites_returns_empty_list() {
        List<Favorites> favorites = sut.getFavorites(1);

        Assert.assertNotNull(favorites);
        Assert.assertTrue(favorites.isEmpty());
    }

    @Test
    public void createFavorite_creates_and_returns_favorite() {
        Favorites favorite = new Favorites();

        favorite.setBusinessId("test-business-1");
        favorite.setBusinessName("Test Coffee");
        favorite.setBusinessAddress1("123 Main St");
        favorite.setBusinessAddress2("");
        favorite.setBusinessCity("Columbus");
        favorite.setBusinessState("OH");
        favorite.setBusinessZipcode("43215");
        favorite.setBusinessImage("https://example.com/image.jpg");
        favorite.setBusinessUrl("https://example.com");

        Favorites createdFavorite = sut.createFavorite(favorite, 1);

        Assert.assertNotNull(createdFavorite);
        Assert.assertTrue(createdFavorite.getFavoriteId() > 0);
        Assert.assertEquals("test-business-1", createdFavorite.getBusinessId());
        Assert.assertEquals("Test Coffee", createdFavorite.getBusinessName());
        Assert.assertEquals(1, createdFavorite.getUser().getId());
    }

    @Test
    public void getFavoriteById_given_favorite_for_different_user_returns_null() {
        Favorites favorite = new Favorites();

        favorite.setBusinessId("test-business-2");
        favorite.setBusinessName("Second Coffee");
        favorite.setBusinessAddress1("456 High St");
        favorite.setBusinessAddress2("");
        favorite.setBusinessCity("Columbus");
        favorite.setBusinessState("OH");
        favorite.setBusinessZipcode("43215");
        favorite.setBusinessImage("https://example.com/second.jpg");
        favorite.setBusinessUrl("https://example.com/second");

        Favorites createdFavorite = sut.createFavorite(favorite, 1);

        Favorites retrievedFavorite =
                sut.getFavoriteById(createdFavorite.getFavoriteId(), 2);

        Assert.assertNull(retrievedFavorite);
    }

    @Test
    public void isFavoriteExists_returns_true_for_existing_favorite_and_false_for_other_user() {
        Favorites favorite = new Favorites();

        favorite.setBusinessId("test-business-3");
        favorite.setBusinessName("Third Coffee");
        favorite.setBusinessAddress1("789 Broad St");
        favorite.setBusinessAddress2("");
        favorite.setBusinessCity("Columbus");
        favorite.setBusinessState("OH");
        favorite.setBusinessZipcode("43215");
        favorite.setBusinessImage("https://example.com/third.jpg");
        favorite.setBusinessUrl("https://example.com/third");

        sut.createFavorite(favorite, 1);

        Assert.assertTrue(
                sut.isFavoriteExists(1, "test-business-3")
        );

        Assert.assertFalse(
                sut.isFavoriteExists(2, "test-business-3")
        );
    }

    @Test
    public void deleteFavorite_deletes_favorite_for_correct_user() {
        Favorites favorite = new Favorites();

        favorite.setBusinessId("test-business-4");
        favorite.setBusinessName("Fourth Coffee");
        favorite.setBusinessAddress1("101 State St");
        favorite.setBusinessAddress2("");
        favorite.setBusinessCity("Columbus");
        favorite.setBusinessState("OH");
        favorite.setBusinessZipcode("43215");
        favorite.setBusinessImage("https://example.com/fourth.jpg");
        favorite.setBusinessUrl("https://example.com/fourth");

        Favorites createdFavorite = sut.createFavorite(favorite, 1);

        sut.deleteFavorite(createdFavorite.getFavoriteId(), 1);

        Favorites deletedFavorite =
                sut.getFavoriteById(createdFavorite.getFavoriteId(), 1);

        Assert.assertNull(deletedFavorite);
    }

    @Test(expected = DaoException.class)
    public void deleteFavorite_given_favorite_for_different_user_throws_exception() {
        Favorites favorite = new Favorites();

        favorite.setBusinessId("test-business-5");
        favorite.setBusinessName("Fifth Coffee");
        favorite.setBusinessAddress1("202 Main St");
        favorite.setBusinessAddress2("");
        favorite.setBusinessCity("Columbus");
        favorite.setBusinessState("OH");
        favorite.setBusinessZipcode("43215");
        favorite.setBusinessImage("https://example.com/fifth.jpg");
        favorite.setBusinessUrl("https://example.com/fifth");

        Favorites createdFavorite = sut.createFavorite(favorite, 1);

        sut.deleteFavorite(createdFavorite.getFavoriteId(), 2);
    }

    @Test(expected = DaoException.class)
    public void createFavorite_given_duplicate_business_for_same_user_throws_exception() {
        Favorites favorite = new Favorites();

        favorite.setBusinessId("test-business-6");
        favorite.setBusinessName("Sixth Coffee");
        favorite.setBusinessAddress1("303 Broad St");
        favorite.setBusinessAddress2("");
        favorite.setBusinessCity("Columbus");
        favorite.setBusinessState("OH");
        favorite.setBusinessZipcode("43215");
        favorite.setBusinessImage("https://example.com/sixth.jpg");
        favorite.setBusinessUrl("https://example.com/sixth");

        sut.createFavorite(favorite, 1);
        sut.createFavorite(favorite, 1);
    }

    @Test
    public void createFavorite_allows_same_business_for_different_users() {
        Favorites favorite = new Favorites();

        favorite.setBusinessId("test-business-7");
        favorite.setBusinessName("Seventh Coffee");
        favorite.setBusinessAddress1("404 High St");
        favorite.setBusinessAddress2("");
        favorite.setBusinessCity("Columbus");
        favorite.setBusinessState("OH");
        favorite.setBusinessZipcode("43215");
        favorite.setBusinessImage("https://example.com/seventh.jpg");
        favorite.setBusinessUrl("https://example.com/seventh");

        Favorites userOneFavorite = sut.createFavorite(favorite, 1);
        Favorites userTwoFavorite = sut.createFavorite(favorite, 2);

        Assert.assertNotNull(userOneFavorite);
        Assert.assertNotNull(userTwoFavorite);
        Assert.assertEquals(1, userOneFavorite.getUser().getId());
        Assert.assertEquals(2, userTwoFavorite.getUser().getId());
    }

    @Test
    public void getFavoriteById_given_own_favorite_returns_favorite() {
        Favorites favorite = new Favorites();

        favorite.setBusinessId("test-business-8");
        favorite.setBusinessName("Eighth Coffee");
        favorite.setBusinessAddress1("505 State St");
        favorite.setBusinessAddress2("");
        favorite.setBusinessCity("Columbus");
        favorite.setBusinessState("OH");
        favorite.setBusinessZipcode("43215");
        favorite.setBusinessImage("https://example.com/eighth.jpg");
        favorite.setBusinessUrl("https://example.com/eighth");

        Favorites createdFavorite = sut.createFavorite(favorite, 1);

        Favorites retrievedFavorite =
                sut.getFavoriteById(createdFavorite.getFavoriteId(), 1);

        Assert.assertNotNull(retrievedFavorite);
        Assert.assertEquals(
                createdFavorite.getFavoriteId(),
                retrievedFavorite.getFavoriteId()
        );
        Assert.assertEquals(
                "test-business-8",
                retrievedFavorite.getBusinessId()
        );
        Assert.assertEquals(
                "Eighth Coffee",
                retrievedFavorite.getBusinessName()
        );
        Assert.assertEquals(
                1,
                retrievedFavorite.getUser().getId()
        );
    }

    @Test
    public void getFavorites_returns_only_favorites_for_requested_user() {
        Favorites firstFavorite = new Favorites();

        firstFavorite.setBusinessId("test-business-9");
        firstFavorite.setBusinessName("Ninth Coffee");
        firstFavorite.setBusinessAddress1("606 Main St");
        firstFavorite.setBusinessAddress2("");
        firstFavorite.setBusinessCity("Columbus");
        firstFavorite.setBusinessState("OH");
        firstFavorite.setBusinessZipcode("43215");
        firstFavorite.setBusinessImage("https://example.com/ninth.jpg");
        firstFavorite.setBusinessUrl("https://example.com/ninth");

        Favorites secondFavorite = new Favorites();

        secondFavorite.setBusinessId("test-business-10");
        secondFavorite.setBusinessName("Tenth Coffee");
        secondFavorite.setBusinessAddress1("707 Broad St");
        secondFavorite.setBusinessAddress2("");
        secondFavorite.setBusinessCity("Columbus");
        secondFavorite.setBusinessState("OH");
        secondFavorite.setBusinessZipcode("43215");
        secondFavorite.setBusinessImage("https://example.com/tenth.jpg");
        secondFavorite.setBusinessUrl("https://example.com/tenth");

        sut.createFavorite(firstFavorite, 1);
        sut.createFavorite(secondFavorite, 2);

        List<Favorites> favorites = sut.getFavorites(1);

        Assert.assertEquals(1, favorites.size());
        Assert.assertEquals(
                "test-business-9",
                favorites.get(0).getBusinessId()
        );
        Assert.assertEquals(
                1,
                favorites.get(0).getUser().getId()
        );
    }

    @Test
    public void getFavorites_returns_favorites_in_ascending_id_order() {
        Favorites firstFavorite = new Favorites();

        firstFavorite.setBusinessId("test-business-11");
        firstFavorite.setBusinessName("Eleventh Coffee");
        firstFavorite.setBusinessAddress1("808 High St");
        firstFavorite.setBusinessAddress2("");
        firstFavorite.setBusinessCity("Columbus");
        firstFavorite.setBusinessState("OH");
        firstFavorite.setBusinessZipcode("43215");
        firstFavorite.setBusinessImage("https://example.com/eleventh.jpg");
        firstFavorite.setBusinessUrl("https://example.com/eleventh");

        Favorites secondFavorite = new Favorites();

        secondFavorite.setBusinessId("test-business-12");
        secondFavorite.setBusinessName("Twelfth Coffee");
        secondFavorite.setBusinessAddress1("909 State St");
        secondFavorite.setBusinessAddress2("");
        secondFavorite.setBusinessCity("Columbus");
        secondFavorite.setBusinessState("OH");
        secondFavorite.setBusinessZipcode("43215");
        secondFavorite.setBusinessImage("https://example.com/twelfth.jpg");
        secondFavorite.setBusinessUrl("https://example.com/twelfth");

        Favorites createdFirstFavorite =
                sut.createFavorite(firstFavorite, 1);

        Favorites createdSecondFavorite =
                sut.createFavorite(secondFavorite, 1);

        List<Favorites> favorites = sut.getFavorites(1);

        Assert.assertEquals(2, favorites.size());
        Assert.assertEquals(
                createdFirstFavorite.getFavoriteId(),
                favorites.get(0).getFavoriteId()
        );
        Assert.assertEquals(
                createdSecondFavorite.getFavoriteId(),
                favorites.get(1).getFavoriteId()
        );
    }

    @Test
    public void deleteFavorite_given_favorite_for_different_user_keeps_favorite() {
        Favorites favorite = new Favorites();

        favorite.setBusinessId("test-business-13");
        favorite.setBusinessName("Thirteenth Coffee");
        favorite.setBusinessAddress1("1000 Main St");
        favorite.setBusinessAddress2("");
        favorite.setBusinessCity("Columbus");
        favorite.setBusinessState("OH");
        favorite.setBusinessZipcode("43215");
        favorite.setBusinessImage("https://example.com/thirteenth.jpg");
        favorite.setBusinessUrl("https://example.com/thirteenth");

        Favorites createdFavorite = sut.createFavorite(favorite, 1);

        try {
            sut.deleteFavorite(createdFavorite.getFavoriteId(), 2);
            Assert.fail("Expected DaoException");
        } catch (DaoException e) {
            Favorites existingFavorite =
                    sut.getFavoriteById(createdFavorite.getFavoriteId(), 1);

            Assert.assertNotNull(existingFavorite);
        }
    }

}

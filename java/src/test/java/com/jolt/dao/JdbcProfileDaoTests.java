package com.jolt.dao;

import com.jolt.model.Profile;

import com.jolt.exception.DaoException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcProfileDaoTests extends BaseDaoTests {

    private JdbcProfileDao sut;
    private JdbcUserDao userDao;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        userDao = new JdbcUserDao(jdbcTemplate);
        sut = new JdbcProfileDao(jdbcTemplate, userDao);
    }

    @Test
    public void getProfileByUserId_given_user_with_no_profile_returns_null() {
        Profile profile = sut.getProfileByUserId(1);

        Assert.assertNull(profile);
    }

    @Test
    public void createProfile_creates_formats_and_returns_profile() {
        Profile profile = new Profile();

        profile.setFirstName("jENNIFER");
        profile.setLastName("cURTIS");
        profile.setBirthMonth("January");
        profile.setBirthDay(15);
        profile.setBirthYear(1990);
        profile.setAddress1("123 mAIN sT");
        profile.setAddress2("aPT 4b");
        profile.setCity("cOLUMBUS");
        profile.setState("oh");
        profile.setZipcode("43228");

        Profile createdProfile = sut.createProfile(profile, 1);

        Assert.assertNotNull(createdProfile);
        Assert.assertTrue(createdProfile.getProfileId() > 0);
        Assert.assertEquals("Jennifer", createdProfile.getFirstName());
        Assert.assertEquals("Curtis", createdProfile.getLastName());
        Assert.assertEquals("January", createdProfile.getBirthMonth());
        Assert.assertEquals(15, createdProfile.getBirthDay());
        Assert.assertEquals(1990, createdProfile.getBirthYear());
        Assert.assertEquals("123 Main St", createdProfile.getAddress1());
        Assert.assertEquals("Apt 4b", createdProfile.getAddress2());
        Assert.assertEquals("Columbus", createdProfile.getCity());
        Assert.assertEquals("OH", createdProfile.getState());
        Assert.assertEquals("43228", createdProfile.getZipcode());
        Assert.assertTrue(createdProfile.isFormSubmitted());
        Assert.assertEquals(1, createdProfile.getUser().getId());
    }

    @Test
    public void createProfile_given_existing_profile_returns_existing_profile() {
        Profile firstProfile = new Profile();

        firstProfile.setFirstName("Jennifer");
        firstProfile.setLastName("Curtis");
        firstProfile.setBirthMonth("January");
        firstProfile.setBirthDay(15);
        firstProfile.setBirthYear(1990);
        firstProfile.setAddress1("123 Main St");
        firstProfile.setAddress2("");
        firstProfile.setCity("Columbus");
        firstProfile.setState("OH");
        firstProfile.setZipcode("43228");

        Profile createdProfile = sut.createProfile(firstProfile, 1);

        Profile secondProfile = new Profile();

        secondProfile.setFirstName("Different");
        secondProfile.setLastName("Name");
        secondProfile.setBirthMonth("February");
        secondProfile.setBirthDay(20);
        secondProfile.setBirthYear(1990);
        secondProfile.setAddress1("999 Other St");
        secondProfile.setAddress2("");
        secondProfile.setCity("Dublin");
        secondProfile.setState("OH");
        secondProfile.setZipcode("43017");

        Profile returnedProfile = sut.createProfile(secondProfile, 1);

        Assert.assertEquals(
                createdProfile.getProfileId(),
                returnedProfile.getProfileId()
        );
        Assert.assertEquals("Jennifer", returnedProfile.getFirstName());
        Assert.assertEquals("Curtis", returnedProfile.getLastName());
        Assert.assertEquals("January", returnedProfile.getBirthMonth());
        Assert.assertEquals(15, returnedProfile.getBirthDay());
        Assert.assertEquals(1990, returnedProfile.getBirthYear());
    }

    @Test
    public void getProfileByUserId_given_existing_profile_returns_profile() {
        Profile profile = new Profile();

        profile.setFirstName("Jennifer");
        profile.setLastName("Curtis");
        profile.setBirthMonth("January");
        profile.setBirthDay(15);
        profile.setBirthYear(1990);
        profile.setAddress1("123 Main St");
        profile.setAddress2("");
        profile.setCity("Columbus");
        profile.setState("OH");
        profile.setZipcode("43228");

        Profile createdProfile = sut.createProfile(profile, 1);

        Profile retrievedProfile = sut.getProfileByUserId(1);

        Assert.assertNotNull(retrievedProfile);
        Assert.assertEquals(
                createdProfile.getProfileId(),
                retrievedProfile.getProfileId()
        );
        Assert.assertEquals("Jennifer", retrievedProfile.getFirstName());
        Assert.assertEquals("Curtis", retrievedProfile.getLastName());
        Assert.assertEquals(1, retrievedProfile.getUser().getId());
    }

    @Test
    public void updateProfile_updates_provided_fields_and_keeps_birthday() {
        Profile profile = new Profile();

        profile.setFirstName("Jennifer");
        profile.setLastName("Curtis");
        profile.setBirthMonth("January");
        profile.setBirthDay(15);
        profile.setBirthYear(1990);
        profile.setAddress1("123 Main St");
        profile.setAddress2("");
        profile.setCity("Columbus");
        profile.setState("OH");
        profile.setZipcode("43228");

        sut.createProfile(profile, 1);

        Profile update = new Profile();

        update.setFirstName("jEN");
        update.setCity("dUBLIN");
        update.setState("oh");
        update.setZipcode("43017");

        Profile updatedProfile = sut.updateProfile(update, 1);

        Assert.assertNotNull(updatedProfile);
        Assert.assertEquals("Jen", updatedProfile.getFirstName());
        Assert.assertEquals("Curtis", updatedProfile.getLastName());
        Assert.assertEquals("Dublin", updatedProfile.getCity());
        Assert.assertEquals("OH", updatedProfile.getState());
        Assert.assertEquals("43017", updatedProfile.getZipcode());

        Assert.assertEquals("January", updatedProfile.getBirthMonth());
        Assert.assertEquals(15, updatedProfile.getBirthDay());
        Assert.assertEquals(1990, updatedProfile.getBirthYear());
    }

    @Test
    public void updateProfile_given_no_fields_returns_existing_profile() {
        Profile profile = new Profile();

        profile.setFirstName("Jennifer");
        profile.setLastName("Curtis");
        profile.setBirthMonth("January");
        profile.setBirthDay(15);
        profile.setBirthYear(1990);
        profile.setAddress1("123 Main St");
        profile.setAddress2("");
        profile.setCity("Columbus");
        profile.setState("OH");
        profile.setZipcode("43228");

        Profile createdProfile = sut.createProfile(profile, 1);

        Profile update = new Profile();

        Profile returnedProfile = sut.updateProfile(update, 1);

        Assert.assertNotNull(returnedProfile);
        Assert.assertEquals(
                createdProfile.getProfileId(),
                returnedProfile.getProfileId()
        );
        Assert.assertEquals("Jennifer", returnedProfile.getFirstName());
        Assert.assertEquals("Curtis", returnedProfile.getLastName());
        Assert.assertEquals("Columbus", returnedProfile.getCity());
    }

    @Test
    public void updateProfile_given_user_with_no_profile_returns_null() {
        Profile update = new Profile();

        update.setFirstName("Jennifer");
        update.setCity("Columbus");

        Profile updatedProfile = sut.updateProfile(update, 2);

        Assert.assertNull(updatedProfile);
        Assert.assertNull(sut.getProfileByUserId(2));
    }

    @Test
    public void deleteProfile_deletes_existing_profile() {
        Profile profile = new Profile();

        profile.setFirstName("Jennifer");
        profile.setLastName("Curtis");
        profile.setBirthMonth("January");
        profile.setBirthDay(15);
        profile.setBirthYear(1990);
        profile.setAddress1("123 Main St");
        profile.setAddress2("");
        profile.setCity("Columbus");
        profile.setState("OH");
        profile.setZipcode("43228");

        sut.createProfile(profile, 1);

        sut.deleteProfile(1);

        Profile deletedProfile = sut.getProfileByUserId(1);

        Assert.assertNull(deletedProfile);
    }

    @Test(expected = DaoException.class)
    public void deleteProfile_given_no_existing_profile_throws_exception() {
        sut.deleteProfile(2);
    }

    @Test
    public void isFormSubmittedByUserId_given_user_with_no_profile_returns_false() {
        boolean isSubmitted = sut.isFormSubmittedByUserId(1);

        Assert.assertFalse(isSubmitted);
    }

    @Test
    public void isFormSubmittedByUserId_given_created_profile_returns_true() {
        Profile profile = new Profile();

        profile.setFirstName("Jennifer");
        profile.setLastName("Curtis");
        profile.setBirthMonth("January");
        profile.setBirthDay(15);
        profile.setBirthYear(1990);
        profile.setAddress1("123 Main St");
        profile.setAddress2("");
        profile.setCity("Columbus");
        profile.setState("OH");
        profile.setZipcode("43228");

        sut.createProfile(profile, 1);

        boolean isSubmitted = sut.isFormSubmittedByUserId(1);

        Assert.assertTrue(isSubmitted);
    }

    @Test
    public void isFormSubmittedByUserId_given_nonexistent_user_returns_false() {
        boolean isSubmitted = sut.isFormSubmittedByUserId(9999);

        Assert.assertFalse(isSubmitted);
    }
}

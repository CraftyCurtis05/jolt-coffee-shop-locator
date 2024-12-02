package com.techelevator.model;

public class Favorites {

    private int favoriteId;          // Unique identifier for the favorite entry
    private User user;               // The User object that owns this favorite (reference to User)
    private String businessId;       // ID of the business that is favorited
    private String businessName;     // Name of the business
    private String businessAddress;  // Address of the business
    private String businessWebsite;  // Website URL of the business

    // Constructor to create a Favorites object with all necessary fields.
    public Favorites(int favoriteId, User user, String businessId, String businessName, String businessAddress, String businessWebsite) {
        this.favoriteId = favoriteId;
        this.user = user;  // Set the User object directly
        this.businessId = businessId;
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.businessWebsite = businessWebsite;
    }

    // Default constructor for creating an empty Favorites object.
    public Favorites() {
    }

    // Getter and Setter methods for the favoriteId field.
    public int getFavoriteId() {
        return favoriteId;
    }
    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    // Getter and Setter methods for the user field (User object).
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    // Getter and Setter methods for the businessId field.
    public String getBusinessId() {
        return businessId;
    }
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    // Getter and Setter methods for the businessName field.
    public String getBusinessName() {
        return businessName;
    }
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    // Getter and Setter methods for the businessAddress field.
    public String getBusinessAddress() {
        return businessAddress;
    }
    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    // Getter and Setter methods for the businessWebsite field.
    public String getBusinessWebsite() {
        return businessWebsite;
    }
    public void setBusinessWebsite(String businessWebsite) {
        this.businessWebsite = businessWebsite;
    }
}
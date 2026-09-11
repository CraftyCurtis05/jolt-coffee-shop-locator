package com.jolt.model;

public class Favorites {

    private int favoriteId;
    private User user;
    private String businessId;
    private String businessName;
    private String businessAddress1;
    private String businessAddress2;
    private String businessCity;
    private String businessState;
    private String businessZipcode;
    private String businessImage;
    private String businessUrl;

    public Favorites() {
    }

    public Favorites(int favoriteId,
                     User user,
                     String businessId,
                     String businessName,
                     String businessAddress1,
                     String businessAddress2,
                     String businessCity,
                     String businessState,
                     String businessZipcode,
                     String businessImage,
                     String businessUrl) {

        this.favoriteId = favoriteId;
        this.user = user;
        this.businessId = businessId;
        this.businessName = businessName;
        this.businessAddress1 = businessAddress1;
        this.businessAddress2 = businessAddress2;
        this.businessCity = businessCity;
        this.businessState = businessState;
        this.businessZipcode = businessZipcode;
        this.businessImage = businessImage;
        this.businessUrl = businessUrl;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessAddress1() {
        return businessAddress1;
    }

    public void setBusinessAddress1(String businessAddress1) {
        this.businessAddress1 = businessAddress1;
    }

    public String getBusinessAddress2() {
        return businessAddress2;
    }

    public void setBusinessAddress2(String businessAddress2) {
        this.businessAddress2 = businessAddress2;
    }

    public String getBusinessCity() {
        return businessCity;
    }

    public void setBusinessCity(String businessCity) {
        this.businessCity = businessCity;
    }

    public String getBusinessState() {
        return businessState;
    }

    public void setBusinessState(String businessState) {
        this.businessState = businessState;
    }

    public String getBusinessZipcode() {
        return businessZipcode;
    }

    public void setBusinessZipcode(String businessZipcode) {
        this.businessZipcode = businessZipcode;
    }

    public String getBusinessImage() {
        return businessImage;
    }

    public void setBusinessImage(String businessImage) {
        this.businessImage = businessImage;
    }

    public String getBusinessUrl() {
        return businessUrl;
    }

    public void setBusinessUrl(String businessUrl) {
        this.businessUrl = businessUrl;
    }

}

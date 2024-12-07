package com.techelevator.model;

public class Image {

    private int imageId;
    private User user;
    private String imageName;
    private byte[] image;

    public Image(int imageId, User user, String imageName, byte[] image) {
        this.imageId = imageId;
        this.user = user;
        this.imageName = imageName;
        this.image = image;
    }

    public Image() {}

    public int getImageId() { return imageId; }
    public void setImageId(int imageId) { this.imageId = imageId; }

    public User getUser() { return user; }
    public void setUser(User user) {
        this.user = user;
    }

    public String getImageName() { return imageName; }
    public void setImageName(String imageName) { this.imageName = imageName; }

    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }
}
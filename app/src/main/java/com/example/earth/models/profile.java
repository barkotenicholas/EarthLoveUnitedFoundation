package com.example.earth.models;

import org.parceler.Parcel;

import java.util.List;

public class User {
    String Name;
    String Birthday;
    String Location;
    String Story;
    String Pronoun;
    String Website;
    String imageUri;
    List<String>interestLabels;
    List<Integer>Images;
    List<String>Clubs;
    List<String>Contacts;
    List<String>Followers;
    List<String>Following;
    public User() {

    }
    public User(String name, String birthday, String location, String story, String pronoun, String website, String imageUri) {
        Name = name;
        Birthday = birthday;
        Location = location;
        Story = story;
        Pronoun = pronoun;
        Website = website;
        this.imageUri = imageUri;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getStory() {
        return Story;
    }

    public void setStory(String story) {
        Story = story;
    }

    public String getPronoun() {
        return Pronoun;
    }

    public void setPronoun(String pronoun) {
        Pronoun = pronoun;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public List<String> getInterestLabels() {
        return interestLabels;
    }

    public void setInterestLabels(List<String> interestLabels) {
        this.interestLabels = interestLabels;
    }

    public List<Integer> getImages() {
        return Images;
    }

    public void setImages(List<Integer> images) {
        Images = images;
    }

    public List<String> getClubs() {
        return Clubs;
    }

    public void setClubs(List<String> clubs) {
        Clubs = clubs;
    }

    public List<String> getContacts() {
        return Contacts;
    }

    public void setContacts(List<String> contacts) {
        Contacts = contacts;
    }

    public List<String> getFollowers() {
        return Followers;
    }

    public void setFollowers(List<String> followers) {
        Followers = followers;
    }

    public List<String> getFollowing() {
        return Following;
    }

    public void setFollowing(List<String> following) {
        Following = following;
    }
}

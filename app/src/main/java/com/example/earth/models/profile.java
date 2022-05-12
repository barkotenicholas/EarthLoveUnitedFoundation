package com.example.earth.models;

import android.net.Uri;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Parcel
public class profile implements Serializable {
    String Name;
    String Birthday;
    String Location;
    String Story;
    String Pronoun;
    String Website;
    String ProfileImage;
    Uri ImageUri;
    ArrayList<String> interests;
    ArrayList<String> clubs;
    public profile() {

    }
    public profile(String name, String birthday, String location, String story, String pronoun, String website, Uri imageUri,ArrayList<String> interest,ArrayList<String> club) {
        Name = name;
        Birthday = birthday;
        Location = location;
        Story = story;
        Pronoun = pronoun;
        Website = website;
        ImageUri = imageUri;
        interests = interest;
        clubs = club;
    }
    public profile(String name, String birthday, String location, String story, String pronoun, String website, Uri imageUri) {
        Name = name;
        Birthday = birthday;
        Location = location;
        Story = story;
        Pronoun = pronoun;
        Website = website;
        ImageUri = imageUri;

    }

    public String getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(String profileImage) {
        ProfileImage = profileImage;
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

    public Uri getImageUri() {
        return  ImageUri;
    }

    public void setImageUri(Uri imageUri) {
        ImageUri = imageUri;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }

    public ArrayList<String> getInterests() {
        return interests;
    }

    public ArrayList<String> getClubs() {
        return clubs;
    }

    public void setClubs(ArrayList<String> clubs) {
        this.clubs = clubs;
    }
}

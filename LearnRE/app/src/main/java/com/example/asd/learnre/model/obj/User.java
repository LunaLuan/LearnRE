package com.example.asd.learnre.model.obj;

import android.net.Uri;

/**
 * Created by asd on 9/25/2016.
 */

public class User {

    private String displayName;
    private String photoUrl;

    public User() {
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}

package com.example.earth.models;

public class post {
    private  boolean scrol;

    public post(boolean scrol) {
        this.scrol = scrol;
    }

    public boolean isScrolled() {
        return scrol;
    }

    public void setScrol(boolean scrol) {
        this.scrol = scrol;
    }
}

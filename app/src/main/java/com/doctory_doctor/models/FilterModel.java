package com.doctory_doctor.models;

import java.io.Serializable;

public class FilterModel implements Serializable {
    private String title;
    private String type;

    public FilterModel(String title, String type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }
}

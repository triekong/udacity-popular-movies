package com.snoop.sliding_real.pojo;

import java.util.Date;

/**
 * Created by galaxywizkid on 6/26/16.
 */
public class MovieItem {

    private String title;
    private Date date;
    private String posterURL;
    private int id;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterSuffix) {
        this.posterURL = posterSuffix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

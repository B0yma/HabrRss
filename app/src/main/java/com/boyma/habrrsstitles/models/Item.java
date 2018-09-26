package com.boyma.habrrsstitles.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="item")
public class Item{


    @Element(name="title", required = false)
    private String title;

    @Element(name="guid", required = false)
    private Guid guid;

    @Element(name="link", required = false)
    private String link;

    @Element(name="description", required = false)
    private String description;

    @Element(name="pubDate", required = false)
    private String pubDate;

    @Element(name="creator", required = false)
    @Namespace(reference="http://purl.org/dc/elements/1.1/")
    private String creator;

    @ElementList(inline=true, name="category",required = false)
    public List<Category> category;



    public Guid getGuid() {
        return guid;
    }

    public String getLink() {
        return link;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getCreator() {
        return creator;
    }

    public List<Category> getTags() {
        return category;
    }

    public String getTitle() {
        return title;
    }
}

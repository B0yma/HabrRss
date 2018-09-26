package com.boyma.habrrsstitles.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="channel")
public class Channel {

    @Element(name="title",required = false)
    private String title;

    @Element(name="link",required = false)
    private String link;

    @Element(name="description",data=true,required = false)
    private String description;

    @Element(name="language",required = false)
    private String language;

    @Element(name="managingEditor",required = false)
    private String managingEditor;

    @Element(name="generator",required = false)
    private String generator;

    @Element(name="pubDate",required = false)
    private String pubDate;

    @Element(name="lastBuildDate",required = false)
    private String lastBuildDate;

    @Element(name="image",required = false)
    private Image ImageObject;

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    @ElementList(inline=true, name="item",required = false)
    private ArrayList<Item> items = new ArrayList<>();


    public Channel() {

    }
}

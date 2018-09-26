package com.boyma.habrrsstitles.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="image")
public class Image {

    @Element(name="link",required = false)
    private String link;

    @Element(name="url",required = false)
    private String url;

    @Element(name="title",required = false)
    private String title;

}

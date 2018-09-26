package com.boyma.habrrsstitles.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root
@Namespace(prefix="dc", reference="http://purl.org/dc/elements/1.1/")
public class Rss {


    public void setChannelObject(Channel channelObject) {
        ChannelObject = channelObject;
    }

    public Channel getChannelObject() {
        return ChannelObject;
    }

    @Element(name="channel", required = false)
    private Channel ChannelObject;
    /*@Attribute(name="xmlns:dc")
    private String xmlns;*/
    @Attribute(name="version", required = false)
    private String version;

    public Rss() {
    }


    public Rss(Channel channelObject, String version) {
        ChannelObject = channelObject;
        this.version = version;
    }
}

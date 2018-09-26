package com.boyma.habrrsstitles.models;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name="category")
public class Category {
    public String getValue() {
        return value;
    }

    @Text
    private String value;
}

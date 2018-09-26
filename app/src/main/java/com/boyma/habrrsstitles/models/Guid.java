package com.boyma.habrrsstitles.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name="guid")

public class Guid{



    public boolean isPermaLink() {
        return isPermaLink;
    }

    @Attribute(name="isPermaLink",required = false)
    private boolean isPermaLink;

    public String getValue() {
        return value;
    }

    @Text
    private String value;

    /*@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(value);
        dest.writeInt(isPermaLink ? 1 : 0);
    }

    protected Guid(Parcel in) {
        isPermaLink = in.readByte() != 0;
        value = in.readString();
    }

    public static final Creator<Guid> CREATOR = new Creator<Guid>() {
        @Override
        public Guid createFromParcel(Parcel in) {
            return new Guid(in);
        }

        @Override
        public Guid[] newArray(int size) {
            return new Guid[size];
        }
    };*/
}

package br.unip.myapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.unip.myapplication.managers.LocationType;


public class Location implements Parcelable {

    private Integer mId;
    private String mDescription;
    private Date mData;
    private LocationType mType;

    /* MOCK */
    public List<Location> getAll(LocationType locationType) {
        List<Location> tickets = new ArrayList<>();

        if (locationType.equals(LocationType.BUS)) {
            for (int i = 1; i < 12; i++) {
                tickets.add(createMock(locationType));
            }
        } else {
            for (int i = 1; i < 14; i++) {
                tickets.add(createMock(locationType));
            }
        }

        return tickets;
    }

    /* MOCK */
    private Location createMock(LocationType locationType) {
        Location location = new Location();

        if (locationType.equals(LocationType.BUS)) {
            location.setDescription("Bilhetes de ônubus");
            location.setData(new Date());
        } else {
            location.setDescription("Bilhetes de metrô");
            location.setData(new Date());
        }
        return location;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer mId) {
        this.mId = mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Date getData() {
        return mData;
    }

    public void setData(Date mData) {
        this.mData = mData;
    }

    public LocationType getType() {
        return mType;
    }

    public void setType(LocationType type) {
        this.mType = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;

        Location location = (Location) o;

        if (!mId.equals(location.mId)) return false;
        if (!mDescription.equals(location.mDescription)) return false;
        if (!mData.equals(location.mData)) return false;
        return mType == location.mType;

    }

    @Override
    public int hashCode() {
        int result = mId.hashCode();
        result = 31 * result + mDescription.hashCode();
        result = 31 * result + mData.hashCode();
        result = 31 * result + mType.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mId);
        dest.writeString(this.mDescription);
        dest.writeLong(this.mData.getTime());
        dest.writeInt(this.mType == null ? -1 : this.mType.ordinal());
    }

    public Location () {}

    protected Location(Parcel in) {
        this.mId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mDescription = in.readString();
        this.mData = new Date(in.readLong());
        int tmpMType = in.readInt();
        this.mType = tmpMType == -1 ? null : LocationType.values()[tmpMType];
    }

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}

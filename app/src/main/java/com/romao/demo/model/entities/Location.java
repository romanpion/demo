package com.romao.demo.model.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Location.TABLE_NAME)
public class Location implements Parcelable {
    public static final String TABLE_NAME = "locations";

    public static final String ID_COLUMN = BaseColumns._ID;
    public static final String NAME_COLUMN = "name";

    @DatabaseField(id = true, columnName = ID_COLUMN)
    private int id;

    @DatabaseField(columnName = NAME_COLUMN)
    private String name;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Company company;

    public Location() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    // ---- parcelable implementation
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(id);
    }

    public static final Creator<Location> CREATOR
            = new Creator<Location>() {
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    private Location(Parcel in) {
        name = in.readString();
        id = in.readInt();
    }
}

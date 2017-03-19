package com.romao.demo.model.entities;

import android.provider.BaseColumns;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = ResourceType.TABLE_NAME)
public class ResourceType implements Serializable {
    public static final String TABLE_NAME = "resourceTypes";

    public static final String ID_COLUMN = BaseColumns._ID;
    public static final String NAME_COLUMN = "name";

    @DatabaseField(id = true, columnName = ID_COLUMN)
    private int id;

    @DatabaseField(columnName = NAME_COLUMN)
    private String name;

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
}

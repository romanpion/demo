package com.romao.demo.model.entities;

import android.provider.BaseColumns;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Company.TABLE_NAME)
public class Company {

    public static final String TABLE_NAME = "companies";

    public static final String ID_COLUMN = BaseColumns._ID;
    public static final String NAME_COLUMN = "name";
    public static final String PAYMENT_VERIFIED_COLUMN = "paymentVerified";
    public static final String IMAGE_URL_COLUMN = "imageUrl";
    public static final String RATING_COLUMN = "rating";
    public static final String FOUNDED_COLUMN = "founded";
    public static final String LOCATION_ID_COLUMN = "locationId";
    public static final String INDUSTRY_COLUMN = "industry";
    public static final String IS_VALIDATED_COLUMN = "isValidated";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String CIN_COLUMN = "cin";


    @DatabaseField(columnName = PAYMENT_VERIFIED_COLUMN)
    private Boolean isPaymentVerified;

    @DatabaseField(columnName = IMAGE_URL_COLUMN)
    private String imageUrl;

    @DatabaseField(columnName = RATING_COLUMN)
    private int raiting;

    @DatabaseField(columnName = FOUNDED_COLUMN)
    private String founded;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = LOCATION_ID_COLUMN)
    private Location location;

    @DatabaseField(columnName = INDUSTRY_COLUMN)
    private String industry;

    @DatabaseField(columnName = NAME_COLUMN)
    private String name;

    @DatabaseField(columnName = IS_VALIDATED_COLUMN)
    private boolean isValidated;

    @DatabaseField(id = true, columnName = ID_COLUMN)
    private int id;

    @DatabaseField(columnName = DESCRIPTION_COLUMN)
    private String description;

    @DatabaseField(columnName = CIN_COLUMN)
    private String cin;


    public Boolean isPaymentVerified() {
        return isPaymentVerified;
    }

    public void setPaymentVerified(Boolean paymentVerified) {
        isPaymentVerified = paymentVerified;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getRaiting() {
        return raiting;
    }

    public void setRaiting(int raiting) {
        this.raiting = raiting;
    }

    public String getFounded() {
        return founded;
    }

    public void setFounded(String founded) {
        this.founded = founded;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }
}

package com.surroundapps.stringtojson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Murtuza Rahman on 4/2/19.
 */
public class Example {
    @SerializedName("Category1")
    @Expose
    private List<Category1> category = null;
    @SerializedName("Collection")
    @Expose
    private List<Collection> collection = null;
    @SerializedName("app_data_date_time")
    @Expose
    private String appDataDateTime;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<Category1> getCategory() {
        return category;
    }

    public void setCategory(List<Category1> category) {
        this.category = category;
    }

    public List<Collection> getCollection() {
        return collection;
    }

    public void setCollection(List<Collection> collection) {
        this.collection = collection;
    }

    public String getAppDataDateTime() {
        return appDataDateTime;
    }

    public void setAppDataDateTime(String appDataDateTime) {
        this.appDataDateTime = appDataDateTime;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}

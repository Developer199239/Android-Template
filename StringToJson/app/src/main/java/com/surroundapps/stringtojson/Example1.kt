package com.surroundapps.stringtojson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Murtuza Rahman on 4/2/19.
 */
class Example1 {
    @SerializedName("Category")
    @Expose
    private var category: List<Category1>? = null
    @SerializedName("Collection")
    @Expose
    private var collection: List<Collection1>? = null
    @SerializedName("app_data_date_time")
    @Expose
    private var appDataDateTime: String? = null
    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    fun getCategory(): List<Category1>? {
        return category
    }

    fun setCategory(category: List<Category1>) {
        this.category = category
    }

    fun getCollection(): List<Collection1>? {
        return collection
    }

    fun setCollection(collection: List<Collection1>) {
        this.collection = collection
    }

    fun getAppDataDateTime(): String? {
        return appDataDateTime
    }

    fun setAppDataDateTime(appDataDateTime: String) {
        this.appDataDateTime = appDataDateTime
    }

    fun getSuccess(): Boolean? {
        return success
    }

    fun setSuccess(success: Boolean?) {
        this.success = success
    }

}

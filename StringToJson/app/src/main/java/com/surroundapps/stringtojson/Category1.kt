package com.surroundapps.stringtojson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Murtuza Rahman on 4/2/19.
 */
class Category1 {
    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("application_id")
    @Expose
    private var applicationId: String? = null
    @SerializedName("parent_id")
    @Expose
    private var parentId: String? = null
    @SerializedName("title")
    @Expose
    private var title: String? = null
    @SerializedName("icon")
    @Expose
    private var icon: Any? = null
    @SerializedName("sort_weight")
    @Expose
    private var sortWeight: String? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getApplicationId(): String? {
        return applicationId
    }

    fun setApplicationId(applicationId: String) {
        this.applicationId = applicationId
    }

    fun getParentId(): String? {
        return parentId
    }

    fun setParentId(parentId: String) {
        this.parentId = parentId
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getIcon(): Any? {
        return icon
    }

    fun setIcon(icon: Any) {
        this.icon = icon
    }

    fun getSortWeight(): String? {
        return sortWeight
    }

    fun setSortWeight(sortWeight: String) {
        this.sortWeight = sortWeight
    }

}

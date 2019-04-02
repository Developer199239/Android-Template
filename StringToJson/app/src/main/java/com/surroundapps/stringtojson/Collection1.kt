package com.surroundapps.stringtojson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Murtuza Rahman on 4/2/19.
 */
class Collection1 {
    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("category_id")
    @Expose
    private var categoryId: String? = null
    @SerializedName("descr")
    @Expose
    private var descr: String? = null
    @SerializedName("answer")
    @Expose
    private var answer: Any? = null
    @SerializedName("total_up_vote")
    @Expose
    private var totalUpVote: String? = null
    @SerializedName("total_down_vote")
    @Expose
    private var totalDownVote: String? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getCategoryId(): String? {
        return categoryId
    }

    fun setCategoryId(categoryId: String) {
        this.categoryId = categoryId
    }

    fun getDescr(): String? {
        return descr
    }

    fun setDescr(descr: String) {
        this.descr = descr
    }

    fun getAnswer(): Any? {
        return answer
    }

    fun setAnswer(answer: Any) {
        this.answer = answer
    }

    fun getTotalUpVote(): String? {
        return totalUpVote
    }

    fun setTotalUpVote(totalUpVote: String) {
        this.totalUpVote = totalUpVote
    }

    fun getTotalDownVote(): String? {
        return totalDownVote
    }

    fun setTotalDownVote(totalDownVote: String) {
        this.totalDownVote = totalDownVote
    }

}

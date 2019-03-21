package com.surroundapps.jsonreadwrite.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Murtuza Rahman on 2/27/19.
 */
public class ServerBaseUrl {
    @SerializedName("baseUrl")
    @Expose
    private String baseUrl;

    @SerializedName("baseUrlVCHub")
    @Expose
    private String baseUrlVCHub;

    @SerializedName("baseUrlFirebasePush")
    @Expose
    private String baseUrlFirebasePush;

    @SerializedName("baseUrlXmpp")
    @Expose
    private String baseUrlXmpp;

    @SerializedName("baseUrlImage")
    @Expose
    private String baseUrlImage;


    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrlVCHub() {
        return baseUrlVCHub;
    }

    public void setBaseUrlVCHub(String baseUrlVCHub) {
        this.baseUrlVCHub = baseUrlVCHub;
    }

    public String getBaseUrlFirebasePush() {
        return baseUrlFirebasePush;
    }

    public void setBaseUrlFirebasePush(String baseUrlFirebasePush) {
        this.baseUrlFirebasePush = baseUrlFirebasePush;
    }

    public String getBaseUrlXmpp() {
        return baseUrlXmpp;
    }

    public void setBaseUrlXmpp(String baseUrlXmpp) {
        this.baseUrlXmpp = baseUrlXmpp;
    }

    public String getBaseUrlImage() {
        return baseUrlImage;
    }

    public void setBaseUrlImage(String baseUrlImage) {
        this.baseUrlImage = baseUrlImage;
    }
}

package com.surroundapps.jsonreadwrite.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Murtuza Rahman on 2/27/19.
 */
public class ServerConfig {

    @SerializedName("ServerBaseUrl")
    @Expose
    private ServerBaseUrl serverBaseUrl;

    @SerializedName("debugLogEnable")
    @Expose
    private boolean debugLogEnable;

    public ServerConfig(ServerBaseUrl serverBaseUrl, boolean debugLogEnable) {
        super();
        this.serverBaseUrl = serverBaseUrl;
        this.debugLogEnable = debugLogEnable;
    }

    public ServerBaseUrl getServerBaseUrl() {
        return serverBaseUrl;
    }
}

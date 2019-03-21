package com.surroundapps.jsonreadwrite;

import android.Manifest;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.surroundapps.jsonreadwrite.dataModel.ServerBaseUrl;
import com.surroundapps.jsonreadwrite.dataModel.ServerConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkWriteExternalPermission(false);
    }

    public void writeJson(View view) {
        manageServerConfigFile();
    }

    private void checkWriteExternalPermission(final Boolean writeJson) {
        List<String> needPermissionList = new ArrayList<>();
        if (!hasPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            needPermissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!hasPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            needPermissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (needPermissionList.size() == 0) {
            if (writeJson) {
                if (manageServerConfigFile()) {
                    Toast.makeText(this, "write successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "write failed", Toast.LENGTH_SHORT).show();
                }
            }

        } else {
            requestPermissions(101, needPermissionList,
                    new OnPermissionResponse() {
                        @Override
                        public void onPermissionGranted() {
                            if (writeJson) {
                                if (manageServerConfigFile()) {
                                    Toast.makeText(MainActivity.this, "write successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "write failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onPermissionDenied() {
                            Log.d(TAG, "onPermissionDenied");
                        }
                    });
        }
    }


    private boolean manageServerConfigFile() {

        if (!FileReadWrite.ifConfigFileExist()) {
            Log.e(TAG, "Config file not found creating it.");
            ServerBaseUrl baseUrl = new ServerBaseUrl();

            baseUrl.setBaseUrl(Constants.BASE_URL_DEFAULT);
            baseUrl.setBaseUrlVCHub(Constants.BASE_URL_VCHUB_DEFAULT);
            baseUrl.setBaseUrlFirebasePush(Constants.BASE_URL_FIREBASE_PUSH_DEFAULT);
            baseUrl.setBaseUrlXmpp("192.168....");
            baseUrl.setBaseUrlImage(Constants.IMAGE_BASE_URL_DEFAULT);

            ServerConfig serverConfig = new ServerConfig(baseUrl, Constants.ENABLE_DEBUG_LOG_DEFAULT);

            try {
                FileReadWrite.writeJsonToSDCard(serverConfig);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ServerConfig serverConfig = null;
        try {
            serverConfig = FileReadWrite.readJsonFromSDCard();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (serverConfig != null) {
            String jsonInString = bodyToString(serverConfig);
            updateServerAddresses(serverConfig);
            return true;
        }
        return false;
    }

    private void updateServerAddresses(ServerConfig serverConfig) {

    }

    private String bodyToString(final Object request) {
        try {
            final Object copy = request;
            if (copy != null) {
                Gson gson = new Gson();
                String reqString = gson.toJson(copy).toString();
                Log.e(TAG, "BODY: " + reqString);
                return reqString;
            } else {
                return "";
            }
        } catch (final Exception e) {
            return "did not work";
        }
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


    public File getPublicAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), albumName);

//        File root = new File(Environment.getExternalStorageDirectory(), "VCHUB");
        if (!file.mkdirs()) {
            Log.e(TAG, "Directory not created");
        }
        return file;
    }


}

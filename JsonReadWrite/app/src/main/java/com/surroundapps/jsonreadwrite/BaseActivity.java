package com.surroundapps.jsonreadwrite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;
public class BaseActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();
    private Dialog progressDialog;
    private Toast mToast;
    private OnPermissionResponse permissionResponseListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean neverAskAgainSelected(final String permission) {
        final boolean prevShouldShowStatus = hasPermissions(permission);
        final boolean currShouldShowStatus = this.shouldShowRequestPermissionRationale(permission);
        return prevShouldShowStatus != currShouldShowStatus;
    }

    // region (permission)
    public boolean hasPermissions(String permission) {
        return PackageManager.PERMISSION_GRANTED
                == ContextCompat.checkSelfPermission(this, permission);
    }

    public void requestPermissions(int requestCode, String permissionName, OnPermissionResponse permissionResponseListener) {
        this.permissionResponseListener = permissionResponseListener;
        ActivityCompat.requestPermissions(BaseActivity.this, new String[]{permissionName}, requestCode);
    }

    public void requestPermissions(int requestCode, List<String> permissionName, OnPermissionResponse permissionResponseListener) {
        this.permissionResponseListener = permissionResponseListener;
        String[] permissions = permissionName.toArray(new String[0]);
        ActivityCompat.requestPermissions(BaseActivity.this, permissions, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            permissionResponseListener.onPermissionGranted();
        } else {
            permissionResponseListener.onPermissionDenied();
        }
    }

    // endregion
}

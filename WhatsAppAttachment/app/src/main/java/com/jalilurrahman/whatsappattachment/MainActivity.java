package com.jalilurrahman.whatsappattachment;

import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private AttachmentTypeSelector attachmentTypeSelector;
    private boolean isMmsEnabled = true;
    private boolean isSecurityInitialized = false;
    private boolean isSecureText = true;
    private String TAG = MainActivity.class.getSimpleName();

    private ImageButton attachButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        attachButton = findViewById(R.id.attachButton);

        attachButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleAddAttachment();
            }
        });

        initializeViews();
    }

    private void handleAddAttachment() {
        if (this.isMmsEnabled || isSecureText) {
            if (attachmentTypeSelector == null) {
                attachmentTypeSelector = new AttachmentTypeSelector(this, getSupportLoaderManager(), new AttachmentTypeListener());
            }
            attachmentTypeSelector.show(this, attachButton);
        }
//        else {
//            handleManualMmsRequired();
//        }
    }

    private void initializeViews() {
        attachmentTypeSelector = null;
    }

    private void addAttachment(int type) {
//        linkPreviewViewModel.onUserCancel();

        Log.i(TAG, "Selected: " + type);
        switch (type) {
            case AttachmentTypeSelector.ADD_GALLERY:
                Log.i(TAG, "Selected: " + type);
                break;
            case AttachmentTypeSelector.ADD_DOCUMENT:
                Log.i(TAG, "Selected: " + type);
                break;
            case AttachmentTypeSelector.ADD_SOUND:
                Log.i(TAG, "Selected: " + type);
                break;
            case AttachmentTypeSelector.ADD_CONTACT_INFO:
                Log.i(TAG, "Selected: " + type);
                break;
            case AttachmentTypeSelector.ADD_LOCATION:
                Log.i(TAG, "Selected: " + type);
                break;
            case AttachmentTypeSelector.TAKE_PHOTO:
                Log.i(TAG, "Selected: " + type);
                break;
            case AttachmentTypeSelector.ADD_GIF:
                Log.i(TAG, "Selected: " + type);
                break;
        }
    }

    private class AttachmentTypeListener implements AttachmentTypeSelector.AttachmentClickedListener {
        @Override
        public void onClick(int type) {
            addAttachment(type);
        }

        @Override
        public void onQuickAttachment(Uri uri, String mimeType, String bucketId, long dateTaken, int width, int height, long size) {
            Log.d(TAG, "onQuickAttach");
        }
    }
}



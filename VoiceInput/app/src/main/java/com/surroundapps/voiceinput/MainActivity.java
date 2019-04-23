package com.surroundapps.voiceinput;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView mVoiceText;


    private AudioRecoderUtils mAudioRecoderUtils;
    private PopupWindowFactory mVoicePop;
    private TextView mPopVoiceText;

    ImageView imageView;
    private boolean isSpeakButtonLongPressed = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVoiceText = findViewById(R.id.voiceText);

        /* section 0*/
        imageView = findViewById(R.id.imageView);
        imageView.setOnLongClickListener(speakHoldListener);
        imageView.setOnTouchListener(speakTouchListener);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "single", Toast.LENGTH_SHORT).show();
            }
        });


        /* section 1*/
        View view = View.inflate(this, R.layout.layout_microphone, null);
        mVoicePop = new PopupWindowFactory(this, view);
        final ImageView mImageView = (ImageView) view.findViewById(R.id.iv_recording_icon);
        final TextView mTextView = (TextView) view.findViewById(R.id.tv_recording_time);
        mPopVoiceText = (TextView) view.findViewById(R.id.tv_recording_text);

        /* section 2*/
        mAudioRecoderUtils = new AudioRecoderUtils();
        mAudioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {

            //录音中....db为声音分贝，time为录音时长
            @Override
            public void onUpdate(double db, long time) {
                mImageView.getDrawable().setLevel((int) (3000 + 6000 * db / 100));
                mTextView.setText(Utils.long2String(time));
            }

            //录音结束，filePath为保存路径
            @Override
            public void onStop(long time, String filePath) {
                mTextView.setText(Utils.long2String(0));
                Log.d("tag",filePath);
                //todo here
                /*MessageInfo messageInfo = new MessageInfo();
                messageInfo.setFilepath(filePath);
                messageInfo.setVoiceTime(time);
                EventBus.getDefault().post(messageInfo);*/
            }

            @Override
            public void onError() {
                mVoiceText.setVisibility(View.GONE);
                //todo here
//                mEditText.setVisibility(View.VISIBLE);
            }
        });

        mVoiceText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mVoicePop.showAtLocation(v, Gravity.CENTER, 0, 0);
                        mVoiceText.setText("Release end");
                        mPopVoiceText.setText("Slip on your finger, cancel sending");
                        mVoiceText.setTag("1");
                        mAudioRecoderUtils.startRecord(MainActivity.this);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (wantToCancle(x, y)) {
                            mVoiceText.setText("Release end");
                            mPopVoiceText.setText("Release your finger and cancel sending");
                            mVoiceText.setTag("2");
                        } else {
                            mVoiceText.setText("Release end");
                            mPopVoiceText.setText("Slip on your finger, cancel sending");
                            mVoiceText.setTag("1");
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        mVoicePop.dismiss();
                        if (mVoiceText.getTag().equals("2")) {
                            //取消录音（删除录音文件）
                            //Cancel recording (delete recording file)
                            mAudioRecoderUtils.cancelRecord();
                        } else {
                            //结束录音（保存录音文件）
                            //End recording (save recording file)
                            mAudioRecoderUtils.stopRecord();
                        }
                        mVoiceText.setText("Hold and talk");
                        mVoiceText.setTag("3");
//                        mVoiceText.setVisibility(View.GONE);
                        //todo here
//                        mEditText.setVisibility(View.VISIBLE);
                        break;
                }

                return true;
            }
        });

    }


    private boolean wantToCancle(int x, int y) {
        // 超过按钮的宽度
        if (x < 0 || x > mVoiceText.getWidth()) {
            return true;
        }
        // 超过按钮的高度
        if (y < -50 || y > mVoiceText.getHeight() + 50) {
            return true;
        }
        return false;
    }

    private View.OnLongClickListener speakHoldListener = new View.OnLongClickListener() {

        @Override
        public boolean onLongClick(View pView) {
            Toast.makeText(MainActivity.this, "Long", Toast.LENGTH_SHORT).show();
            // Do something when your hold starts here.
            isSpeakButtonLongPressed = true;

            mVoicePop.showAtLocation(pView, Gravity.CENTER, 0, 0);
            mVoiceText.setText("Release end");
            mPopVoiceText.setText("Slip on your finger, cancel sending");
            mVoiceText.setTag("1");
            mAudioRecoderUtils.startRecord(MainActivity.this);
            return true;
        }
    };

    private View.OnTouchListener speakTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            v.onTouchEvent(event);

            int x = (int) event.getX();
            int y = (int) event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
//                    mVoicePop.showAtLocation(v, Gravity.CENTER, 0, 0);
//                    mVoiceText.setText("Release end");
//                    mPopVoiceText.setText("Slip on your finger, cancel sending");
//                    mVoiceText.setTag("1");
//                    mAudioRecoderUtils.startRecord(MainActivity.this);
                    break;
                case MotionEvent.ACTION_MOVE:
                    if(!isSpeakButtonLongPressed) {
                        return false;
                    }
                    if (wantToCancle(x, y)) {
                        mVoiceText.setText("Release end");
                        mPopVoiceText.setText("Release your finger and cancel sending");
                        mVoiceText.setTag("2");
                    } else {
                        mVoiceText.setText("Release end");
                        mPopVoiceText.setText("Slip on your finger, cancel sending");
                        mVoiceText.setTag("1");
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if(!isSpeakButtonLongPressed) {
                        return false;
                    }
                    mVoicePop.dismiss();
                    if (mVoiceText.getTag().equals("2")) {
                        //取消录音（删除录音文件）
                        //Cancel recording (delete recording file)
                        mAudioRecoderUtils.cancelRecord();
                    } else {
                        //结束录音（保存录音文件）
                        //End recording (save recording file)
                        mAudioRecoderUtils.stopRecord();
                    }
                    mVoiceText.setText("Hold and talk");
                    mVoiceText.setTag("3");
//                        mVoiceText.setVisibility(View.GONE);
                    //todo here
//                        mEditText.setVisibility(View.VISIBLE);
                    isSpeakButtonLongPressed = false;
                    break;
            }


            return true;
        }
    };

}

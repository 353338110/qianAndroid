package com.qian.fragment;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;

import com.nostra13.universalimageloader.utils.L;
import com.qian.R;
import com.qian.base.BaseFragment;
import com.qian.base.BasePresenter;
import com.qian.oksocket.OkSocketAdapter;
import com.qian.oksocket.SendBean;
import com.qian.utils.Constants;
import com.shrikanthravi.chatview.data.Message;
import com.shrikanthravi.chatview.widget.ChatView;
import com.xuhao.android.libsocket.sdk.ConnectionInfo;
import com.xuhao.android.libsocket.sdk.OkSocketOptions;
import com.xuhao.android.libsocket.sdk.connection.IConnectionManager;
import com.xuhao.android.libsocket.sdk.protocol.IHeaderProtocol;
import com.xuhao.android.libsocket.utils.BytesUtils;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import java.io.File;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;
import static com.xuhao.android.libsocket.sdk.OkSocket.open;

/**
 * Created by master on 2017/8/25.
 */

public class WhispersFragment extends BaseFragment {
    @BindView(R.id.chatView)
    ChatView chatView;
    private ConnectionInfo mInfo;
    private IConnectionManager mManager;
    private OkSocketOptions mOkOptions;
    private OkSocketAdapter okSocketAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_whispers;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        createOkSocket();
        Message message = new Message();
        message.setBody("你好");
        message.setMessageType(Message.MessageType.RightSimpleImage);
        message.setTime(getTime());
        message.setUserName("Groot");
        chatView.addMessage(message);





    }

    @Override
    protected void initEvent() {
        chatView.setOnClickSendButtonListener(new ChatView.OnClickSendButtonListener() {
            @Override
            public void onSendButtonClick(String body) {

                Message message = new Message();
                message.setBody(body);
                message.setMessageType(Message.MessageType.RightSimpleImage);
                message.setTime(getTime());
                message.setUserName(Constants.uidUser.getUser().getUsername());
                //message.setUserIcon(Uri.parse("android.resource://com.shrikanthravi.chatviewlibrary/drawable/groot"));
                sendData(message);
                chatView.addMessage(message);


            }
        });

        //Gallery button click listener
        chatView.setOnClickGalleryButtonListener(new ChatView.OnClickGalleryButtonListener() {
            @Override
            public void onGalleryButtonClick() {
                Matisse.from(mActivity)
                        .choose(MimeType.allOf())
                        .countable(true)
                        .maxSelectable(9)
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new PicassoEngine())
                        .forResult(imagePickerRequestCode);
            }
        });

        //Video button click listener
        chatView.setOnClickVideoButtonListener(new ChatView.OnClickVideoButtonListener() {
            @Override
            public void onVideoButtonClick() {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                i.setType("video/*");
                startActivityForResult(i, SELECT_VIDEO);
            }
        });

        //Camera button click listener
        chatView.setOnClickCameraButtonListener(new ChatView.OnClickCameraButtonListener() {
            @Override
            public void onCameraButtonClicked() {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                File file = new File(Environment.getExternalStorageDirectory(), "MyPhoto.jpg");
                file.delete();
                File file1 = new File(Environment.getExternalStorageDirectory(), "MyPhoto.jpg");

                Uri uri = FileProvider.getUriForFile(mActivity, mActivity.getPackageName() + ".provider", file1);
                cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });


        chatView.setOnClickAudioButtonListener(new ChatView.OnClickAudioButtonListener() {
            @Override
            public void onAudioButtonClicked() {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("audio/*");
                //String[] mimetypes = {"audio/3gp", "audio/AMR", "audio/mp3"};
                //i.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(i, SELECT_AUDIO);
            }
        });
    }
    private void createOkSocket() {
            /*默认的配置   如果没有修改。都按照这个默认的配置走
        * OkSocketOptions okOptions = new OkSocketOptions();
        okOptions.mPulseFrequency = 5 * 1000;默认发送心跳间隔5秒
        okOptions.mIOThreadMode = IOThreadMode.DUPLEX;
        okOptions.mHeaderProtocol = new DefaultNormalHeaderProtocol();
        okOptions.mMaxReadDataMB = 10;
        okOptions.mConnectTimeoutSecond = 3;
        okOptions.mWritePackageBytes = 100;
        okOptions.mReadPackageBytes = 50;单次读最大字节
        okOptions.mReadByteOrder = ByteOrder.BIG_ENDIAN;
        okOptions.mWriteOrder = ByteOrder.BIG_ENDIAN;
        okOptions.isConnectionHolden = true;
        okOptions.mPulseFeedLoseTimes = 5;
        okOptions.mReconnectionManager = new DefaultReconnectManager();
        okOptions.mSSLConfig = null;
        return okOptions;*/
        mInfo = new ConnectionInfo(Constants.HOST, Constants.TCP_PORT);
        mOkOptions = new OkSocketOptions.Builder()
                .setConnectionHolden(true)
                .setPulseFrequency(10 * 1000)//心跳间隔10秒
                .setPulseFeedLoseTimes(3)
                .setHeaderProtocol(new IHeaderProtocol() {
                    @Override
                    public int getHeaderLength() {
                        return 9;
                    }

                    @Override
                    public int getBodyLength(byte[] header, ByteOrder byteOrder) {
                        if (header == null || header.length == 0) {
                            return 0;
                        }
                        if (ByteOrder.BIG_ENDIAN.toString().equals(byteOrder.toString())) {
                            return BytesUtils.bytesToInt2(header, 7);
                        } else {
                            return BytesUtils.bytesToInt(header, 7);
                        }
                    }
                })
                .build();
        mManager = open(mInfo).option(mOkOptions);
        okSocketAdapter = new OkSocketAdapter();
        mManager.registerReceiver(okSocketAdapter);
        mManager.connect();
    }
    private void sendData(Message message) {
        if (null == mManager) {
            L.w("mManager为空 重新创建");
            createOkSocket();
        } else {
            try {
                if (!mManager.isConnect()) {
                    L.w("mManager断开连接,重新连接");
                    createOkSocket();
                }else {
                    mManager.send(new SendBean(message));
                    L.w("发送成功");
                }
            } catch (Exception e) {
                L.w("发送错误", e);

            }

        }
    }
    public String getTime() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat mdformat = new SimpleDateFormat("dd MMM yyyy HH:mm");
        String time = mdformat.format(date);
        return time;
    }

    public static int imagePickerRequestCode=10;
    public static int SELECT_VIDEO=11;
    public static int CAMERA_REQUEST=12;
    public static int SELECT_AUDIO=13;
    List<Uri> mSelected;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        switch (requestCode){
            case 10:{

                //Image Selection result
                if(resultCode==RESULT_OK){
                    mSelected = Matisse.obtainResult(data);

                        if (mSelected.size() == 1) {
                            Message message = new Message();
                            message.setBody("不知道写什么");
                            message.setMessageType(Message.MessageType.RightSingleImage);
                            message.setTime(getTime());
                            message.setUserName("Groot");
                            message.setImageList(mSelected);
                            message.setUserIcon(Uri.parse("android.resource://com.shrikanthravi.chatviewlibrary/drawable/groot"));
                            chatView.addMessage(message);

                    }
                    else{

                        if (mSelected.size() == 1) {
                            Message message = new Message();
                            message.setBody("随便乱写");
                            message.setMessageType(Message.MessageType.LeftSingleImage);
                            message.setTime(getTime());
                            message.setUserName("Hodor");
                            message.setImageList(mSelected);
                            message.setUserIcon(Uri.parse("android.resource://com.shrikanthravi.chatviewlibrary/drawable/hodor"));
                            chatView.addMessage(message);
                        } else {

                            Message message = new Message();
                            message.setBody("还写不写");
                            message.setMessageType(Message.MessageType.LeftMultipleImages);
                            message.setTime(getTime());
                            message.setUserName("Hodor");
                            message.setImageList(mSelected);
                            message.setUserIcon(Uri.parse("android.resource://com.shrikanthravi.chatviewlibrary/drawable/hodor"));
                            chatView.addMessage(message);
                        }

                    }
                }
                break;
            }
            case 11:{

                //Video Selection Result
                if(resultCode == RESULT_OK) {
                        Message message = new Message();
                        message.setMessageType(Message.MessageType.RightVideo);
                        message.setTime(getTime());
                        message.setUserName("Groot");
                        message.setVideoUri(Uri.parse(getPathVideo(data.getData())));
                        message.setUserIcon(Uri.parse("android.resource://com.shrikanthravi.chatviewlibrary/drawable/groot"));
                        chatView.addMessage(message);

                }
                break;
            }
            case 12:{

                //Image Capture result

                if (resultCode == RESULT_OK) {


                        Message message = new Message();
                        message.setMessageType(Message.MessageType.RightSingleImage);
                        message.setTime(getTime());
                        message.setUserName("Groot");
                        mSelected.clear();
                        File file = new File(Environment.getExternalStorageDirectory(), "MyPhoto.jpg");
                        //Uri of camera image
                        Uri uri = FileProvider.getUriForFile(mActivity, mActivity.getPackageName() + ".provider", file);
                        mSelected.add(uri);
                        message.setImageList(mSelected);
                        message.setUserIcon(Uri.parse("android.resource://com.shrikanthravi.chatviewlibrary/drawable/groot"));
                        chatView.addMessage(message);


                }
                break;
            }
            case 13:{
                if(resultCode == RESULT_OK){
                        Message message = new Message();
                        message.setMessageType(Message.MessageType.RightAudio);
                        message.setTime(getTime());
                        message.setUserName("Groot");
                        message.setAudioUri(Uri.parse(getPathAudio(data.getData())));
                        message.setUserIcon(Uri.parse("android.resource://com.shrikanthravi.chatviewlibrary/drawable/groot"));
                        chatView.addMessage(message);


                }
                break;
            }
        }

    }

    public String getPathAudio(Uri uri) {
        System.out.println("getpath "+uri.toString());
        String[] projection = { MediaStore.Audio.Media.DATA };
        Cursor cursor = mActivity.getContentResolver().query(uri, projection, null, null, null);

        int columnIndex = cursor.getColumnIndex(projection[0]);
        cursor.moveToFirst();
        if(cursor!=null) {
            return cursor.getString(columnIndex);
        }
        else return null;
    }
    public String getPathVideo(Uri uri) {
        System.out.println("getpath "+uri.toString());
        String[] projection = { MediaStore.Video.Media.DATA };
        Cursor cursor = mActivity.managedQuery(uri, projection, null, null, null);
        if(cursor!=null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        else return null;
    }
}

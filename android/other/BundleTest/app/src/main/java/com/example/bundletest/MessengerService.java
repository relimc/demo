package com.example.bundletest;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import androidx.annotation.NonNull;

public class MessengerService extends Service {

    private static final String TAG = "MessengerService";

    private static class MessengerHandler extends Handler {  // 消息处理器
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:  // 处理标识为 1 的消息

                    // 处理客户端发送过来的消息
                    String msg1 = msg.getData().getString("msg");
                    Log.d(TAG, msg1);

                    // 向客户端回复消息
                    Messenger client = msg.replyTo;
                    Message message = Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putString("reply", "嗯，你的消息我已经收到，稍后会回复你。");
                    message.setData(bundle);
                    message.what = 2;
                    try {
                        client.send(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                  break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
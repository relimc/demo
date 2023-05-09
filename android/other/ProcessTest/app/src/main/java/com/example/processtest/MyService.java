package com.example.processtest;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;

public class MyService extends Service {

    private final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                Bundle data = msg.getData();
                String test = data.getString("test");
                Log.d("MyService", "get message from client: " + test);

                Messenger replyTo = msg.replyTo;
                Bundle bundle = new Bundle();
                bundle.putString("replyTo", "message from serv");
                Message message = Message.obtain();
                message.what = 11;
                message.setData(bundle);
                try {
                    replyTo.send(message);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    };

    private final Messenger messenger = new Messenger(handler);

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
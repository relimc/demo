package com.example.processtest.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.processtest.IMyAidlInterface;

public class AidlService extends Service {

    private final Binder mBinder = new IMyAidlInterface.Stub() {
        @Override
        public void test() throws RemoteException {
            Log.d("AidlService", "server message");
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
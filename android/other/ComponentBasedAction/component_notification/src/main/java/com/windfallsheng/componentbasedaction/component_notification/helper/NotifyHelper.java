package com.windfallsheng.componentbasedaction.component_notification.helper;

import android.util.Log;

public class NotifyHelper {

    public static NotifyHelper getInstance() {
        return NotifyHelper.SingletonHolder.INSTANCE;
    }

    public void notify(String message) {
        Log.d("NotifyHelper", "notify#message=" + message);
    }

    private static class SingletonHolder {
        private static final NotifyHelper INSTANCE = new NotifyHelper();
    }
}

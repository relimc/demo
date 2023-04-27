package com.windfallsheng.componentbasedaction.component_network.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import com.windfallsheng.componentbasedaction.component_lib.util.Logger;

public class NetworkUtils {

    public static boolean isConn(Context context) {
        ConnectivityManager connMgr =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiConn = false;
        boolean isMobileConn = false;
        for (Network network : connMgr.getAllNetworks()) {
            NetworkInfo networkInfo = connMgr.getNetworkInfo(network);
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isWifiConn |= networkInfo.isConnected();
            }
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                isMobileConn |= networkInfo.isConnected();
            }
        }
        Logger.dl("NetworkUtils", "Wifi connected: " + isWifiConn);
        Logger.dl("NetworkUtils", "Mobile connected: " + isMobileConn);
        return isWifiConn || isMobileConn;
    }
}

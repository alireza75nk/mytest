package ir.android.mytest.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AndroidAPI {

    public static boolean isInternetAvailable(Activity activity){
        ConnectivityManager manager =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        // Wifi or Mobile Network is present and connected
        return networkInfo != null && networkInfo.isConnected();
    }
}

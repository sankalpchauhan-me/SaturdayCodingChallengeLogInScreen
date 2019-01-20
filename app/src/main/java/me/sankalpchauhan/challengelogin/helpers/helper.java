package me.sankalpchauhan.challengelogin.helpers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 *  I created a class for most commonly used method so that they don't have to be written again and again
 */

public class helper extends AppCompatActivity {


    //GENERIC DISPLAY DIALOG
    public void DisplayDialog(Context context, String title, String message, String posText) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title).setMessage(message).setPositiveButton(posText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }

    //GENERIC TOAST
    public void StandardToast(Context context, String Message) {
        Toast.makeText(context, Message, Toast.LENGTH_SHORT).show();
    }

    //Custom Toast with Image
    public void ImageInToast(Context context, String Message, int ImageId) {
        Toast toast = Toast.makeText(context, Message, Toast.LENGTH_SHORT);
        LinearLayout toastContentView = (LinearLayout) toast.getView();
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(ImageId);
        toastContentView.addView(imageView, 0);
        toast.show();

    }


    //CHECK NETWORK STATE----------------------------------------------------------------------------------------------------------------------
    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        Log.i("NetworkState", "isConnected checking network state.......");
        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else
            return false;
    }

    public android.support.v7.app.AlertDialog.Builder buildNetworkDialog(Context c) {
        Log.i("NetworkState", "Network Failed......... Building Dialog");
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or WIFI to access this app. Press Enable to access network settings");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("Enable", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent in = new Intent(android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS);
                c.startActivity(in);
            }
        });

        return builder;
    }


}

package com.prashant.demowipro.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.prashant.demowipro.R;

/**
 * The type Utils.
 */
public class Utils {

    /**
     * Network dialog.
     *
     * @param activity the activity
     */
    public static void networkDialog(final Activity activity) {

        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style
                    .CustomDialogTheme);
            String labelTextOK = activity.getString(R.string.ok);;
            String labelTextTitle = activity.getString(R.string.network);
            String labelTextMessage = activity.getString(R.string.no_network);


            builder.setTitle(labelTextTitle);

            builder.setMessage(labelTextMessage)
                    .setCancelable(false)
                    .setPositiveButton(labelTextOK,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    try {
                                        dialog.cancel();
                                        activity.finish();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

            AlertDialog alert = builder.create();
            if (!alert.isShowing())
                alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

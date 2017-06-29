package com.android.activelife.tampa.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;


import com.android.activelife.tampa.R;

import java.util.Set;

/**
 * Created by skasam on 2/16/2017.
 */
public class AppPropertiesUtil {
    private String app_reference = "com.mtuity.ecentriclams.credetentials";
    // storing the current latitude into shared preferences with this key
    public String CURRENT_LATITUDE = "current_latitude";
    // storing the current longitude into shared preferences with this key
    public String CURRENT_LONGITUDE = "current_longitude";
    // storing the current latitude into shared preferences with this key
    public String APP_LATITUDE = "app_latitude";
    // storing the current longitude into shared preferences with this key
    public String APP_LONGITUDE = "app_longitude";

    public static String getGCMID(Context context) {
        if (context == null) {
            return null;
        }
        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        String gcmID = appPref.getString(Utilities.KEY_GCM_ID, "none");

        return gcmID;
    }

    public static String getDeviceId(Context context){
        if (context == null) {
            return null;
        }
        String android_id = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return android_id;
    }


    public static String getSessionId(Context context) {
        String sessionId = null;
        if (context != null) {
            SharedPreferences appPref = context.getSharedPreferences(Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
            sessionId = appPref.getString(Utilities.KEY_SESSION_ID, "");
        }
        return sessionId;
    }

    public static void setSessionID(Context context, String sessionID) {
        if (context != null) {
            SharedPreferences appPref = context.getSharedPreferences(Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = appPref.edit();
            if (editor != null) {
                editor.putString(Utilities.KEY_SESSION_ID, sessionID);
                editor.commit();
            }
        }
    }
    public static String getAuthId(Context context) {
        String sessionId = null;
        if (context != null) {
            SharedPreferences appPref = context.getSharedPreferences(Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
            sessionId = appPref.getString(Utilities.KEY_AUTH_ID, "");
        }
        return sessionId;
    }

    public static void setAuthID(Context context, String sessionID) {
        if (context != null) {
            SharedPreferences appPref = context.getSharedPreferences(Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = appPref.edit();
            if (editor != null) {
                editor.putString(Utilities.KEY_AUTH_ID, sessionID);
                editor.commit();
            }
        }
    }

    public static void setGCMID(Context context, String gcmID) {

        if(context!=null) {
            SharedPreferences appPref = context.getApplicationContext().getSharedPreferences(
                    Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = appPref.edit();
            if (editor != null) {
                editor.putString(Utilities.KEY_GCM_ID, gcmID);
                editor.commit();
            }}

    }

    public static void setUserID(Context context, String userID) {
        if(context!=null){
            SharedPreferences appPref = context.getApplicationContext().getSharedPreferences(
                    Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = appPref.edit();
            if (editor != null) {
                editor.putString(Utilities.KEY_USER_ID, userID);
                editor.commit();
            }
        }}

    public static String getUserID(Context context) {

        SharedPreferences appPref = context.getSharedPreferences(Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return appPref.getString(Utilities.KEY_USER_ID, "none");
    }

    public static void clearPrefs(Context context) {
        if(context!=null){
            SharedPreferences preferences = context.getApplicationContext().getSharedPreferences(Utilities.KEY_APP_PREFERENCE_NAME, 0);
            preferences.edit().remove(Utilities.KEY_USER_ID).commit();
            preferences.edit().remove(Utilities.KEY_GCM_ID).commit();
            preferences.edit().remove(Utilities.KEY_SESSION_ID).commit();
            preferences.edit().remove(Utilities.KEY_PROFILE_PIC).commit();
            preferences.edit().remove(Utilities.KEY_USER_NAME).commit();
            preferences.edit().clear().commit();}
    }

    public static void setUserName(Context context, String userName) {
        if(context!=null) {
            SharedPreferences appPref = context.getApplicationContext().getSharedPreferences(
                    Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = appPref.edit();
            if (editor != null) {
                editor.putString(Utilities.KEY_USER_NAME, userName);
                editor.commit();
            }
        }
    }

    public static String getUserName(Context context) {

        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        return appPref.getString(Utilities.KEY_USER_NAME, null);

    }
    public static void setUserAgencyId(Context context, String userName) {
        if(context!=null) {
            SharedPreferences appPref = context.getApplicationContext().getSharedPreferences(
                    Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = appPref.edit();
            if (editor != null) {
                editor.putString(Utilities.KEY_USER_AGENCY_ID, userName);
                editor.commit();
            }
        }
    }

    public static String getUserAgencyId(Context context) {

        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        return appPref.getString(Utilities.KEY_USER_AGENCY_ID, null);

    }
    public static void setUserRole(Context context, String userName) {
        if(context!=null) {
            SharedPreferences appPref = context.getApplicationContext().getSharedPreferences(
                    Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = appPref.edit();
            if (editor != null) {
                editor.putString(Utilities.KEY_USER_ROLE, userName);
                editor.commit();
            }
        }
    }

    public static String getUserRole(Context context) {

        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        return appPref.getString(Utilities.KEY_USER_ROLE, null);

    }
    public static void setUserMangerId(Context context, String userName) {
        if(context!=null) {
            SharedPreferences appPref = context.getApplicationContext().getSharedPreferences(
                    Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = appPref.edit();
            if (editor != null) {
                editor.putString(Utilities.KEY_USER_MANGER_ID, userName);
                editor.commit();
            }
        }
    }

    public static String getUserMangerId(Context context) {

        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        return appPref.getString(Utilities.KEY_USER_MANGER_ID, null);

    }

    public static void setUserEmail(Context context, String email) {

        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = appPref.edit();
        if (editor != null) {
            editor.putString(Utilities.KEY_USER_EMAIL, email);
            editor.commit();
        }

    }

    public static String getUseEmail(Context context) {

        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        return appPref.getString(Utilities.KEY_USER_EMAIL, null);

    }


    public static void setProfilePic(Context context, String userName) {

        if(context!=null) {
            SharedPreferences appPref = context.getApplicationContext().getSharedPreferences(
                    Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = appPref.edit();
            if (editor != null) {
                editor.putString(Utilities.KEY_PROFILE_PIC, userName);
                editor.commit();
            }
        }
    }

    public static String getProfilePic(Context context) {

        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        return appPref.getString(Utilities.KEY_PROFILE_PIC, null);

    }


    public static void setBline(Context context, String userName) {

        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = appPref.edit();
        if (editor != null) {
            editor.putString(Utilities.KEY_BLINE, userName);
            editor.commit();
        }

    }

    public static String getBline(Context context) {

        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        return appPref.getString(Utilities.KEY_BLINE, null);

    }

    public static void setCompany(Context context, String userName) {

        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = appPref.edit();
        if (editor != null) {
            editor.putString(Utilities.KEY_COMPANY, userName);
            editor.commit();
        }

    }

    public static String getCompany(Context context) {

        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        return appPref.getString(Utilities.KEY_COMPANY, null);

    }

    public static void setDesg(Context context, String userName) {

        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = appPref.edit();
        if (editor != null) {
            editor.putString(Utilities.KEY_DESG, userName);
            editor.commit();
        }

    }

    public static String getDesg(Context context) {

        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        return appPref.getString(Utilities.KEY_DESG, null);

    }

    public static Dialog showProgressDialog(Context mContext, String text, boolean cancelable) {
        Dialog mDialog = new Dialog(mContext, R.style.ProgressBarTheme);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        View layout = mInflater.inflate(R.layout.custom_progressbar, null);
        mDialog.setContentView(layout);

        TextView tvProgressMessage = (TextView) layout.findViewById(R.id.tvProgressMessage);

        if (text.equals(""))
            tvProgressMessage.setVisibility(View.GONE);
        else
            tvProgressMessage.setText(text);

        mDialog.setCancelable(cancelable);
        mDialog.setCanceledOnTouchOutside(cancelable);
        /* mDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);*/
        mDialog.show();

        return mDialog;
    }

    public static SharedPreferences getPrefs(Context context) {
        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return appPref;
    }

    public static void dismissKeyboard(Activity context, EditText editText) {

        if (context != null && editText != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }


    public static void setTopics(Context context, Set<String> topics) {

        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = appPref.edit();
        if (editor != null) {
            editor.putStringSet(Utilities.KEY_TOPIC, topics);
            editor.commit();
        }

    }

    public static Set<String> getTopics(Context context) {

        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        return appPref.getStringSet(Utilities.KEY_TOPIC, null);

    }


    public static void logoutPopup(final Context context) {
        if(context!=null){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(R.string.session_expired);
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    clearPrefs(context);
                    /*Intent in = new Intent(context, LoginActivity.class);
                    context.startActivity(in);
                    for (Activity activity : Utilities.activities) {
                        if (activity != null && !activity.isDestroyed())
                            activity.finish();
                    }*/
                }
            });

            AlertDialog dialog = builder.create();
            dialog.setCancelable(false);
            dialog.show();}
    }




    public static void setHost(Context context, String userName) {
        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = appPref.edit();
        if (editor != null) {
            editor.putString(Utilities.KEY_HOST, userName);
            editor.commit();
        }
    }

    public static String getHost(Context context) {

        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);

        return appPref.getString(Utilities.KEY_HOST, null);

    }

    public static void setPort(Context context, String userName) {
        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = appPref.edit();
        if (editor != null) {
            editor.putString(Utilities.KEY_PORT, userName);
            editor.commit();
        }
    }

    public static String getPort(Context context) {
        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return appPref.getString(Utilities.KEY_PORT, null);
    }

    public static void setUid(Context context, String userName) {
        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = appPref.edit();
        if (editor != null) {
            editor.putString(Utilities.KEY_UID, userName);
            editor.commit();
        }
    }

    public static String getUid(Context context) {
        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return appPref.getString(Utilities.KEY_UID, null);
    }

    public static void setPwd(Context context, String userName) {
        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = appPref.edit();
        if (editor != null) {
            editor.putString(Utilities.KEY_PWD, userName);
            editor.commit();
        }
    }

    public static String getPwd(Context context) {
        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return appPref.getString(Utilities.KEY_PWD, null);
    }
    public static void setAttendanceId(Context context, String userName) {
        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = appPref.edit();
        if (editor != null) {
            editor.putString(Utilities.KEY_AID, userName);
            editor.commit();
        }
    }

    public static String getAttendanceId(Context context) {
        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return appPref.getString(Utilities.KEY_AID, null);
    }

    /**
     *
     * @param context
     * @param Key
     * @param Value
     */
    public void storeValueIntoSharedPreference(Context context, String Key,
                                               String Value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Utilities.KEY_APP_PREFERENCE_NAME,
                Context.MODE_PRIVATE).edit();
        editor.putString(Key, Value);
        editor.commit();
    }

    public static String getValueIntoSharedPreferences(Context context, String Key) {
        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return appPref.getString(Key, null);
    }

    /**
     *
     * @param context
     * @param Key
     * @param Value
     */
    public void storeBooleanValueIntoSharedPreference(Context context, String Key,
                                               boolean Value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Utilities.KEY_APP_PREFERENCE_NAME,
                Context.MODE_PRIVATE).edit();
        editor.putBoolean(Key, Value);
        editor.commit();
    }

    public static boolean getBooleanValueIntoSharedPreferences(Context context, String Key) {
        SharedPreferences appPref = context.getSharedPreferences(
                Utilities.KEY_APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return appPref.getBoolean(Key, false);
    }
}

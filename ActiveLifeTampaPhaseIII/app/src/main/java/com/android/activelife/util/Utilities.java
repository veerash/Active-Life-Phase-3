package com.android.activelife.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.android.activelife.R;
import com.android.activelife.prefs.AppSharedPref;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.logging.Level;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by skasam on 2/16/2017.
 */
public class Utilities {

    public static final String GALLERY_IMAGE_ITEM = "gallery_image_item";
    public static final String FROM_EVENT_CREATE_EDIT = "event_create_edit";
    public static final String FROM_GALLERY_FRAGMENT = "from_gallery_fragment";
    public static final String IS_FROM_CAMERA = "is_from_camera";
    public static final String CROP_IMAGES = "/Crop Images/";
    public static final String IMAGE_PATH = "image_path";
    public static String FOLDER_PATH = "/ecentriclams/";
    public static final String MEMBERS_LIST = "com.ecentriclams.ecentriclams.members.list";
    public static String RECEIVE_CHAT = "com.mtuity.ecentriclams.fragments.GroupChatFragment";

    public static void showToast(Context context, String message) {
        if(context!=null){
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    public static HashSet<Activity> activities = new HashSet<Activity>();
    public static String currentChatId;
    public static boolean isRefresh = false;
    public static final int MEDIA_GALLERY = 1;

    public static final String KEY_APP_PREFERENCE_NAME = "com.mtuity.ecentriclams";
    public static final String KEY_GCM_ID = "com.ecentriclams.ecentriclams.gcmid";
    public static final String KEY_USER_ID = "com.ecentriclams.ecentriclams.userid";
    public static final String KEY_USER_NAME = "com.ecentriclams.ecentriclams.username";
    public static final String KEY_USER_AGENCY_ID = "com.ecentriclams.ecentriclams.user.agencyid";
    public static final String KEY_USER_ROLE = "com.ecentriclams.ecentriclams.user.role";
    public static final String KEY_USER_MANGER_ID = "com.ecentriclams.ecentriclams.user.mangerid";
    public static final String KEY_SESSION_ID = "com.ecentriclams.ecentriclams.session.id";
    public static final String KEY_AUTH_ID = "com.ecentriclams.ecentriclams.auth.id";
    public static final String KEY_PROFILE_PIC = "com.ecentriclams.ecentriclams.profilepic";
    public static final String KEY_USER_EMAIL ="com.ecentriclams.ecentriclams.useremail";
    public static final String KEY_BLINE = "com.ecentriclams.ecentriclams.bline";
    public static final String KEY_COMPANY = "com.ecentriclams.ecentriclams.company";
    public static final String KEY_DESG = "com.ecentriclams.ecentriclams.designation";
    public static final String KEY_TOPIC = "com.ecentriclams.ecentriclams.topic";
    public static final String KEY_HOST = "com.ecentriclams.ecentriclams.host";
    public static final String KEY_PORT = "com.ecentriclams.ecentriclams.port";
    public static final String KEY_UID = "com.ecentriclams.ecentriclams.uid";
    public static final String KEY_PWD = "com.ecentriclams.ecentriclams.pwd";
    public static final String KEY_AID = "com.ecentriclams.ecentriclams.aid";
    public static final String KEY_ADMIN = "com.ecentriclams.ecentriclams.admin";
    public static final int CROP_ACTIVITY = 1001;

    public static void showOrhideKeyBoard(Context context, boolean show, View view)
    {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && show) {
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
        if(inputMethodManager!=null && view!=null && !show)
        {
            inputMethodManager.hideSoftInputFromInputMethod(view.getWindowToken(), 0);
        }
    }

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme()
                .obtainStyledAttributes(new int[] { R.attr.actionBarSize });
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

    public static boolean saveJSONArrayToPrivateFilesDir(Context context, String jsonArray, String destFileName) {
        boolean isSaved = false;
        JSONArray jarray = null;
        try {
            jarray = new JSONArray(jsonArray);
            System.out.println("saveJSONArrayToPrivateFilesDir jarray length "+ jarray.length());
        } catch (JSONException e1) {
            java.util.logging.Logger.getAnonymousLogger().log(Level.SEVERE, e1.getMessage(), e1);
        }

        OutputStreamWriter writer = null;
        if (context != null && !TextUtils.isEmpty(jsonArray)
                && !TextUtils.isEmpty(destFileName) && jarray!=null && jarray.length()>0) {
            try {
                if (jsonArray != null) {
                    String appPrivateFilesDirectory = context.getFilesDir()
                            .getAbsolutePath();
                    File filesDir = new File(appPrivateFilesDirectory);
                    if (!filesDir.exists()) {
                        filesDir.mkdirs();
                    }

                    File destFile = new File(filesDir, destFileName);

                    writer = new OutputStreamWriter(new FileOutputStream(
                            destFile));
                    // writer.write(resultJSON.toString());
                    writer.write(jsonArray);
                    writer.close();
                    isSaved = true;
                }
            } catch (FileNotFoundException fnfe) {
                Log.e("TAG", fnfe.toString());
            } catch (Exception e) {
                Log.e("TAG", e.toString());
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException ioe) {
                        Log.e("TAG", ioe.toString());
                    }
                }
            }
        }
        return isSaved;
    }

    public static String getJsonStringFromFile(Context context, String destFileName) {
        if (context == null || TextUtils.isEmpty(destFileName)) {
            return null;
        }
        StringBuilder text = new StringBuilder();
        BufferedReader br = null;
        try {

            String appPrivateFilesDirectory = context.getFilesDir()
                    .getAbsolutePath();
            File filesDir = new File(appPrivateFilesDirectory);
            if (!filesDir.exists()) {
                filesDir.mkdirs();
            }

            File destFile = new File(filesDir, destFileName);

            br = new BufferedReader(new FileReader(destFile));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        } catch (IOException e) {
            java.util.logging.Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage(), e);
        } catch (Exception e) {
            java.util.logging.Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage(), e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {

            }
        }
        return text.toString();
    }


    public static boolean isOnline(Context context) {
        if (context == null) {
            return false;
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isConnectedOrConnecting();
        } else {
            return false;
        }
    }
    public static void dismissKeyboard(Activity activity)
    {
        View v = activity.getWindow().getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
    public static String getDate(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static Typeface getRobotoLight(Context context)
    {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
    }

    public static Typeface getRobotoThin(Context context)
    {
        return  Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Thin.ttf");
    }

    public static Typeface getRobotoBold(Context context)
    {
        return  Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");
    }

    public static Typeface getRobotoMedium(Context context)
    {
        return  Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
    }
    public static Typeface getRobotoRegular(Context context)
    {
        return  Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
    }


    //getDateDiff method find the difference of dates and return the string.
    public static String getDateDiff(long delta) {
        long difference = 0;
        Long mDate = System.currentTimeMillis();

        if (mDate > delta) {
            difference = mDate - delta;
            final long seconds = difference / 1000;
            final long minutes = seconds / 60;
            final long hours = minutes / 60;
            final long days = hours / 24;
            final long months = days / 31;
            final long years = days / 365;

            if (seconds < 0) {
                return "not yet";
            } else {
                if (seconds < 60) {
                    return seconds == 1 ? "one sec ago" : seconds + " secs ago";
                } else {
                    if (seconds < 120) {
                        return "a min ago";
                    } else {
                        if (seconds < 2700) {
                            return minutes + " mins ago";
                        } else {
                            if (seconds < 5400) {
                                return "an hour ago";
                            } else {
                                if (seconds < 86400) {
                                    return hours + " hours ago";
                                } else {
                                    if (seconds < 172800) {
                                        return "yesterday";
                                    } else {
                                        if (seconds < 2592000) {
                                            return days + " days ago";
                                        } else {
                                            if (seconds < 31104000) {

                                                return months <= 1 ? "one month ago" : days + " months ago";
                                            } else {

                                                return years <= 1 ? "one year ago" : years + " years ago";
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return "now";
    }

    public static String decrypt(String seed, String encrypted) throws Exception {
        byte[] keyb = seed.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(keyb);
        SecretKeySpec skey = new SecretKeySpec(thedigest, "AES");
        Cipher dcipher = Cipher.getInstance("AES/ECB/NoPadding");
        dcipher.init(Cipher.DECRYPT_MODE, skey);
        byte[] clearbyte = dcipher.doFinal(toByte(encrypted));
        return new String(clearbyte);
    }

    public static byte[] toByte(String hexString) {
        int len = hexString.length()/2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            result[i] = Integer.valueOf(hexString.substring(2*i, 2*i+2), 16).byteValue();
        }
        return result;
    }

    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return  dp * scale + 0.5f;
    }

    public static float sp2px(Resources resources, float sp){
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public static String get_count_of_days(long toMillis, long fromMillis) {

        long diff = toMillis - fromMillis;
        float dayCount = (float) diff / (24 * 60 * 60 * 1000);
        return ("" + (int) dayCount);
    }
    public static int get_count_of_hours(long toMillis, long fromMillis) {

        long diff = toMillis - fromMillis;
        float hourCount = (float) diff / (60 * 60 * 1000);
        return ( (int) hourCount);
    }
    public static AppSharedPref sharedPreferencesData;

    public static AppSharedPref getSharedPrefernceData(){
        if(sharedPreferencesData==null){
            sharedPreferencesData= new AppSharedPref();
        }

        return sharedPreferencesData;
    }
}

package com.bangladesh.tourism.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Zakir on 20/02/2016.
 */
public class AppUtils {


    public static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(Constants.PREFERENCE_FILE,Context.MODE_PRIVATE);
    }

    public static boolean putImageFileInLocalDir(Context context, File f, Uri uri) {
        try {
            InputStream is = context.getContentResolver().openInputStream(uri);
            FileOutputStream os = new FileOutputStream(f);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            os.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static File getImageDirectory(Context context) {
        return context.getDir(Constants.INSIDE_BANGLADESH_IMAGE_DIRECTORY, Context.MODE_PRIVATE);
    }
}

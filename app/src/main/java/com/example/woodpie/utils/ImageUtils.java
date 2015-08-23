package com.example.woodpie.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by asmita on 22/8/15.
 */
public class ImageUtils
{
    public static Bitmap getBitmap (String url)
    {
        Bitmap bitmap = null;
        try
        {
            URL imageURL = new URL(url);
            URLConnection connection = imageURL.openConnection();
            connection.connect();
            InputStream is = connection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bitmap = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        }
        catch (Exception e)
        {
            Log.e("BITMAP DOWNLOAD", "Error getting bitmap", e);
        }
        return bitmap;
    }
}

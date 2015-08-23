package com.example.woodpie.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import org.apache.http.HttpStatus;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by asmita on 23/8/15.
 */
public class ImageDownloaderTask extends AsyncTask <String, Void, Bitmap>
{
    private final WeakReference<ImageView> imageViewReference;

    public ImageDownloaderTask(ImageView imageView)
    {
        imageViewReference = new WeakReference<ImageView>(imageView);
    }

    @Override
    protected Bitmap doInBackground(String... params)
    {
        return downloadBitmap(params[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap)
    {
        if(isCancelled())
            bitmap = null;
        if (imageViewReference != null)
        {
            ImageView imageView = imageViewReference.get();
            if (imageView != null)
            {
                if (bitmap != null)
                    imageView.setImageBitmap(bitmap);
                else
                {
//                    Drawable placeHolder = imageView.getContext().getResources().getDrawable()
                }
            }
        }
    }

    private Bitmap downloadBitmap(String imageURL)
    {
        HttpURLConnection urlConnection = null;
        try
        {
            URL url = new URL(imageURL);
            urlConnection = (HttpURLConnection) url.openConnection();

            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpStatus.SC_OK)
                return null;

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null)
            {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        }
        catch(Exception e)
        {
            Log.w("ImageDownloader", "Error downloading image from " + imageURL);
        }
        finally
        {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return null;
    }
}

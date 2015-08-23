package com.example.woodpie.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.woodpie.R;
import com.example.woodpie.data.FeedItem;
import com.example.woodpie.utils.ActionType;
import com.example.woodpie.utils.ImageDownloaderTask;
import com.example.woodpie.utils.ImageUtils;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by asmita on 22/8/15.
 */
public class FeedListAdapter extends BaseAdapter
{
    private Activity activity;
    private LayoutInflater inflater;
    private List <FeedItem> feedItems;

    public FeedListAdapter(Activity activity, List <FeedItem> feedItems)
    {
        this.activity = activity;
        this.feedItems = feedItems;
    }

    @Override
    public int getCount() {
        return feedItems.size();
    }

    @Override
    public Object getItem(int position) {
        return feedItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.feed_item_view, null);

        ImageView profilePic = (ImageView) convertView.findViewById(R.id.profile_pic);
        TextView userName = (TextView) convertView.findViewById(R.id.user_name);
        TextView action = (TextView) convertView.findViewById(R.id.action);
        ImageView bookCover = (ImageView) convertView.findViewById(R.id.book_cover);
        TextView bookName = (TextView) convertView.findViewById(R.id.book_name);
        TextView bookAuthor = (TextView) convertView.findViewById(R.id.book_author);
        TextView bookReview = (TextView) convertView.findViewById(R.id.review);
        RatingBar bookRating = (RatingBar) convertView.findViewById(R.id.user_rating);
        TextView likeLink = (TextView) convertView.findViewById(R.id.like);
        TextView commentLink = (TextView) convertView.findViewById(R.id.comment);

        FeedItem feedItem = feedItems.get(position);

        final Bitmap[] profilePicBmp = {null};
        final String profilePicURL = feedItem.getUser().getProfilePicURL();
        if (profilePicURL != null)
        {
            /*Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try
                    {
                        profilePicBmp[0] = ImageUtils.getBitmap(profilePicURL);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        Log.e("BITMAP DOWNLOAD", "Error getting bitmap", e);
                    }
                }
            });
            thread.start();*/

            new ImageDownloaderTask(profilePic).execute(profilePicURL);
        }



        userName.setText(feedItem.getUser().getUserName());
        action.setText(feedItem.getAction().getActionText());

        final Bitmap[] bookCoverBmp = {null};
        final String coverPicURL = feedItem.getBook().getCoverPicURL();
        if (coverPicURL != null)
        {
            /*Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try
                    {
                        bookCoverBmp[0] = ImageUtils.getBitmap(coverPicURL);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        Log.e("BITMAP DOWNLOAD", "Error getting bitmap", e);
                    }
                }
            });
            thread.start();*/
            new ImageDownloaderTask(bookCover).execute(coverPicURL);
        }



        bookName.setText(feedItem.getBook().getBookName());
        bookAuthor.setText(feedItem.getBook().getAuthorName());

        if (feedItem.getAction().equals(ActionType.REVIEW))
        {
            bookReview.setText(feedItem.getReview());
            bookRating.setNumStars(feedItem.getRating());
        }
        else if (feedItem.getAction().equals(ActionType.RATE))
        {
            bookRating.setNumStars(feedItem.getRating());
            bookReview.setVisibility(View.GONE);
        }
        else
        {
            bookRating.setVisibility(View.GONE);
            bookReview.setVisibility(View.GONE);
        }

        if (profilePicBmp[0] != null)
            profilePic.setImageBitmap(profilePicBmp[0]);
        if (bookCoverBmp[0] != null)
            bookCover.setImageBitmap(bookCoverBmp[0]);

        return convertView;
    }
}
